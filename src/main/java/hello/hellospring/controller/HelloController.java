package hello.hellospring.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    /**
     * 일반 View 응답인 경우 HttpMessageConverter가 아닌 ViewResolver를 사용한다.
     */
    @RequestMapping("hello-mvc")
    public String hello(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @RequestMapping("hello-string")
    public @ResponseBody String helloString(@RequestParam("name") String name) {
        return name;
    }

    /**
     * ResponseBody 사용시 객체를 JSON 형태로 변환해준다.
     * Spring Boot에 HttpMessageConverter가 빈으로 등록되어 있기 때문
     * 1. String인 경우 StringHttpMessageConvereter
     * 2. Object인 경우 MappingJackson2HttpMessageConverter
     * 이 외 여러 MessageConverter들이 이미 등록되어 있음
     */
    @GetMapping("hello-api")
    public @ResponseBody Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    @Getter
    @Setter
    static class Hello {
        private String name;

        // 자바 빈 규약
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
