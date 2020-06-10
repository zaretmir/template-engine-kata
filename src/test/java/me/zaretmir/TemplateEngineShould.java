package me.zaretmir;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineShould {

    private Map<String, String> variables;

    @Before
    public void setup() {
        variables = new HashMap<String, String>();
    }

    @Test
    public void not_parse_if_there_are_no_variables() {
        assertThat(TemplateEngine.parseTemplate("hello", variables)).isEqualTo("hello");
    }

    @Test
    public void replace_variable_in_template() {
        variables.put("usr", "Carlos");

        assertThat(TemplateEngine.parseTemplate("hello `$usr`", variables)).isEqualTo("hello Carlos");
    }

    @Test
    public void replace_several_variables_in_template() {
        variables.put("usr", "Carlos");
        variables.put("day", "Monday");

        assertThat(TemplateEngine.parseTemplate("hello `$usr`, today is `$day`", variables)).isEqualTo("hello Carlos, today is Monday");
    }

    @Test
    public void ignore_variables_that_are_not_in_template() {
        variables.put("usr", "Carlos");

        assertThat(TemplateEngine.parseTemplate("hello", variables)).isEqualTo("hello");
    }

    @Test
    public void leave_variables_in_template_when_they_are_not_found() {
        variables.put("name", "Carlos");

        assertThat(TemplateEngine.parseTemplate("hello `$usr`", variables)).isEqualTo("hello `$usr`");
    }

}
