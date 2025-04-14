package gabriel.infra.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/library", "/"})
    public String library(){
        return "pages/library/home";
    }

}
