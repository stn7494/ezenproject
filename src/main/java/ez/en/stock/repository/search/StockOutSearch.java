package ez.en.stock.repository.search;

import ez.en.stock.domain.Stock;
import ez.en.stock.domain.Stockin;
import ez.en.stock.domain.Stockout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockOutSearch {

    Page<Stockout> searchOut(String[] types, String keyword,Pageable pageable);
}
