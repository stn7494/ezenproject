package ez.en.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {



    @GetMapping("/stock/stockList")
    public void stockList(){
    }

    @GetMapping("/stock/orderList")
    public void orderList(){
    }

    @GetMapping("/stock/ioList")
    public void ioList(){
    }
}
