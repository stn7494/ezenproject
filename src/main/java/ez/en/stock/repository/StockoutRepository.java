package ez.en.stock.repository;

import ez.en.stock.domain.Stockout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockoutRepository extends JpaRepository<Stockout, Integer> {
}
