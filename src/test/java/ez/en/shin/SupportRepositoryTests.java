package ez.en.shin;

import ez.en.support.domain.Middle;
import ez.en.support.domain.Product;
import ez.en.support.domain.Supportplan;
import ez.en.support.domain.Top;
import ez.en.support.repository.MiddleRepository;
import ez.en.support.repository.ProductRepository;
import ez.en.support.repository.SupportplanRepository;
import ez.en.support.repository.TopRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class SupportRepositoryTests {

    @Autowired
    private SupportplanRepository supportplanRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TopRepository topRepository;

    @Autowired
    private MiddleRepository middleRepository;
    @Test
    public void insert(){

//        List<Product> list = productRepository.findAll();
//        List<Product> list = productRepository.joinList();

        List<Middle> list = middleRepository.category("T01");

        for (Middle middle:list
             ) {
            log.info("카테고리 목록 : " + middle);
        }
//        for (Product product:list
//             ) {
//            log.info("조회된 상품 : " + product.getMiddle().getTop().getTname());
//        }

        Supportplan supportplan = Supportplan.builder()
                .build();

    }


}
