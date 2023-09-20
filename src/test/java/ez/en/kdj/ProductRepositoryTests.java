package ez.en.kdj;

import ez.en.support.domain.Product;
import ez.en.support.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSearch1() {
        //2페이지 번호순(pno)으로 내림차순
        Pageable pageable = PageRequest.of(1,10, Sort.by("pno").descending());
        productRepository.productsearch1(pageable);
    }

    @Test
    public void testProductsearchAll() {
        String[] types = {"c","m"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());
        Page<Product> result = productRepository.productsearchAll(types, keyword, pageable);

        //total pages
        log.info(result.getTotalPages());
        //page size
        log.info(result.getSize());
        //pageNumber
        log.info(result.getNumber());
        //prev next
        log.info(result.hasPrevious() +": " + result.hasNext());
        result.getContent().forEach(product -> log.info(product));
    }

    @Test
    public void testproductUpdate() {
        int pno = 38;
        Optional<Product> result = productRepository.findById(pno);
        Product product = result.orElseThrow();
        product.productchange("업데이트 삼성 키보드","업데이트 테스트임...", "수정한거임" );
        productRepository.save(product);
    }

//    @Test
//    public void testInsertWithImages() {
//        Product product = Product.builder()
//                .pcode("P20230914T01M05SS08")
//                .pname("이미지테스트")
//                .pcontent("첨부파일 테스트")
//                .build();
//
//        for (int i = 0; i <3; i++) {
//            product.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");
//        }// end for
//
//        productRepository.save(product);
//
//    }






}
