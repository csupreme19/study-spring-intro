package hello.hellospring.config;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomStringHttpMessageConverter extends StringHttpMessageConverter {

    @Override
    protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException {
        String read = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
        return "hello " + read;
    }
}
