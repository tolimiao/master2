package com.example.kemei.utils;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonChange {
    /**
     * json字符串转换为map
     */
    public static <T> Map<String, Object> json2map(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(null);
        return mapper.readValue(jsonString, Map.class);
    }

}
