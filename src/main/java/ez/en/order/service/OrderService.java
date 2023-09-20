package ez.en.order.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.dto.OrderDTO;
import ez.en.order.dto.PopContractDTO;
import ez.en.order.dto.PopSplanDTO;

import java.util.List;


public interface OrderService {

    int register(OrderDTO orderDTO);

    int modify(OrderDTO orderDTO);

    PageResponseDTO<OrderDTO> orderList(PageRequestDTO pageRequestDTO, String sort);

    OrderDTO detail(int ono);

    List<PopContractDTO> popContractList(String pcode);

    List<PopSplanDTO> popSplanList();











//    default OrderDTO entityToDTO(Orders orders){
//        OrderDTO orderDTO = OrderDTO.builder()
//                .ono(orders.getOno())
//                .ocode(orders.getOcode())
//                .ocount(orders.getOcount())
//                .odate(orders.getOdate())
//                .odelidate(orders.getOdelidate())
//                .odetail(orders.getOdetail())
//                .ostate(orders.getOstate())
//                .build();
//        return orderDTO;
//    }


//    default Orders dtoToEntity(OrderDTO orderDTO){
//        Orders orders = Orders.builder()
//                .ocode(orderDTO.getOcode())
//                .ocount(orderDTO.getOcount())
//                .odate(orderDTO.getOdate())
//                .odetail(orderDTO.getOdetail())
//                .ostate(orderDTO.getOstate())
//                .contract(orderDTO.getContract())
//                .supportplan(orderDTO.getSupportplan())
//                .login(orderDTO.getLogin())
//                .build();
//        return orders;
//    }










}
