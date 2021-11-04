package com.gbsfo.tutor.springboottutor.model;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");
    
    private final String id;
    
    private static final Map<String, Gender> map;
    
    static {
        Map<String, Gender> initMap = new HashMap<>();
        Gender[] list = Gender.values();
        for (Gender gender : list) {
            initMap.put(gender.id, gender);
        }
        map = Collections.unmodifiableMap(initMap);
    }
    
    Gender(String id) {
        this.id = id;
    }

    public String toStringLine() {
        return this.id;
    }
    
    public static Gender fromString(String id) {
        return map.get(id);
    }
}
