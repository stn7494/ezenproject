package ez.en.support.repository;

import ez.en.support.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p from Product p join fetch p.middle")
    List<Product> joinList();




}
