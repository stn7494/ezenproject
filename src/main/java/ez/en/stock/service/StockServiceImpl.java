package ez.en.stock.service;

import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.order.dto.PopContractDTO;
import ez.en.order.repository.OrderRepository;
import ez.en.stock.domain.Stock;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.repository.StockRepository;
import ez.en.support.domain.Contract;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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
    private final OrderRepository orderRepository;

    @Override
    public int register(StockDTO stockDTO) {

        Stock stock = modelMapper.map(stockDTO, Stock.class);

        int sno = stockRepository.save(stock).getSno();

        return sno;
    }

    @Override
    public List<OrderDTO> getOrder() {
        List<Orders> result = orderRepository.getOrder();
        List<OrderDTO> oList = result.stream()
                .map(i -> modelMapper.map(i,OrderDTO.class))
                .collect(Collectors.toList());

        return oList;
    }

//    public List<PopContractDTO> popContractList(String pcode) {
//        List<Contract> result = contractRepository.popContractList(pcode, "계약완료");
//        List<PopContractDTO> dtoList = result.stream()
//                .map(contract -> modelMapper.map(contract, PopContractDTO.class))
//                .collect(Collectors.toList());
//        return dtoList;
//    }
}
