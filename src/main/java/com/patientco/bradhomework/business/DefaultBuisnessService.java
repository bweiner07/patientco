package com.patientco.bradhomework.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

@Slf4j
@Service
public class DefaultBuisnessService implements BuisnessService{

    @Override
    public Map<String, Object> read() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("")));
            String line;
            while((line = reader.readLine()) != null){
               
            }
        }catch (Exception e){
          log.warn("doom");
        }
        return null;
    }
}
