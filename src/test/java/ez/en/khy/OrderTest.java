package ez.en.khy;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.login.domain.Login;
import ez.en.order.domain.Orders;
import ez.en.order.domain.Progressinspection;
import ez.en.order.dto.OrderDTO;
import ez.en.order.dto.ProgressInspectionDTO;
import ez.en.order.repository.OrderRepository;
import ez.en.order.repository.ProgressInspectionRepository;
import ez.en.order.service.OrderService;
import ez.en.order.service.ProgressInspectionService;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Supportplan;
import ez.en.support.repository.ContractRepository;
import ez.en.support.repository.SupportplanRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    SupportplanRepository supportplanRepository;

    @Autowired
    ProgressInspectionService progressInspectionService;

    @Autowired
    ProgressInspectionRepository progressInspectionRepository;


    @Test
    public void orderRegisterTest(){
        OrderDTO orderDTO = OrderDTO.builder()
                .odate(LocalDate.now().toString())
                .ocode("O"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddmmssSS")))
                .ocount(60)
                .ostate("발주신청")
                .odetail("order register test 13")
                .contract(Contract.builder().cno(9).build())
                .supportplan(Supportplan.builder().spno(2).build())
                .login(Login.builder().email("user03@naver.com").build())
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
        int ono = 10;
        OrderDTO orderDTO = orderService.detail(ono);

        log.info("====== order detail : "+orderDTO);
    }

    @Test
    public void popContractListTest(){
        String pcode = "P20230914T01M01CP01";
        List<Contract> list = contractRepository.popContractList(pcode, "계약완료");
        Contract contract = list.get(0);
        log.info(list);
        log.info(contract.getPartner());
    }

    @Test
    public void popSplanListTest(){
        List<Supportplan> list = supportplanRepository.popSplanList("조달요청");
        Supportplan supportplan = list.get(0);
        log.info(list);
        log.info(supportplan.getProduct());
    }

    @Test
    public void popContTest(){
        String pcode = "P20230914T01M01CP01";
        log.info(orderService.popContractList(pcode));
    }

    @Test
    public void popSpTest(){
        log.info(orderService.popSplanList());
    }

    @Test
    public void piSaveTest(){
        ProgressInspectionDTO progressInspectionDTO = ProgressInspectionDTO.builder()
                .pidate("2023-09-30")
                .pidetail("test 124213213")
                .pisequence(1)
                .piprogress(50)
                .email("admin")
                .ono(25)
                .build();
        log.info(progressInspectionDTO);
        log.info("save result pino : "+progressInspectionService.save(progressInspectionDTO));
    }

    @Test
    public void piFindTest(){
        Optional<Progressinspection> result = progressInspectionRepository.findById(1);
        Progressinspection progressinspection = result.orElseThrow();
        log.info(progressinspection);
    }

    @Transactional
    @Test
    public void popPiTest(){
        int ono = 25;
        List<ProgressInspectionDTO> dtoList = progressInspectionService.popPiList(ono);
        log.info(dtoList);
    }





}
