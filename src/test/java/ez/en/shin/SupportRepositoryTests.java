package ez.en.shin;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.*;
import ez.en.support.dto.*;
import ez.en.support.repository.*;
import ez.en.support.service.ContractServiceImpl;
import ez.en.support.service.SupportPlanServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class SupportRepositoryTests {


    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private SupportplanRepository supportplanRepository;
    @Autowired
    private ContractServiceImpl service;

    @Autowired
    private SupportPlanServiceImpl supportPlanService;

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

    @Test
    public void selectOne(){

        Contract contract = contractRepository.selectOne("1");

        log.info(contract.getProduct());
        log.info(contract.getPartner());
        log.info(contract.getProduct().getMiddle().getMcode());

        Optional<Contract> result = contractRepository.findByCcode("1");
        Contract contract1 = result.orElseThrow();

        contract1.update("계약종료");

        Contract contract2 = contractRepository.save(contract1);
        log.info(contract1);
        log.info(contract2);
    }

    @Test
    public void insert(){
        TopDTO topDTO = TopDTO.builder()
                .tname("데스크탑")
                .tcode("T01")
                .build();
        MiddleDTO dto = MiddleDTO.builder()
                .topDTO(topDTO)
                .mcode("M01")
                .mname("CPU")
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .pno(1)
                .pcode("P20230914T01M01CP01")
                .pname("CPU AMD 라이젠7-4세대 5800X")
                .build();

        SupportPlanDTO planDTO = SupportPlanDTO.builder()
                .middleDTO(dto)
                .productDTO(productDTO)
                .spcount(50)
                .spdelidate("2023-09-21")
                .spdate("2023-09-30")
                .spstate("조달요청")
                .build();

        supportPlanService.insert(planDTO);
    }

    @Test
    public void select(){
        Supportplan supportplan = supportplanRepository.selectOne(4);
        log.info(supportplan);

        SupportPlanDTO planDTO = supportPlanService.selectOne(4);
        log.info(planDTO);
    }

    @Test
    public void supportlist(){
        Pageable pageable = PageRequest.of(0,2, Sort.by("spno").descending());

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("a")
                .keyword("라")
                .sort("spno")
                .build();

        PageResponseDTO<Supportplan> list = supportPlanService.list(pageRequestDTO);

        List<Supportplan>list2 = list.getDtoList();

        log.info(list2);
    }

}
