package ez.en.stock.repository;

import ez.en.stock.domain.Stock;
import ez.en.stock.domain.Stockout;
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

    @Modifying
    @Transactional
    @Query(value = "update Stock s set s.socountall = :socountAll where s.sno = :sno")
    void socountAll(int sno,int socountAll);

    @Query(value = "select s from Stock s")
    List<Stock> getStock();

    @Query(value = "select s from Stock s where s.sno = :sno")
    List<Stock> stockDetail(int sno);

    @Query(value = "select s.sno from Stock s where s.product.pno = :pno")
    Integer checkStock(int pno);
}
