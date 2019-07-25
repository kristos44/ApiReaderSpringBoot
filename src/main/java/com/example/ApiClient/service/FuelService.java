package com.example.ApiClient.service;

import com.example.ApiClient.formatter.ResponseFormatter;
import com.example.ApiClient.integration.FuelClient;
import com.example.ApiClient.mapper.JsonToModelMapper;
import com.example.ApiClient.model.StationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuelService {
    private FuelClient fuelClient;
    private ResponseFormatter responseFormatter;
    private JsonToModelMapper jsonToModelMapper;
    @Value("${api.urlStatic}")
    private String apiUrlStatic;
    @Value("${api.urlDynamic}")
    private String apiUrlDynamic;
    @Value("${api.key}")
    private String key;

    public FuelService(FuelClient fuelClient, ResponseFormatter responseFormatter, JsonToModelMapper jsonToModelMapper) {
        this.fuelClient = fuelClient;
        this.responseFormatter = responseFormatter;
        this.jsonToModelMapper = jsonToModelMapper;
    }

    public String fetchStationInfo(String apiUrl, String country) {
        Optional<StationData> staticStationDataOptional = jsonToModelMapper.mapJsonToStationData(
                fuelClient.fetchStationData(apiUrl + apiUrlStatic + country + "&key=" + key));

        Optional<StationData> dynamicStationDataOptional = jsonToModelMapper.mapJsonToStationData(
                fuelClient.fetchStationData(apiUrl + apiUrlDynamic + country + "&key=" + key));

        return responseFormatter.formatResponse(staticStationDataOptional, dynamicStationDataOptional);
    }
}
