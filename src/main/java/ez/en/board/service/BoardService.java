package ez.en.board.service;

import ez.en.board.dto.BoardDTO;
import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;

public interface BoardService {

    int register(BoardDTO boardDTO);

    BoardDTO readOne(int bno);

    void modify(BoardDTO boardDTO);

    void remove(int bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
