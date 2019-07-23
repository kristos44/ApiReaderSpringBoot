package com.example.ApiClient.service;

import com.example.ApiClient.formatter.ResponseFormatter;
import com.example.ApiClient.integration.FuelClient;
import com.example.ApiClient.mapper.JsonToModelMapper;
import com.example.ApiClient.model.StationData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuelService {
    private FuelClient fuelClient;
    private ResponseFormatter responseFormatter;
    private JsonToModelMapper jsonToModelMapper;
    @Value("${apiUrl.static}")
    private String apiUrlStatic;
    @Value("${apiUrl.dynamic}")
    private String apiUrlDynamic;

    public FuelService(FuelClient fuelClient, ResponseFormatter responseFormatter, JsonToModelMapper jsonToModelMapper) {
        this.fuelClient = fuelClient;
        this.responseFormatter = responseFormatter;
        this.jsonToModelMapper = jsonToModelMapper;
    }

    public String fetchStationInfo(String apiUrl, String country) {
        Optional<StationData> staticStationDataOptional = jsonToModelMapper.mapJsontoStationData(
                fuelClient.fetchStationData(apiUrl + apiUrlStatic + country));

        Optional<StationData> dynamicStationDataOptional = jsonToModelMapper.mapJsontoStationData(
                fuelClient.fetchStationData(apiUrl + apiUrlDynamic + country));

        return responseFormatter.formatResponse(staticStationDataOptional, dynamicStationDataOptional);
    }
}
