package ez.en.order.repository;

import ez.en.order.domain.Orders;
import ez.en.order.repository.search.OrderSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Orders, Integer>, OrderSearch {

}
