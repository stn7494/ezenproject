package ez.en.hsh;


import ez.en.order.repository.OrderRepository;
import ez.en.stock.domain.Stock;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.repository.StockRepository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Test
    public void test(){

    }







}
