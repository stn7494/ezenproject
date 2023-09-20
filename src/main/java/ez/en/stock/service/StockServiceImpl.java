package ez.en.stock.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.stock.domain.Stock;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public PageResponseDTO<StockDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("sno");

        Page<Stock> result = stockRepository.searchAll(types, keyword, pageable);

        List<StockDTO> dtoList = result.getContent().stream().map(stock -> modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<StockDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }
}
