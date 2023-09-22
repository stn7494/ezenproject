package ez.en.order.repository;

import ez.en.order.domain.ProgressInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgressInspectionRepository extends JpaRepository<ProgressInspection, Integer> {

    @Query("select p from ProgressInspection p where p.orders.ono=:ono")
    List<ProgressInspection> popPiList(int ono);
}
