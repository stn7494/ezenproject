package ez.en.stock.repository;

import ez.en.stock.domain.Stock;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.repository.search.StockSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer>, StockSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();


}
