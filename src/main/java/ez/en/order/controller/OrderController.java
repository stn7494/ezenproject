package ez.en.order.controller;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.dto.OrderDTO;
import ez.en.order.dto.PopContractDTO;
import ez.en.order.dto.PopSplanDTO;
import ez.en.order.service.OrderService;
import ez.en.support.domain.Contract;
import ez.en.support.dto.ContractPageRequestDTO;
import ez.en.support.dto.ContractPageResponseDTO;
import ez.en.support.service.ContractService;
import ez.en.support.service.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@Log4j2
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

//    발주 목록 (선택 정렬 기능 미완성)
    @GetMapping("/order/list")
    public void listGet(PageRequestDTO pageRequestDTO, Model model){
        String sortNow="ono";
        log.info("sortNow : "+sortNow+" sort : "+pageRequestDTO.getSort() );
        PageResponseDTO<OrderDTO> responseDTO = orderService.orderList(pageRequestDTO, sortNow);
        model.addAttribute("responseDTO", responseDTO);
    }

//    발주 하나 상세 확인
    @GetMapping("/order/detail")
    public void detailGet(PageRequestDTO pageRequestDTO, Model model, int ono){
        OrderDTO orderDTO = orderService.detail(ono);
//        조달 리스트, 계약 상세 확인 기능 필요
        model.addAttribute("dto", orderDTO);
    }

//    발주 등록 페이지 이동
    @GetMapping("/order/register")
    public void registerGet(){
    }

//    발주 등록
    @PostMapping("/order/register")
    public String registerPost(OrderDTO orderDTO, RedirectAttributes redirectAttributes){
        orderDTO.setOcode("O"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddmmssSS")));
        orderDTO.setOstate("발주신청");
        orderDTO.setOdate(LocalDate.now().toString());
        log.info("CONTROLLER REGISTER orderDTO : "+orderDTO);
        int ono = orderService.register(orderDTO);
        redirectAttributes.addFlashAttribute("result", "regist");
        return "redirect:/order/register";
    }

//    발주 수정 페이지 이동
    @GetMapping("/order/modify")
    public void modifyGet(Model model, int ono){
        OrderDTO orderDTO = orderService.detail(ono);
        model.addAttribute("dto", orderDTO);
    }

//    발주 수정
    @PostMapping("/order/modify")
    public String modify(OrderDTO orderDTO, RedirectAttributes redirectAttributes){
        orderService.modify(orderDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("ono", orderDTO.getOno());
        return "redirect:/order/detail";

    }

    @GetMapping("/order/popSplan")
    public void popSplanGet(Model model){
        List<PopSplanDTO> spDTO = orderService.popSplanList();
        model.addAttribute("spDTO", spDTO);

    }

    @GetMapping("/order/popContract")
    public void popContract(String pcode, Model model){
        List<PopContractDTO> cDTO = orderService.popContractList(pcode);
        model.addAttribute("cDTO", cDTO);
    }









}
