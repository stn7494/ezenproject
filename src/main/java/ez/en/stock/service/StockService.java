package ez.en.stock.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.dto.StockInDTO;

import java.util.List;

public interface StockService {

    PageResponseDTO<StockDTO> list(PageRequestDTO pageRequestDTO);

    List<OrderDTO> getOrder();
    List<StockInDTO> getIn();
    void updateOstate(int ono);
    void insertIn(int ono, String email, String sidate);
}
