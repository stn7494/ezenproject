package ez.en.order.repository;

import ez.en.order.domain.ProgressInspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressInspectionRepository extends JpaRepository<ProgressInspection, Integer> {
}
