package ez.en.stock.service;

import ez.en.login.domain.Login;
import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.order.dto.PopContractDTO;
import ez.en.order.repository.OrderRepository;
import ez.en.stock.domain.Stock;
import ez.en.stock.domain.Stockin;
import ez.en.stock.domain.Stockout;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.dto.StockInDTO;
import ez.en.stock.repository.StockRepository;
import ez.en.stock.repository.StockinRepository;
import ez.en.stock.repository.StockoutRepository;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Product;
import ez.en.support.domain.Supportplan;
import ez.en.support.dto.ProductDTO;
import ez.en.support.repository.ProductRepository;
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

    private final StockoutRepository stockoutRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

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
    @Override // 입고테이블 등록
    public void insertIn(int ono, String email, String sidate, int pno, int sicount) {
        Stockin stockin = Stockin.builder()
                .order(Orders.builder().ono(ono).build())
                .login(Login.builder().email(email).build())
                .product(Product.builder().pno(pno).build())
                .sidate(sidate)
                .sicount(sicount)
                .build();
        stockinRepository.save(stockin);

    }
    @Override // 입고테이블 등록
    public void insertOut(int sno, String email, String sodate,int socount) {
        Stockout stockout = Stockout.builder()
                .stock(Stock.builder().sno(sno).build())
                .login(Login.builder().email(email).build())
                .sodate(sodate)
                .socount(socount)
                .build();
        stockoutRepository.save(stockout);

    }
    @Override
    public List<Integer> getPno() {
        List<Product> result = productRepository.findAll();
        List<Integer> pnoList = result.stream()
                .map(i -> modelMapper.map(i.getPno(),Integer.class))
                .collect(Collectors.toList());

        return pnoList;
    }

    @Override
    public int getSicountAll(int pno) {
        int all = stockinRepository.getSicountAll(pno);
        return all;
    }

    @Override
    public void sicountAll(int pno, int sicountAll) {

        stockRepository.sicountAll(pno,sicountAll);
    }

    @Override
    public List<StockDTO> getStock() {
        List<Stock> result = stockRepository.getStock();
        List<StockDTO> sList = result.stream()
                .map(i -> modelMapper.map(i,StockDTO.class))
                .collect(Collectors.toList());
        return sList;
    }
}
