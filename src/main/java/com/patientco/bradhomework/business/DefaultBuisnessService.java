package com.patientco.bradhomework.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

@Slf4j
@Service
public class DefaultBuisnessService implements BuisnessService{

    //TODO: Handle Commas inside quotes
    //TODO: Handle quotes ""word"" -> ""word"" !-> "\"word\""
    @Override
    public List<Map<String, Object>> read(String delimiter) {
        List<Map<String, Object>> values = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/input.txt")));
            String headers = reader.readLine();
            List<String> titles = Arrays.asList(headers.split(delimiter));

            if(!titles.isEmpty()){
                String line;
                while((line = reader.readLine()) != null){

                    List<String> entries = Arrays.asList(line.split(delimiter));
                    values.add(mapEntriesToTitles(titles, entries));
                }
            }


        }catch (Exception e){
          log.warn("doom");
        }

        return values;
    }

    private Map<String, Object> mapEntriesToTitles(List<String> titles, List<String> entries) {
        Map<String, Object> values = new HashMap<>();
        for(int i = 0; i < titles.size(); i++){
            String entry = entries.get(i);
            if (isNumber(entry)) {
                values.put(titles.get(i), Integer.parseInt(entry));
            } else {
                if(entry.contains("\"")){
                    entry
                }
                values.put(titles.get(i), entry);
            }

        }
        return values;
    }

    private boolean isNumber(String item){
        try {
            Integer.parseInt(item);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
