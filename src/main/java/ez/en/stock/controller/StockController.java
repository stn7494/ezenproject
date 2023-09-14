package ez.en.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {



    @GetMapping("/stock/index")
    public void index(){
    }
}
