package ez.en.support.repository;

import ez.en.support.domain.Contract;
import ez.en.support.domain.Partner;
import ez.en.support.dto.ContractDTO;
import ez.en.support.repository.search.ContractSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Integer>{

//    @Query(value = "select c.ptno, c.cprice, p.ptname from contract c join partner p on c.ptno = p.ptno" ,nativeQuery = true)

    @Query(value = "select c from Contract c join fetch c.product join fetch c.partner"
            ,countQuery = "select count(c) from Contract c")
    Page<Contract> list(Pageable pageable);

    @Query(value = "select c from Contract c join fetch c.product join fetch c.partner where  c.ccode like concat('%',:keyword,'%')and c.cstate = :state"
            ,countQuery = "select count(c) from Contract c")
    Page<Contract> ccodeSearch(String keyword, Pageable pageable, String state);

    @Query(value = "select c from Contract c join fetch c.product join fetch c.partner where  c.product.pcode like concat('%',:keyword,'%')and c.cstate = :state"
            ,countQuery = "select count(c) from Contract c")
    Page<Contract> pcodeSearch(String keyword, Pageable pageable, String state);

    @Query(value = "select c from Contract c join fetch c.product join fetch c.partner where  c.product.pname like concat('%',:keyword,'%')and c.cstate = :state"
            ,countQuery = "select count(c) from Contract c")
    Page<Contract> pnameSearch(String keyword, Pageable pageable, String state);

    @Query(value = "select c from Contract c join fetch c.product join fetch c.partner where  c.partner.ptname like concat('%',:keyword,'%')and c.cstate = :state"
            ,countQuery = "select count(c) from Contract c")
    Page<Contract> ptnameSearch(String keyword, Pageable pageable, String state);

    @Query(value = "select c from Contract c join fetch c.product join fetch c.partner where  c.cstate = :state"
            ,countQuery = "select count(c) from Contract c")
    Page<Contract> stateSearch(String state, Pageable pageable);

    @Query(value = "select c from Contract c join fetch c.product join fetch c.partner where  c.ccode = :keyword " +
            "or c.product.pcode = :keyword or c.product.pname = :keyword or c.partner.ptname = :keyword"
            ,countQuery = "select count(c) from Contract c")
    Page<Contract> keywordSearch(String keyword, Pageable pageable);



//    발주시 계약 목록 팝업 메소드
    @EntityGraph(attributePaths = {"product", "partner"})
    @Query("select c from Contract c  where c.product.pcode=:pcode and c.cstate=:cstate order by c.cprice")
    List<Contract> popContractList(String pcode, String cstate);

//    private int cno;
//    private String ccode;
//    private int cprice;
//    private String cstate;
//    private Partner partner;


}
