package ez.en.order.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.dto.OrderDTO;
import ez.en.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public int register(OrderDTO orderDTO) {
        return 0;
    }

    @Override
    public int modify(OrderDTO orderDTO) {
        return 0;
    }

    @Override
    public PageResponseDTO<OrderDTO> orderList(PageRequestDTO pageRequestDTO, String sort) {
        return null;
    }

    @Override
    public OrderDTO detail(int ono) {
        return null;
    }
}
