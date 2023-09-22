package ez.en.stock.repository;

import ez.en.order.domain.Orders;
import ez.en.stock.domain.Stockin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StockinRepository extends JpaRepository<Stockin, Integer> {
    @EntityGraph(attributePaths = {"order"})
    @Query(value = "select s from Stockin s where s.order.ostate = '입고완료'")
    List<Stockin> getIn();

    @Query(value= "select COALESCE(sum(s.sicount),0) from Stockin s where s.product.pno=:pno")
    int getSicountAll(int pno);
}
