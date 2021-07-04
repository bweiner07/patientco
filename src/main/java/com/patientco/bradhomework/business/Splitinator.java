package com.patientco.bradhomework.business;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class Splitinator {

    public List<String> split(String unSplit, char delimiter) {
        if (isNull(unSplit) || unSplit.isBlank()) {
            return List.of();
        }

        List<String> splitWords = new ArrayList<>();
        for (int i = 0; i < unSplit.length(); i++) {
            if (unSplit.charAt(i) == '"') {
                StringBuilder word = new StringBuilder();
                for (int j = i + 1; j < unSplit.length(); j++) {
                    if (unSplit.charAt(j) == '"') {
                        if (j + 1 == unSplit.length() || unSplit.charAt(j + 1) == delimiter) {
                            splitWords.add(word.toString().trim());
                            i = j + 1;
                            break;
                        }
                        else if(unSplit.charAt(j + 1) == '"') {
                            word.append("\"");
                            j++;
                        }
                    }
                    else {
                        word.append(unSplit.charAt(j));
                    }
                }
            }
            else {
                StringBuilder word = new StringBuilder();
                for (int j = i; j < unSplit.length(); j++) {
                    if (j + 1 == unSplit.length()) {
                        word.append(unSplit.charAt(j));
                        splitWords.add(word.toString().trim());
                        i = j + 1;
                        break;
                    }
                    else if (unSplit.charAt(j) == delimiter){
                        splitWords.add(word.toString().trim());
                        i = j + 1;
                        break;
                    }
                    word.append(unSplit.charAt(j));
                }
            }
        }
        return splitWords;
    }
}
