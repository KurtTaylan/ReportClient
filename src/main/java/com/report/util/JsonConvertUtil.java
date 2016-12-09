package com.report.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Utility for JSON data transform when its necessary.
 */
public class JsonConvertUtil {


    private static ObjectMapper MAPPER = null;



    public static <T> T toObjectFromJson(String jsonStr, Class<T> clazz) throws IOException {
        ObjectMapper mapper = getMapper();
        return mapper.readValue(jsonStr, clazz);
    }


    public static String toJsonFrom(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = getMapper();
        return mapper.writeValueAsString(obj);
    }


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {                                    // For testing reasons
        ObjectMapper mapper = getMapper();
        return mapper.writeValueAsBytes(object);
    }

    private static ObjectMapper getMapper() {
        if (MAPPER == null) {
            MAPPER = new ObjectMapper();
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return MAPPER;
    }
}
