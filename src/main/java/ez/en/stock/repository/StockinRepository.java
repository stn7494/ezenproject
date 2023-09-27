package ez.en.stock.repository;

import ez.en.order.domain.Orders;
import ez.en.stock.domain.Stockin;
import ez.en.stock.repository.search.StockInSearch;
import ez.en.stock.repository.search.StockSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StockinRepository extends JpaRepository<Stockin, Integer>, StockInSearch {

    @Query(value = "select s from Stockin s")
    List<Stockin> getIn();

    @Query(value= "select COALESCE(sum(s.sicount),0) from Stockin s where s.product.pno=:pno") //입고총량가져오기
    int getSicountAll(int pno);
}
