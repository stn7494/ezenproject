package ez.en.order.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;


public interface OrderService {

    int register(OrderDTO orderDTO);

    int modify(OrderDTO orderDTO);

    PageResponseDTO<OrderDTO> orderList(PageRequestDTO pageRequestDTO, String sort);

    OrderDTO detail(int ono);








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
//                .ono(orderDTO.getOno())
//                .ocode(orderDTO.getOcode())
//                .ocount(orderDTO.getOcount())
//                .odate(orderDTO.getOdate())
//                .odelidate(orderDTO.getOdelidate())
//                .odetail(orderDTO.getOdetail())
//                .ostate(orderDTO.getOstate())
//                .build();
//        return orders;
//    }










}
