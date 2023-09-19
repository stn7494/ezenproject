package ez.en.support.controller;

import ez.en.support.domain.Contract;
import ez.en.support.domain.Partner;
import ez.en.support.dto.*;
import ez.en.support.service.ContractServiceImpl;
import ez.en.support.service.ProductServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractServiceImpl service;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/list")
    public ModelAndView contractList(ContractPageRequestDTO pageRequestDTO){
        ModelAndView mav = new ModelAndView();

        log.info(pageRequestDTO.getState());
        log.info(pageRequestDTO.getKeyword());
        log.info(pageRequestDTO.getType());

        ContractPageResponseDTO<Contract> responseDTO = service.list(pageRequestDTO);

        mav.addObject("responseDTO", responseDTO);

        mav.setViewName("/contract/contractList");

        return mav;
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(ContractDTO contractDTO, PartnerDTO partnerDTO, ProductDTO productDTO, MiddleDTO middleDTO, TopDTO topDTO){
        log.info(contractDTO);
        log.info(partnerDTO);
        log.info(productDTO);
        log.info(middleDTO);
        log.info(topDTO);
        String ccode = "C";
        String[] day = contractDTO.getCdate().split("-");
        for(String str:day){
            ccode += str;
        }
        ccode += topDTO.getTcode();
        ccode += middleDTO.getMcode();
        for (int i = 0; i < 6; i++){
            if(i <=1){
                char c = (char)((int)(Math.random()*25)+65);
                ccode += c;
            }else{
                int n = (int)(Math.random()*9)+1;
                ccode += n;
            }
        }
        log.info(ccode);
        contractDTO.setCcode(ccode);
        contractDTO.setCstate("계약완료");
        contractDTO.setPartnerdto(partnerDTO);
        contractDTO.setProductdto(productDTO);
        service.insert(contractDTO);
        return "redirect:/contract/list";
    }


    @GetMapping("/partner")
    public void partner(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<Partner> responseDTO = service.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/product")
    public void product(ProductPageRequestDTO pageRequestDTO, Model model){

        ProductPageResponseDTO<ProductDTO> responseDTO = productService.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);

    }

    @GetMapping({"detail","modify"})
    public void modify(@RequestParam("ccode")String ccode, Model model){
        ContractDTO dto = service.selectOne(ccode);
        model.addAttribute("dto", dto);
        log.info("조회결과 : "+dto);
    }
}
