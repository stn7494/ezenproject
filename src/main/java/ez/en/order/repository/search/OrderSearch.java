package ez.en.order.repository.search;

import ez.en.order.domain.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderSearch {

    Page<Orders> search(String[] types, String keyword, Pageable pageable);
}
