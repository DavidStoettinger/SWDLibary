package at.fh.winb.swd.libary.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestController {
    @GetMapping("/HelloWorld")
    public String helloWorld(){
        return "test";
    }
}
