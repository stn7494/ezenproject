package ez.en.support.controller;


import ez.en.support.dto.ProductDTO;
import ez.en.support.dto.ProductPageRequestDTO;
import ez.en.support.dto.ProductPageResponseDTO;
import ez.en.support.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/support")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //제품전체목록 보여주기
    @GetMapping("/productList")
    public void list(ProductPageRequestDTO productPageRequestDTO, Model model){ // list메소드로 페이징처리와 검색에 이용
        ProductPageResponseDTO<ProductDTO> responseDTO = productService.list(productPageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);


    }
}
