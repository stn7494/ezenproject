package ez.en.stock.repository;

import ez.en.stock.domain.Dailystock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailyStockRepository extends JpaRepository<Dailystock, Integer> {

    List<Dailystock> findByDscodeOrderByDsDateAsc(String dsCode);

    @Query(value = "select d from Dailystock d order by d.dsDate asc")
    List<Dailystock> listAll();

}
