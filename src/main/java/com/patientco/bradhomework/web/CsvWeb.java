package com.patientco.bradhomework.web;

import com.patientco.bradhomework.business.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/csv")
@RequiredArgsConstructor
public class CsvWeb {

    private final BusinessService service;

    @GetMapping("{delimiter}")
    public List<Map<String, Object>> read(@PathVariable String delimiter){
        return service.read(delimiter);
    }
}
