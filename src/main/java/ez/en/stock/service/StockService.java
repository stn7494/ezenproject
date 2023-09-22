package ez.en.stock.service;

import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.dto.StockInDTO;
import ez.en.support.dto.ProductDTO;

import java.util.List;

public interface StockService {

    List<OrderDTO> getOrder();
    List<StockInDTO> getIn();
    void updateOstate(int ono);
    void insertIn(int ono, String email, String sidate, int pno,int sicount); //입고테이블 입력
    List<Integer> getPno();
    int getSicountAll(int pno); // 입고총량 가져오기
    void sicountAll(int pno,int sicountAll); // 입고총량 입력
    
    List<StockDTO> getStock(); // 자재리스트
}
