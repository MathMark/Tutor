package com.gbsfo.tutor.springboottutor.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum TeacherStatus {
    FIRED("Fired"),
    VACATION("Vacation"),
    SICK("Sick"),
    ACTIVE("Active");
    
    private final String id;
    
    private static final Map<String, TeacherStatus> map;
    
    static {
        Map<String, TeacherStatus> initMap = new HashMap<>();
        TeacherStatus[] list = TeacherStatus.values();
        for (TeacherStatus teacherStatus : list) {
            initMap.put(teacherStatus.id, teacherStatus);
        }
        map = Collections.unmodifiableMap(initMap);
    }
    
    TeacherStatus(String id) {
        this.id = id;
    }
    
    public String toStringLine() {
        return this.id;
    }
    
    public static TeacherStatus fromString(String id) {
        return map.get(id);
    }
}
