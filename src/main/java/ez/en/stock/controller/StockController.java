package ez.en.stock.controller;

import ez.en.order.dto.OrderDTO;
import ez.en.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StockController {


    private final StockService stockService;


    @GetMapping("/stock/stockList")
    public void stockList(){
    }

    @GetMapping("/stock/orderList")
    public void orderList(Model model){
        List<OrderDTO> list = stockService.getOrder();
        model.addAttribute("oList",list);
    }

    @GetMapping("/stock/ioList")
    public void ioList(){
    }
}
