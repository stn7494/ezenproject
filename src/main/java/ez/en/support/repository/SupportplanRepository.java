package ez.en.support.repository;

import ez.en.support.domain.Supportplan;
import ez.en.support.repository.search.SupportplanSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupportplanRepository extends JpaRepository<Supportplan, Integer>, SupportplanSearch {

    @Query(value = "select sp from Supportplan sp join fetch sp.product")
    List<Supportplan> joinList();



}
