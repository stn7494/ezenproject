package ez.en.stock.repository;

import ez.en.stock.domain.Stockin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockinRepository extends JpaRepository<Stockin, Integer> {
}
