package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;

import java.io.File;
import java.io.IOException;

public class JsonParseUtil {

    private JsonParseUtil() {
    }

    public static Person parseJson(String file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(file), Person.class);
        } catch (IOException ex) {
            ex.getMessage();
            throw new IllegalStateException();
        }
    }

}
