package ez.en.order.repository;

import ez.en.order.domain.Orders;
import ez.en.order.repository.search.OrderSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface OrderRepository extends JpaRepository<Orders, Integer>, OrderSearch {

    @EntityGraph(attributePaths = {"contract"})
    @Query(value = "select o from Orders o where o.ostate = '검수완료'")
    List<Orders> getOrder();

    @Modifying
    @Transactional
    @Query(value = "update Orders o set o.ostate = '입고완료' where o.ono = :ono")
    void updateOstate(int ono);
}
