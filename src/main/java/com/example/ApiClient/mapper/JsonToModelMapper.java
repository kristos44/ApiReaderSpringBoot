package com.example.ApiClient.mapper;

import com.example.ApiClient.model.StationData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class JsonToModelMapper {
    private ObjectMapper objectMapper;

    public JsonToModelMapper() {
        objectMapper = new ObjectMapper();
    }

    public Optional<StationData> mapJsontoStationData(String json) {
        StationData stationData;
        try {
            stationData = objectMapper.readValue(json, StationData.class);
            return Optional.of(stationData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
