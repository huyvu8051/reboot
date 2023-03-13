/**
 * @Author HuyVu
 * @CreatedDate 3/1/2023 10:43 AM
 */
package io.huyvu.reboot.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONObject;

public class JsonUtils {
    private static final ObjectMapper om = new ObjectMapper();

    @SneakyThrows
    public static JSONObject toJsonObj(Object obj){
        String s = om.writeValueAsString(obj);
        return new JSONObject(s);
    }

    @SneakyThrows
    public static <T> T  toPojoObj(String stringJson, Class<T> clazz){
        return om.readValue(stringJson,clazz);
    }

}
