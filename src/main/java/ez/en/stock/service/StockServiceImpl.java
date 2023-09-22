package ez.en.stock.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.order.dto.PopContractDTO;
import ez.en.order.repository.OrderRepository;
import ez.en.stock.domain.Stock;
import ez.en.stock.domain.Stockin;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.dto.StockInDTO;
import ez.en.stock.repository.StockRepository;
import ez.en.stock.repository.StockinRepository;
import ez.en.support.domain.Contract;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class StockServiceImpl implements StockService{

    private final ModelMapper modelMapper;

    private final StockRepository stockRepository;
    private final StockinRepository stockinRepository;
    private final OrderRepository orderRepository;


    @Override
    public List<OrderDTO> getOrder() {
        List<Orders> result = orderRepository.getOrder();
        List<OrderDTO> oList = result.stream()
                .map(i -> modelMapper.map(i,OrderDTO.class))
                .collect(Collectors.toList());

        return oList;
    }

    @Override
    public List<StockInDTO> getIn() {
        List<Stockin> result = stockinRepository.getIn();
        List<StockInDTO> inList = result.stream()
                .map(i -> modelMapper.map(i,StockInDTO.class))
                .collect(Collectors.toList());

        return inList;
    }

    @Override
    public void updateOstate(int ono) {
        orderRepository.updateOstate(ono);
    }

    @Override
    public void insertIn(int ono, String email, String sidate) {
//        StockIn stockin
//        stockRepository.save(ono,)
    }

    @Override
    public PageResponseDTO<StockDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("sno");

        Page<Stock> result = stockRepository.searchAll(types, keyword, pageable);

        List<StockDTO> dtoList = result.getContent().stream()
                .map(stock -> modelMapper.map(stock,StockDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<StockDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }
}
