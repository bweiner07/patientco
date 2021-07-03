package com.patientco.bradhomework.web;

import com.patientco.bradhomework.business.BuisnessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/csv")
@RequiredArgsConstructor
public class CsvWeb {

    private final BuisnessService service;

    @GetMapping
    public Map<String, Object> read(){
        return service.read();
    }
}
