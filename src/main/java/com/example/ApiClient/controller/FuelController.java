package com.example.ApiClient.controller;

import com.example.ApiClient.service.FuelService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fuel")
@ResponseStatus(HttpStatus.OK)
public class FuelController {
    private FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @GetMapping("/fetchStationInfo")
    public String fetchStationInfo(@ApiParam(defaultValue = "http://localhost:9000") @RequestParam String apiUrl,
                                   @ApiParam(defaultValue = "NO") @RequestParam String country) {
        return fuelService.fetchStationInfo(apiUrl, country);
    }
}
