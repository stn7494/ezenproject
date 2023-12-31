package ez.en.support.service;


import ez.en.support.domain.Product;
import ez.en.support.dto.ProductDTO;
import ez.en.support.dto.ProductPageRequestDTO;
import ez.en.support.dto.ProductPageResponseDTO;
import ez.en.support.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public int productregister(ProductDTO productDTO,MultipartFile file)throws Exception{
        //여기부터
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        if(!file.isEmpty()){
            file.transferTo(saveFile);
            productDTO.setFilename(fileName);
            productDTO.setFilepath("/files/"+fileName);
        }
        Product product = modelMapper.map(productDTO, Product.class); // 이건 엔티티로 바꿔주는애
        productRepository.save(product);
        //여기까지 파일업로드때매 추가함

        int pno = productRepository.save(product).getPno();
        return pno;
    }

    @Override
    public ProductDTO productreadOne(int pno) {
        
        Optional<Product> result = productRepository.findById(pno);
        Product product = result.orElseThrow();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    // 무엇을 수정할것인지는 Product.java에서 써줘야함
    @Override
    public void productmodify(ProductDTO productDTO, MultipartFile file) throws Exception {
//        log.info("내가 전송한 데이터 : " + productDTO);
//        log.info("내가 전송한 파일의 데이터 " + file.getOriginalFilename());

        Optional<Product> result = productRepository.findById(productDTO.getPno());
        Product product = result.orElseThrow();

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
             // 1. 파일을 수정하지 않을때
        if(file.getOriginalFilename().equals("")){
            productDTO.setFilename(productDTO.getFilename());
            productDTO.setFilepath(productDTO.getFilepath());

        }else if (file.getOriginalFilename() !=null){
            // 2. 원래 있는 파일에 새로운 파일로 수정할때
            File f = new File(projectPath+"/"+productDTO.getFilename());

            if (f.exists()){ // 파일이 존재하면
                f.delete();  // 파일 삭제
            }
            UUID uuid = UUID.randomUUID();
            String fileName = uuid+"_"+file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);
            productDTO.setFilename(fileName);
            productDTO.setFilepath("/files/" + fileName);

        }
        product.productchange(productDTO.getPname(), productDTO.getPcontent(), productDTO.getPnote(), productDTO.getFilename(), productDTO.getFilepath());
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

}
