package ez.en.kdj;

import ez.en.support.domain.Middle;
import ez.en.support.repository.MiddleRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class MiddleRepositoryTests {

    @Autowired
    private MiddleRepository middleRepository;

    @Test
    public void tcodetest() {
        List<Middle> list = middleRepository.tCategory("T01");
        log.info("조회결과 : " + list.get(0).getTop().getTcode());
    }
}
