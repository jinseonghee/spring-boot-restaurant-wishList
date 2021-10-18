package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // @RestController가 아닌 그냥 @Controller 사용
@RequestMapping("/pages")
public class PageController {

    @GetMapping("/main")
    public ModelAndView main() { //타임리프에서 특정한 html model을 view 로 보낼때 사용
        return new ModelAndView("aaaa/main"); //templates 하위의 main경
    }
}
