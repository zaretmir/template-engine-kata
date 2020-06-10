package me.zaretmir;

import java.util.Map;
import java.util.Set;

public class TemplateEngine {

    public static String parseTemplate(String template, Map<String, String> variables) {
        if (variables.isEmpty()) {
            return template;
        }

        Set<String> keys = variables.keySet();
        for (String key : keys) {
            String value = variables.get(key);
            String templateVariable = String.format("`$%s`", key);
            template = template.replace(templateVariable, value);
        }

        return template;
    }

    private TemplateEngine() {}
}
