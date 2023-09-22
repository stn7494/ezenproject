package ez.en.support.controller;


import ez.en.support.dto.MiddleDTO;
import ez.en.support.dto.ProductDTO;
import ez.en.support.dto.ProductPageRequestDTO;
import ez.en.support.dto.ProductPageResponseDTO;
import ez.en.support.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //품목전체목록 보여주기
    @GetMapping("/list")
    public void list(ProductPageRequestDTO productPageRequestDTO, Model model){ // list메소드로 페이징처리와 검색에 이용
        ProductPageResponseDTO<ProductDTO> responseDTO = productService.list(productPageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    //품목등록
    @GetMapping("/register")
    public void productregisterGET() {

    }

    @PostMapping("/register")
    public String productregisterPOST(ProductDTO productDTO, MiddleDTO middleDTO, BindingResult bindingResult,
                                      MultipartFile file, RedirectAttributes redirectAttributes) throws Exception{
        log.info("product POST register.....");
        productDTO.setMiddleDTO(middleDTO);
        log.info(productDTO.getMiddleDTO());

        int pno = productService.productregister(productDTO, file);
        redirectAttributes.addFlashAttribute("result", pno);
        return "redirect:/product/list";
    }

    //품목 상세조회
    @GetMapping({"/detail", "/modify"})
    public void productread(int pno, ProductPageRequestDTO productPageRequestDTO, Model model){
        ProductDTO productDTO = productService.productreadOne(pno);
        log.info(productDTO);
        model.addAttribute("dto", productDTO);
    }

    //품목 수정
    @PostMapping("/modify")
    public String productmodify(ProductPageRequestDTO productPageRequestDTO, ProductDTO productDTO, BindingResult bindingResult,
                                MultipartFile file,RedirectAttributes redirectAttributes) throws Exception{
        log.info("product modify post........" + productDTO);
        productService.productmodify(productDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("pno", productDTO.getPno());
        return "redirect:/product/detail";

    }

    // 품목 삭제
    @PostMapping("/remove")
    public String productremove(int pno, RedirectAttributes redirectAttributes){
        log.info("remove post..." + pno);
        productService.productremove(pno);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/product/list";
    }


}
