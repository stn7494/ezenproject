package ez.en.board.repository;

import ez.en.board.domain.Board;
import ez.en.board.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board,Integer> , BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
