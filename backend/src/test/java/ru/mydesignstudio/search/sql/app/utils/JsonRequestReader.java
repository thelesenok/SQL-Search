package ru.mydesignstudio.search.sql.app.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class JsonRequestReader {
    /**
     * Read string from resource file.
     * @param filename filename
     * @return content as a string
     */
    public static final String readFromFile(final String filename) {
        try (InputStream resource = JsonRequestReader.class.getResourceAsStream("/" + filename)) {
            return IOUtils.toString(resource, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
