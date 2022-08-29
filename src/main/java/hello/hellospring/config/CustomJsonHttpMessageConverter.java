package hello.hellospring.config;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class CustomJsonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public CustomJsonHttpMessageConverter() {
        super.setJsonPrefix("hello");
    }

}
