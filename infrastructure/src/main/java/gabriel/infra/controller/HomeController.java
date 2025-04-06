package gabriel.infra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "pages/index";
    }

    @GetMapping("/library")
    public String library(){
        return "pages/library/home";
    }

}
