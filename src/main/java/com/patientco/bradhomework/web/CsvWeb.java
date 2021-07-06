package com.patientco.bradhomework.web;

import com.patientco.bradhomework.business.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/csv")
@RequiredArgsConstructor
public class CsvWeb {

    private final BusinessService service;

    @PostMapping
    public List<Map<String, Object>> read(@RequestBody FileData body){
        return service.read(body.delimiter, body.filePath);
    }
}
