package ez.en.stock.repository;

import ez.en.stock.domain.Dailystock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyStockRepository extends JpaRepository<Dailystock, Integer> {

    List<Dailystock> findByDscodeOrderByDsDateAsc(String dsCode);

}
