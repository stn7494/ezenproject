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


}
