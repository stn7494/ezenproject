package ez.en;

import ez.en.order.repository.OrderRepository;
import ez.en.order.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;



}
