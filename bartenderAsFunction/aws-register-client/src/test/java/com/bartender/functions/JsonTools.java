package com.bartender.functions;

import com.bartender.model.Json;
import java.util.Map;

public interface JsonTools {

    default Map<String, Object> jsonToMap(String json) {
        return Json.serializer().toMap(json);
    }
}
