package ez.en.support.controller;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Supportplan;
import ez.en.support.dto.*;
import ez.en.support.service.ProductServiceImpl;
import ez.en.support.service.SupportPlanServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/supportplan")
@Log4j2
public class SupportPlanController {

    @Autowired
    private SupportPlanServiceImpl service;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<Supportplan> responseDTO = service.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(SupportPlanDTO planDTO, ProductDTO productDTO, MiddleDTO middleDTO){
        planDTO.setProductDTO(productDTO);
        planDTO.setMiddleDTO(middleDTO);
        planDTO.setSpstate("조달요청");
        service.insert(planDTO);

        return "redirect:/supportplan/list";
    }
    @GetMapping("/product")
    public void product(ProductPageRequestDTO pageRequestDTO, Model model){

        ProductPageResponseDTO<ProductDTO> responseDTO = productService.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);

    }

    @GetMapping({"detail","modify"})
    public void detail(PageRequestDTO pageRequestDTO, @RequestParam("spno")int spno, Model model){
          log.info("값 : "+spno);
        SupportPlanDTO planDTO = service.selectOne(spno);
        log.info("dto값 : " + planDTO);
        log.info("dto값 : " + planDTO.getProductDTO());

        model.addAttribute("dto", planDTO);

    }

    @PostMapping("modify")
    public String modify(PageRequestDTO pageRequestDTO, SupportPlanDTO planDTO){

        log.info("===========================================================================");

        service.update(planDTO);

        return "redirect:/supportplan/detail?spno="+planDTO.getSpno()+"&"+pageRequestDTO.getLink();
    }

    @ResponseBody
    @PostMapping("/modal")
    public SupportPlanDTO modal(@RequestParam("data")int data){
        return service.selectOne(data);
    }


    @GetMapping("remove")
    public String remove(PageRequestDTO pageRequestDTO, @RequestParam("spno")int spno){
        service.delete(spno);
        return "redirect:/supportplan/list?"+pageRequestDTO.getLink();
    }


    @GetMapping("/state")
    public String state(PageRequestDTO pageRequestDTO, @RequestParam("spno")int spno, @RequestParam("state")String state){
        String link = pageRequestDTO.getLink();
        service.stateUpdate(spno, state);
        return "redirect:/supportplan/list?"+link;
    }
}
