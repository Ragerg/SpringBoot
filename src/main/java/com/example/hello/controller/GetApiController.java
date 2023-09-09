package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/get")
public class GetApiController {

    @GetMapping(path = "/hello")    // http://localhost:8080/api/get/hello
    public String getHello() {
        return "get Hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)   // get http://localhost:8080/api/get/hi
    public String hi() {
        return "hi";
    }

    // http://localhost:8080/api/get/path-variable/{name}
    @GetMapping(path="/path-variable/{id}") // 주소에는 대문자를 쓰지 않는다 -> -로 가독성을 높이자
    public String pathVariable(@PathVariable(name="id") String pathName) {

        System.out.println("PathVariable : " + pathName);

        return pathName;
    }

    // Query Parameter : ?key=value&key2=value2
    // http://localhost:8080/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path="query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+"="+entry.getValue()+"\n");
        });

        return sb.toString();
    }

    @GetMapping(path="query-param02")
    public String queryParam02(
            @RequestParam String user,
            @RequestParam String email,
            @RequestParam int age
    ) {

        System.out.println(user);
        System.out.println(email);
        System.out.println(age);

        return user + " " + email + " " + age;
    }

    // 추천 / 제일 많이 활용
    // ?user=steve&email=steve@gmail.com&age=30&address=서울시
    // : 객체에서 정의되지 않은 값이 들어오면 해당 값은 파싱이 되지 않기 때문에 누락되니 유의
    @GetMapping(path="query-param03")
    public String queryParam03(UserRequest userRequest) {

        System.out.println(userRequest.getUser());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }

}
