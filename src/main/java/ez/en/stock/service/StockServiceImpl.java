package ez.en.stock.service;

import ez.en.login.domain.Login;
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
import ez.en.support.domain.Supportplan;
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
        log.info("!!!!!!!!!!!!!!!!"+ono+email+sidate);
        Stockin stockin = Stockin.builder()
                .order(Orders.builder().ono(ono).build())
                .login(Login.builder().email(email).build())
                .sidate(sidate)
                .build();
        stockinRepository.save(stockin);

    }
}
