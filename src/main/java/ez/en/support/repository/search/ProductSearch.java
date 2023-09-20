package ez.en.support.repository.search;


import ez.en.support.domain.Product;
import ez.en.support.dto.ProductListAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// 단순히 페이지 처리기능만을 선언
public interface ProductSearch {
    Page<Product> productsearch1(Pageable pageable);

    Page<Product> productsearchAll(String[] types, String keyword, Pageable pageable);

    Page<ProductListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);

}
