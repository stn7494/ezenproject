package ez.en.support.service;


import com.querydsl.jpa.JPQLQuery;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Product;
import ez.en.support.domain.QProduct;
import ez.en.support.dto.ProductDTO;
import ez.en.support.dto.ProductListAllDTO;
import ez.en.support.dto.ProductPageRequestDTO;
import ez.en.support.dto.ProductPageResponseDTO;
import ez.en.support.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{
    //주입
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public int productregister(ProductDTO productDTO) {
//        Product product = modelMapper.map(productDTO, Product.class);
        Product product = dtoToEntity(productDTO);
        int pno = productRepository.save(product).getPno();
        return pno;
    }

    @Override
    public ProductDTO productreadOne(int pno) {
        
        // board_image까지 조인처리는 findByWithLmages()를 이용
        Optional<Product> result = productRepository.findByIdWithImages(pno);
        Product product = result.orElseThrow();
//        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        ProductDTO productDTO = entityToDTO(product);
        return productDTO;
    }

    // 무엇을 수정할것인지는 Product.java에서 써줘야함
    @Override
    public void productmodify(ProductDTO productDTO) {
        Optional<Product> result = productRepository.findById(productDTO.getPno());
        Product product = result.orElseThrow();
        product.productchange(productDTO.getPname(), productDTO.getPcontent(), productDTO.getPnote());

        //첨부파일의 처리
        product.clearImages();

        if (productDTO.getFileNames() != null){
            for (String fileName : productDTO.getFileNames()) {
                String[] arr = fileName.split("_");
                product.addImage(arr[0], arr[1]);
            }
        }
        productRepository.save(product);
    }

    @Override
    public void productremove(int pno) {
        productRepository.deleteById(pno);
    }

    @Override
    public ProductPageResponseDTO<ProductDTO> list(ProductPageRequestDTO productpageRequestDTO) {
        String[] types = productpageRequestDTO.getTypes();
        String keyword = productpageRequestDTO.getKeyword();
        Pageable pageable = productpageRequestDTO.getPageable("pno");

        Page<Product> result = productRepository.productsearchAll(types, keyword, pageable);

        List<ProductDTO> dtoList = result.getContent().stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());

        return ProductPageResponseDTO.<ProductDTO>withAll()
                .productPageRequestDTO(productpageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }









//    @Override
//    public ProductPageResponseDTO<ProductListAllDTO> listWithAll(ProductPageRequestDTO productPageRequestDTO){
//        String[] types = productPageRequestDTO.getTypes();
//        String keyword = productPageRequestDTO.getKeyword();
//        Pageable pageable = productPageRequestDTO.getPageable("pno");
//
//        Page<ProductListAllDTO> result = productRepository.productsearchAll(types, keyword, pageable);
//
//        return ProductPageResponseDTO.<ProductListAllDTO>withAll()
//                .productPageRequestDTO(productPageRequestDTO)
//                .dtoList(result.getContent())
//                .total((int)result.getTotalElements())
//                .build();
//
//    }
//
}
