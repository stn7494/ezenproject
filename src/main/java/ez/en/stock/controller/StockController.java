package ez.en.stock.controller;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.dto.OrderDTO;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.dto.StockInDTO;
import ez.en.stock.dto.StockOutDTO;
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
    public void stockList(PageRequestDTO pageRequestDTO, Model model){
        List<Integer> pnoList = stockService.getPno();
        for(int i:pnoList){
            int siAll = stockService.getSicountAll(i);
            stockService.sicountAll(i,siAll);
        }

        List<Integer> snoList = stockService.getSno();
        for(int i:snoList){
            int soAll = stockService.getSocountAll(i);
            stockService.socountAll(i,soAll);
        }
        PageResponseDTO<StockDTO> responseDTO = stockService.sList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/stock/orderList")
    public void orderList(PageRequestDTO pageRequestDTO,Model model){
        PageResponseDTO<OrderDTO> responseDTO = stockService.oList(pageRequestDTO);
        model.addAttribute("oList",responseDTO);
    }

    @GetMapping("/stock/inList")
    public void inList(PageRequestDTO pageRequestDTO,Model model){
        PageResponseDTO<StockInDTO> responseDTO = stockService.inList(pageRequestDTO);
        model.addAttribute("inList",responseDTO);

    }

    @GetMapping("/stock/outList")
    public void outList(PageRequestDTO pageRequestDTO,Model model){
        PageResponseDTO<StockOutDTO> responseDTO = stockService.outList(pageRequestDTO);
        model.addAttribute("outList",responseDTO);
    }

    @GetMapping("/stock/stockIn")
    public void stockIn(int ono,Model model){
        OrderDTO order = stockService.orderDetail(ono).get(0);
        model.addAttribute("dto",order);
    }

    @PostMapping("/stock/stockIn")
    @ResponseBody
    public void stockIn(int ono, String email, int pno, int cno, int sicount){
        if(stockService.checkStock(pno)==null){
            stockService.addStock(pno, cno);
        }
        String sidate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")); // 날짜 포맷
        stockService.insertIn(ono, email,sidate,pno,sicount); // 입고완료시 입고테이블에 데이터추가
        stockService.updateOstate(ono); // 발주상태 입고완료로 변경

    }

    @GetMapping("/stock/stockOut")
    public void stockOut(int sno,Model model){
        StockDTO dto = stockService.stockDetail(sno).get(0);
        model.addAttribute("dto",dto);
    }

    @PostMapping("/stock/stockOut")
    @ResponseBody
    public void stockOut(int sno, String email,int socount){
        String sodate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")); // 날짜 포맷
        stockService.insertOut(sno, email,sodate,socount); // 출고완료시 출고테이블에 데이터추가

    }
}
