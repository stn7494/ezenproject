package ez.en.support.service;

import ez.en.support.dto.ProductDTO;
import ez.en.support.dto.ProductPageRequestDTO;
import ez.en.support.dto.ProductPageResponseDTO;

public interface ProductService {

    // 제품 등록
    int productregister(ProductDTO productDTO);

    // 제품 상세보기
    ProductDTO productreadOne(int pno);

    // 제품 수정
    void productmodify(ProductDTO productDTO);

    // 제품 삭제
    void productremove(int pno);

    //페이징 : (list()라는 이름으로 목록/검색 기능을 선언함
    ProductPageResponseDTO<ProductDTO> list(ProductPageRequestDTO productPageRequestDTO);


}
