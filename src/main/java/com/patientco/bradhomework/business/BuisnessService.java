package com.patientco.bradhomework.business;

import java.util.List;
import java.util.Map;

public interface BuisnessService {

    List<Map<String, Object>> read(String delimiter);
}
