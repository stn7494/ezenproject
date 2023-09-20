package ez.en.support.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supportpaln")
public class SupportPlanController {

    @GetMapping("/list")
    public void list(){

    }

}
