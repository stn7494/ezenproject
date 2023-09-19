package ez.en.support.controller;

import ez.en.support.domain.Contract;
import ez.en.support.domain.Partner;
import ez.en.support.dto.*;
import ez.en.support.service.ContractServiceImpl;
import ez.en.support.service.ProductServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/partner")
    public void partner(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<Partner> responseDTO = service.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/product")
    public void product(ProductPageRequestDTO pageRequestDTO, Model model){

//        ProductPageResponseDTO<ProductDTO> responseDTO = service


    }
}
