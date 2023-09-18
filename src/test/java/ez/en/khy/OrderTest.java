package ez.en.khy;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.order.repository.OrderRepository;
import ez.en.order.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Log4j2
public class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;


    @Test
    public void orderRegisterTest(){
        OrderDTO orderDTO = OrderDTO.builder()
                .odate(LocalDate.now().toString())
                .odelidate(LocalDate.now().toString())
                .ocode("O"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddmmssSS")))
                .ocount(40)
                .ostate("발주신청")
                .odetail("order register test 7")
                .ccode("C20230915T1P3M21TD0233")
                .pname("testpname3")
                .ptname("testptname3")
                .cno(1)
                .spno(1)
                .email("user01@naver.com")
                .build();
        log.info("====== orderDTO : "+orderDTO);
        int result = orderService.register(orderDTO);
        log.info("====== result : "+result);

    }

    @Test
    public void orderModifyTest(){
        OrderDTO orderDTO = OrderDTO.builder()
                .ono(2)
                .odetail("order modify test 2")
                .ostate("teststate2")
                .build();
        int result = orderService.modify(orderDTO);
        log.info("====== result : "+result);

    }

    @Test
    public void orderListTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("p")
                .keyword("name1")
                .build();
        PageResponseDTO<OrderDTO> responseDTO = orderService.orderList(pageRequestDTO, "ono");
        log.info(responseDTO);
    }

    @Test
    public void orderDetailTest(){
        int ono = 3;
        OrderDTO orderDTO = orderService.detail(ono);
        log.info("====== order detail : "+orderDTO);
    }




}
