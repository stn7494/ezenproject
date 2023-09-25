package ez.en.stock.controller;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.dto.OrderDTO;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.dto.StockInDTO;
import ez.en.stock.service.StockService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class StockController {


    private final StockService stockService;


    @GetMapping("/stock/stockList")
    public void stockList(Model model){
        List<Integer> pnoList = stockService.getPno();
        for(int i:pnoList){
            int all = stockService.getSicountAll(i);
            stockService.sicountAll(i,all);
        }

        List<StockDTO> sList = stockService.getStock();
        model.addAttribute("sList",sList);


    }

    @GetMapping("/stock/orderList")
    public void orderList(Model model){
        List<OrderDTO> list = stockService.getOrder();
        model.addAttribute("oList",list);
    }

    @GetMapping("/stock/ioList")
    public void ioList(Model model){
        List<StockInDTO> inList = stockService.getIn();
        model.addAttribute("inList",inList);
    }

    @GetMapping("/stock/stockIn")
    public void stockIn(int index,Model model){
        OrderDTO order = stockService.getOrder().get(index);
        model.addAttribute("dto",order);
    }


    @PostMapping("/stock/stockIn")
    @ResponseBody
    public void stockIn(int ono, String email, int pno, int sicount){
        String sidate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")); // 날짜 포맷
        stockService.insertIn(ono, email,sidate,pno,sicount); // 입고완료시 입고테이블에 데이터추가
        stockService.updateOstate(ono); // 발주상태 입고완료로 변경
    }
}
