package ez.en.support.repository;

import ez.en.support.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

//    @Query(value = "select c.ptno, c.cprice, p.ptname from contract c join partner p on c.ptno = p.ptno" ,nativeQuery = true)
    @Query(value = "select c from Contract c join fetch c.product join fetch c.partner")
    List<Contract> selectAll();
}
