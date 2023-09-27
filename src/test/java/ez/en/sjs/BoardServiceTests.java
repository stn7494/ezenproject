package ez.en.sjs;

import ez.en.board.dto.BoardDTO;
import ez.en.board.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        BoardDTO boardDTO = BoardDTO.builder()
                .title("추가 테스트")
                .content("추가 테스트트")
                .writer("user00")
                .build();

        int bno = boardService.register(boardDTO);

        log.info("bno : " + bno);

    }

    @Test
    public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(51)
                .title("수정 테스트 51")
                .content("수정 테스트트 51")
                .build();

        boardService.modify(boardDTO);

    }
}
