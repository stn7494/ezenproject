package ez.en.shin;

import ez.en.stock.domain.Dailystock;
import ez.en.stock.repository.DailyStockRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Log4j2
public class ChartRepositoryTests {

    @Autowired
    private DailyStockRepository repository;


    @Test
    public void chart(){
//        List<Dailystock> list = repository.findByDscodeOrderByDsDateAsc("P20230914T01M01CP01");

        LocalDate date = LocalDate.parse("2023-09-01");

        String last = date.withDayOfMonth(date.lengthOfMonth()).toString();


        List<Dailystock> list1 = repository.list("2023-09-01", last, "P20230914T01M01CP01");



        for(Dailystock dailystock: list1){
            log.info(dailystock);
            log.info(dailystock.getDsDate());
        }
    }



}
