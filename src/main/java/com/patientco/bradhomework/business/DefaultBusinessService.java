package com.patientco.bradhomework.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultBusinessService implements BusinessService {

    //Todo, handle if entry is missing...need to insert a blank or something
    //Todo: proper exception handling
    //Todo: tests..shame no TDD
    private final Splitinator splitinator;

    @Override
    public List<Map<String, Object>> read(String delimiter) {
        List<Map<String, Object>> values = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
            String headers = reader.readLine();
            List<String> titles = Arrays.asList(headers.split(delimiter));

            if(!titles.isEmpty()){
                String line;
                while((line = reader.readLine()) != null){

                    List<String> entries = splitinator.split(line, delimiter.charAt(0));
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
                values.put(titles.get(i).trim(), Integer.parseInt(entry));
            } else {
                values.put(titles.get(i).trim(), entry);
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
