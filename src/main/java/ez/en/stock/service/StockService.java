package ez.en.stock.service;

import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.stock.dto.StockDTO;

import java.util.List;

public interface StockService {

    int register(StockDTO stockDTO);

    List<OrderDTO> getOrder();
}
