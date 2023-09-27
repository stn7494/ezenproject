package ez.en.hsh;


import ez.en.order.repository.OrderRepository;
import ez.en.stock.domain.Stock;
import ez.en.stock.domain.Stockin;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.repository.StockRepository;

import ez.en.stock.repository.StockinRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Log4j2
public class StockTest {

    @Autowired
    StockRepository stockRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    StockinRepository stockinRepository;

    @PersistenceContext
    private EntityManager em;


    @Test
    public void test(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("sino").descending());
        Page<Stockin> result = stockinRepository.searchIn(pageable);
        log.info("조회개수 : "+result.getTotalElements());
        List<Stockin> list = result.getContent();

        for (Stockin stockin:list){
            log.info(stockin);
        }
    }








}
