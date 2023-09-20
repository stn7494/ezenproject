package ez.en.stock.service;

import ez.en.stock.domain.Stock;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class StockServiceImpl implements StockService{

    private final ModelMapper modelMapper;

    private final StockRepository stockRepository;

    @Override
    public int register(StockDTO stockDTO) {

        Stock stock = modelMapper.map(stockDTO, Stock.class);

        int sno = stockRepository.save(stock).getSno();

        return sno;
    }
}
