package ez.en.support.repository;

import ez.en.support.domain.Middle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MiddleRepository extends JpaRepository<Middle, String> {

    @Query(value = "select m from Middle m where m.top.tcode = :tcode")
    List<Middle> category(String tcode);



}
