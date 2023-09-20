package ez.en.shin;

import ez.en.support.domain.*;
import ez.en.support.dto.*;
import ez.en.support.repository.*;
import ez.en.support.service.ContractServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class SupportRepositoryTests {


    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractServiceImpl service;




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


}
