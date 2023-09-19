package ez.en.support.controller;

import ez.en.support.domain.Contract;
import ez.en.support.dto.ContractPageRequestDTO;
import ez.en.support.dto.ContractPageResponseDTO;
import ez.en.support.service.ContractServiceImpl;
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

    @GetMapping({"/modify","/detail"})
    public void read(String ccode, ContractPageRequestDTO pageRequestDTO, Model model){


    }
}
