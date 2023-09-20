package ez.en.support.repository;

import ez.en.support.domain.Product;
import ez.en.support.repository.search.ProductSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer>, ProductSearch {

//    @Query("select p from Product p where p.pno =:pno")
//    Optional<Product> findById(int pno);

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

    @Query(value = "select p from Product p join fetch p.middle")
    List<Product> joinList();




}
