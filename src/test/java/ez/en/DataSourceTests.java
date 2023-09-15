package ez.en;

import ez.en.support.repository.SupportplanRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Log4j2
public class DataSourceTests {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private SupportplanRepository supportplanRepository;

    @Test
    public void testConnection() throws SQLException {
        Connection con = dataSource.getConnection();

        log.info(con);

        Assertions.assertNotNull(con);

    }
}
