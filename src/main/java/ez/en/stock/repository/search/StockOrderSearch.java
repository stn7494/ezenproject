package ez.en.stock.repository.search;

import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.stock.domain.Stock;
import ez.en.stock.domain.Stockin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockOrderSearch {

    Page<Orders> searchOrder(String[] types, String keyword,Pageable pageable);

}
