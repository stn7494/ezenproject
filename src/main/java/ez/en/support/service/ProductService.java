package ez.en.support.service;

import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Product;
import ez.en.support.dto.ProductDTO;
import ez.en.support.dto.ProductListAllDTO;
import ez.en.support.dto.ProductPageRequestDTO;
import ez.en.support.dto.ProductPageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    default Product dtoToEntity(ProductDTO productDTO) {
        Product product = Product.builder()
                .pno(productDTO.getPno())
                .pcode(productDTO.getPcode())
                .pname(productDTO.getPname())
                .pcontent(productDTO.getPcontent())
                .pnote(productDTO.getPnote())
                .build();

                if(productDTO.getFileNames() != null) {
                    productDTO.getFileNames().forEach(fileName -> {
                        String[] arr = fileName.split("_");
                        product.addImage(arr[0], arr[1]);
                    });
                }
                return product;
    }

    default ProductDTO entityToDTO(Product product) {
        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .pcode(product.getPcode())
                .pname(product.getPname())
                .pcontent(product.getPcontent())
                .pnote(product.getPnote())
                .pdate(product.getPdate())
                .pmoddate(product.getPmoddate())
                .build();

                List<String> fileNames =
                        product.getImageSet().stream().sorted().map(productImage ->
                                productImage.getUuid()+"_"+productImage.getFileName()).collect(Collectors.toList());

                productDTO.setFileNames(fileNames);
                return productDTO;
    }

    //게시글의 이미지와 댓글의 숫자까지 처리
//    ProductPageResponseDTO<ProductListAllDTO> listWithAll(ProductPageRequestDTO productPageRequestDTO);
}
