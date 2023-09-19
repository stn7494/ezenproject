package ez.en.shin;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.*;
import ez.en.support.dto.ContractPageRequestDTO;
import ez.en.support.dto.ContractPageResponseDTO;
import ez.en.support.repository.*;
import ez.en.support.repository.search.ContractSearchImpl;
import ez.en.support.service.ContractServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
@Log4j2
public class SupportRepositoryTests {

    @Autowired
    private SupportplanRepository supportplanRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TopRepository topRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private MiddleRepository middleRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractSearchImpl search;

    @Autowired
    private ContractServiceImpl service;

    @Test
    public void insert(){

        Pageable pageable = PageRequest.of(0,10,Sort.by("cno").descending());

        Page<Contract> result = search.search(null,null, null ,pageable);

        List<Contract> list = result.getContent();

        log.info(list.get(0).getPartner());
        log.info(list.get(0).getProduct());

        ContractPageRequestDTO pageRequestDTO = ContractPageRequestDTO.builder()
                .keyword(null)
                .type(null)
                .build();
        ContractPageResponseDTO<Contract> responseDTO = search.list(pageRequestDTO);

        List<Contract> list1 = responseDTO.getDtoList();
        log.info(list1);



    }
    @Test
    public void list(){
        ContractPageRequestDTO pageRequestDTO = ContractPageRequestDTO.builder()
                .keyword(null)
                .type(null)
                .build();
        ContractPageResponseDTO<Contract> responseDTO = service.list(pageRequestDTO);
        List<Contract> list1 = responseDTO.getDtoList();
        log.info(list1.get(0).getPartner());
        log.info(list1.get(0).getProduct());

    }


}
