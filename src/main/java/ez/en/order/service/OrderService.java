package ez.en.order.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.domain.Order;
import ez.en.order.dto.OrderDTO;


public interface OrderService {

    int register(OrderDTO orderDTO);

    int modify(OrderDTO orderDTO);

    PageResponseDTO<OrderDTO> orderList(PageRequestDTO pageRequestDTO, String sort);

    OrderDTO detail(int ono);








    default OrderDTO entityToDTO(Order order){
        OrderDTO orderDTO = OrderDTO.builder()
                .ono(order.getOno())
                .ocode(order.getOcode())
                .ocount(order.getOcount())
                .odate(order.getOdate())
                .odelidate(order.getOdelidate())
                .odetail(order.getOdetail())
                .ostate(order.getOstate())
                .build();
        return orderDTO;
    }


    default Order dtoToEntity(OrderDTO orderDTO){
        Order order = Order.builder()
                .ono(orderDTO.getOno())
                .ocode(orderDTO.getOcode())
                .ocount(orderDTO.getOcount())
                .odate(orderDTO.getOdate())
                .odelidate(orderDTO.getOdelidate())
                .odetail(orderDTO.getOdetail())
                .ostate(orderDTO.getOstate())
                .build();
        return order;
    }










}
