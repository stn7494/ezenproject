package ez.en.order.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.login.domain.Login;
import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.order.dto.PopContractDTO;
import ez.en.order.dto.PopSplanDTO;
import ez.en.order.repository.OrderRepository;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Supportplan;
import ez.en.support.repository.ContractRepository;
import ez.en.support.repository.SupportplanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ContractRepository contractRepository;
    private final SupportplanRepository supportplanRepository;
    private final ModelMapper modelMapper;

    @Override
    public int register(OrderDTO orderDTO) {
//        orderDTO.setContract(Contract.builder().cno(orderDTO.getCno()).build());
//        orderDTO.setSupportplan(Supportplan.builder().spno(orderDTO.getSpno()).build());
//        orderDTO.setLogin(Login.builder().email(orderDTO.getEmail().toString()).build());

//        OrderDTO orderDTO1 = OrderDTO.builder()
//                .odate(orderDTO.getOdate())
//                .ocode(orderDTO.getOcode())
//                .ocount(orderDTO.getOcount())
//                .ostate(orderDTO.getOstate())
//                .odetail(orderDTO.getOdetail())
//                .contract(Contract.builder().cno(orderDTO.getCno()).build())
//                .supportplan(Supportplan.builder().spno(orderDTO.getSpno()).build())
//                .login(Login.builder().email(orderDTO.getEmail()).build())
//                .build();
//
//        orderDTO.setCno(0);
//        orderDTO.setSpno(0);
//        orderDTO.setEmail(null);
        log.info("=================="+orderDTO);
        Orders orders = modelMapper.map(orderDTO, Orders.class);
        log.info("service register orders : "+ orders);
        int ono = orderRepository.save(orders).getOno();
        return ono;


    }

    @Override
    public int modify(OrderDTO orderDTO) {
        Optional<Orders> result = orderRepository.findById(orderDTO.getOno());
        Orders orders = result.orElseThrow();
        orders.changeState(orderDTO.getOstate());
        orders.changeDetail(orderDTO.getOdetail());
        int ono = orderRepository.save(orders).getOno();
        return ono;
    }

    @Override
    public PageResponseDTO<OrderDTO> orderList(PageRequestDTO pageRequestDTO, String sort) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable(sort);
        Page<Orders> result = orderRepository.search(types, keyword, pageable);
        List<OrderDTO> dtoList = result.getContent().stream()
                .map(orders -> modelMapper.map(orders, OrderDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<OrderDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public OrderDTO detail(int ono) {
        Optional<Orders> result = orderRepository.findById(ono);
        Orders orders = result.orElseThrow();
        OrderDTO orderDTO = modelMapper.map(orders, OrderDTO.class);
        return orderDTO;
    }

    @Override
    public List<PopContractDTO> popContractList(String pcode) {
        List<Contract> result = contractRepository.popContractList(pcode, "계약완료");
        List<PopContractDTO> dtoList = result.stream()
                .map(contract -> modelMapper.map(contract, PopContractDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public List<PopSplanDTO> popSplanList() {
        List<Supportplan> result = supportplanRepository.popSplanList("조달요청");
        List<PopSplanDTO> dtoList = result.stream()
                .map(supportplan -> modelMapper.map(supportplan, PopSplanDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
}
