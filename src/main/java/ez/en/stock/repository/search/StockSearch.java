package ez.en.stock.repository.search;

import ez.en.stock.domain.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockSearch {

    Page<Stock> searchAll(String[] types, String keyword,Pageable pageable);
}
