package ez.en.kdj;


import ez.en.support.domain.Product;
import ez.en.support.dto.MiddleDTO;
import ez.en.support.dto.ProductDTO;
import ez.en.support.dto.ProductPageRequestDTO;
import ez.en.support.dto.ProductPageResponseDTO;
import ez.en.support.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testproductRegister() {
        log.info(productService.getClass().getName());

        MiddleDTO middleDTO = MiddleDTO.builder()
                .mcode("M11")
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .pcode("P20230914T02M11KB04")
                .pname("갤럭시북프로360 키보드")
                .pcontent("삼성에서 만든 키보드")
                .pnote("2021년 생산")
                .middleDTO(middleDTO)
                .build();

    }



    @Test
    public void testProductList() {
        ProductPageRequestDTO productPageRequestDTO = ProductPageRequestDTO.builder()
                .type("cm")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        ProductPageResponseDTO<ProductDTO> responseDTO = productService.list(productPageRequestDTO);

        log.info(responseDTO);
    }


    @Test
    public void testRemoveAll() {
        int pno = 44;
        productService.productremove(pno);
    }





}
