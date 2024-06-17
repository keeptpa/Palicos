package com.keeptpa.palicobot;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Map;
public class BotConst {
    public static String POEM_API = "";
    public static String JAR_PATH = BotConst.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    public static String getConfigPath() {
        return JAR_PATH.substring(0, JAR_PATH.lastIndexOf("\\")) + "\\config.json";
    }
    public static Map<String, String> getLanguagePair() {
        //ISO-960 language code:
        String langCode = Locale.getDefault().getLanguage();
        String countryUpperCase = Locale.getDefault().getCountry().toUpperCase();
        String languageFileName = String.format("%s-%s.json", langCode, countryUpperCase);

        ObjectMapper mapper = new ObjectMapper();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("language/" + languageFileName);
        if (url == null) {
            url = loader.getResource("language/en_US.json");
        }
        try{
            Path path = Paths.get(url.toURI());
            String langJson = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            return mapper.readValue(langJson, Map.class);
        }catch (Exception e){

        }
        return null;
    }
}
