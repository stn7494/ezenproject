package ez.en.order.controller;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.login.domain.Login;
import ez.en.login.service.LoginService;
import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.order.dto.PopContractDTO;
import ez.en.order.dto.PopSplanDTO;
import ez.en.order.dto.ProgressInspectionDTO;
import ez.en.order.service.OrderService;
import ez.en.order.service.ProgressInspectionService;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Supportplan;
import ez.en.support.dto.ContractPageRequestDTO;
import ez.en.support.dto.ContractPageResponseDTO;
import ez.en.support.dto.SupportPlanDTO;
import ez.en.support.service.ContractService;
import ez.en.support.service.SupportPlanService;
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
    private final ProgressInspectionService progressInspectionService;
    private final SupportPlanService supportPlanService;
    private final LoginService loginService;

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
        log.info("CONTROLLER orderDTO : "+orderDTO);
        model.addAttribute("dto", orderDTO);
    }

//    발주 상세 수정
    @PostMapping("/order/detail")
    public String detailPost(OrderDTO orderDTO, RedirectAttributes redirectAttributes){
        int ono = orderService.modify(orderDTO);
        redirectAttributes.addAttribute("ono", ono);
        return "redirect:/order/detail";
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
        Supportplan supportplan = Supportplan.builder()
                .spno(orderDTO.getSpno())
                .spstate("조달처리중")
                .build();
        supportPlanService.updateState(supportplan);
        redirectAttributes.addFlashAttribute("result", "regist");
        return "redirect:/order/list";
    }

//    발주 등록시 조달 계획 선택 팝업
    @GetMapping("/order/popSplan")
    public void popSplanGet(Model model){
        List<PopSplanDTO> spDTO = orderService.popSplanList();
        model.addAttribute("spDTO", spDTO);
    }

//    발주 등록시 계약 선택 팝업
    @GetMapping("/order/popContract")
    public void popContract(String pcode, Model model){
        List<PopContractDTO> cDTO = orderService.popContractList(pcode);
        model.addAttribute("cDTO", cDTO);
    }

//    발주 상세 확인에서 진척 검수 팝업
    @GetMapping("/order/popInspection")
    public void popInspectionGet(int ono, Model model){
        List<ProgressInspectionDTO> piDTO = progressInspectionService.popPiList(ono);
        List<Login> memberDTO = loginService.listAll();
        log.info("piDTO : "+piDTO);
        ProgressInspectionDTO dto1=ProgressInspectionDTO.builder()
                .orders(Orders.builder().build())
                .login(Login.builder().build())
                .build();
        ProgressInspectionDTO dto2=ProgressInspectionDTO.builder()
                .orders(Orders.builder().build())
                .login(Login.builder().build())
                .build();
        for(ProgressInspectionDTO dto: piDTO){
            if(dto.getPisequence()==1){
                dto1 = dto;
            }
            else if(dto.getPisequence()==2){
                dto2 = dto;
            }
        }
        log.info("dto1 : "+dto1+", dto2 : "+dto2);
        model.addAttribute("dto1", dto1);
        model.addAttribute("dto2", dto2);
        model.addAttribute("memDTO", memberDTO);
    }

    @PostMapping("/order/popInspection")
    public String popInspectionPost(ProgressInspectionDTO progressInspectionDTO){
        progressInspectionService.save(progressInspectionDTO);
        return "redirect:/order/popInspection";
    }









}
