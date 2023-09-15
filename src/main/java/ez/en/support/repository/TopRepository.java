package ez.en.support.repository;

import ez.en.support.domain.Top;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopRepository extends JpaRepository<Top, String> {

    @Query(value = "select * from top where tcode = :keyword", nativeQuery = true)
    Top selectOne(String keyword);
}
