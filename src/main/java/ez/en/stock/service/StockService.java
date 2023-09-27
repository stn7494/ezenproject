package ez.en.stock.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.order.domain.Orders;
import ez.en.order.dto.OrderDTO;
import ez.en.stock.dto.StockDTO;
import ez.en.stock.dto.StockInDTO;
import ez.en.stock.dto.StockOutDTO;
import ez.en.support.dto.ProductDTO;

import java.util.List;

public interface StockService {

    PageResponseDTO<StockDTO> sList(PageRequestDTO pageRequestDTO); // 자재 리스트(페이징)
    PageResponseDTO<StockInDTO> inList(PageRequestDTO pageRequestDTO); // 입고 리스트(페이징)
    PageResponseDTO<StockOutDTO> outList(PageRequestDTO pageRequestDTO); // 출고 리스트(페이징)
    PageResponseDTO<OrderDTO> oList(PageRequestDTO pageRequestDTO); // 발주목록 리스트(페이징)
//    List<OrderDTO> getOrder();
    List<StockInDTO> getIn(); // 입고테이블 가져오기
    List<StockOutDTO> getOut(); // 출고테이블가져오기
    List<StockDTO> stockDetail(int sno); // 자재디테일
    List<OrderDTO> orderDetail(int ono); // 발주디테일
    void updateOstate(int ono);
    void insertIn(int ono, String email, String sidate, int pno,int sicount); //입고테이블 입력
    void insertOut(int sno, String email, String sodate, int socount); // 출고테이블 입력
    List<Integer> getPno();
    List<Integer> getSno();
    int getSicountAll(int pno); // 입고총량 가져오기
    int getSocountAll(int sno); // 출고총량 가져오기
    void sicountAll(int pno,int sicountAll); // 입고총량 입력
    void socountAll(int sno,int socountAll); // 출고총량 입력


}
