package ez.en.support.repository;

import ez.en.support.domain.Product;
import ez.en.support.domain.Supportplan;
import ez.en.support.repository.search.SupportplanSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupportplanRepository extends JpaRepository<Supportplan, Integer>, SupportplanSearch {

    @Query(value = "select sp from Supportplan sp join fetch sp.product")
    List<Supportplan> joinList();


//    발주시 조달 계획 목록 팝업 메소드
    @EntityGraph(attributePaths = "product")
    @Query("select sp from Supportplan sp where sp.spstate=:spstate")
    List<Supportplan> popSplanList(String spstate);


}
