package ez.en.order.repository;

import ez.en.order.domain.Orders;
import ez.en.order.repository.search.OrderSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer>, OrderSearch {
}
