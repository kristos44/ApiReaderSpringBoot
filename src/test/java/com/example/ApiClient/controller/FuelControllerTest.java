package com.example.ApiClient.controller;

import com.example.ApiClient.integration.FuelClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FuelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FuelClient fuelClient;
    @Value("${feedFile.static}")
    private String feedFileStatic;
    @Value("${feedFile.dynamic}")
    private String feedFileDynamic;

    @Test
    public void fetchStationInfo() {
        String staticResponse = "";
        String dynamicResponse = "";

        try {
            staticResponse = IOUtils.toString(
                    this.getClass().getResourceAsStream(feedFileStatic), "UTF-8"
            );
            dynamicResponse = IOUtils.toString(
                    this.getClass().getResourceAsStream(feedFileDynamic), "UTF-8"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        when(fuelClient.fetchStationData("http://localhost:8080/fuel/v1.0/bulk/static/NO"))
                .thenReturn(staticResponse);

        when(fuelClient.fetchStationData("http://localhost:8080/fuel/v1.0/bulk/dynamic/NO"))
                .thenReturn(dynamicResponse);

        try {
            this.mockMvc.perform(
                    get("/fuel/fetchStationInfo?apiUrl=http://localhost:8080&country=NO"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("Stacji ogółem: 1831\n" +
                            "Stacji z cenami: 1625\n" +
                            "Circle K 428\n" +
                            "Shell 333\n" +
                            "Esso 236\n" +
                            "YX 227\n" +
                            "Uno-X 167\n" +
                            "Best 86\n" +
                            "Independent 73\n" +
                            "St1 32\n" +
                            "Bunker Oil 28\n" +
                            "Automat1 5\n" +
                            "1-2-3 1\n" +
                            "Automat 1 1\n" +
                            "Bilhuset Sandnes 1\n" +
                            "Buskerud Olje AS 1\n" +
                            "Fåvang Servicesenter As 1\n" +
                            "Jæren Olje Skurve 1\n" +
                            "Max Bensin 1\n" +
                            "Pedersen Varmeservice Alta 1\n" +
                            "Tanken Brenna 1\n" +
                            "Trøndelag Diesel 1\n")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
