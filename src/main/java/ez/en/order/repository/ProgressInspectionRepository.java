package ez.en.order.repository;

import ez.en.order.domain.Progressinspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProgressInspectionRepository extends JpaRepository<Progressinspection, Integer> {

    @Query("select p from Progressinspection p where p.orders.ono=:ono")
    List<Progressinspection> popPiList(int ono);
}
