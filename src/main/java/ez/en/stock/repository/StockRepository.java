package ez.en.stock.repository;

import ez.en.stock.domain.Stock;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.repository.search.StockSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer>, StockSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

    @Modifying
    @Transactional
    @Query(value = "update Stock s set s.sicountall = :sicountAll where s.product.pno = :pno")
    void sicountAll(int pno,int sicountAll);
    @EntityGraph(attributePaths = {"contract"})
    @Query(value = "select s from Stock s")
    List<Stock> getStock();
}
