package ez.en.stock.service;

import ez.en.stock.domain.Dailystock;
import ez.en.stock.repository.DailyStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class DailyStockServiceImpl implements DailyStockService{

    private final DailyStockRepository dailyStockRepository;

    @Override
    public List<Dailystock> listAll() {
        List<Dailystock> list = dailyStockRepository.listAll();

        return list;
    }
}
