package com.example.ApiClient.model;

import com.example.ApiClient.deserialiaze.CustomDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Map;

@Data
@JsonDeserialize(using = CustomDeserializer.class)
public class StationData {
    private final Map<String, Station> stationMap;
}
