package ez.en.stock.repository;

import ez.en.stock.domain.Stockin;
import ez.en.stock.domain.Stockout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockoutRepository extends JpaRepository<Stockout, Integer> {
    @Query(value = "select s from Stockout s")
    List<Stockout> getOut();
    @Query(value= "select COALESCE(sum(s.socount),0) from Stockout s where s.stock.sno=:sno") // 출고총량가져오기
    int getSocountAll(int sno);
}
