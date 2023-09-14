package ez.en.order.repository;

import ez.en.order.domain.Orderstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderstateRepository extends JpaRepository<Orderstate, String> {
}
