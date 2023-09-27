package ez.en.board.search;

import ez.en.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
