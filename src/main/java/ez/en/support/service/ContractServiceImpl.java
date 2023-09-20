package ez.en.support.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Partner;
import ez.en.support.dto.ContractDTO;
import ez.en.support.dto.ContractPageRequestDTO;
import ez.en.support.dto.ContractPageResponseDTO;
import ez.en.support.repository.ContractRepository;
import ez.en.support.repository.search.ContractSearchImpl;
import ez.en.support.repository.search.PartnerSearchImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ContractServiceImpl implements ContractService {


    private final ContractRepository contractRepository;

    private final ContractSearchImpl contractSearch;

    private final PartnerSearchImpl partnerSearch;

    private final ModelMapper modelMapper;

    @Override
    public ContractPageResponseDTO<Contract> list(ContractPageRequestDTO pageRequestDTO) {

        String keyword = pageRequestDTO.getKeyword();

        String type = pageRequestDTO.getType();

        String state = pageRequestDTO.getState();

        Pageable pageable = pageRequestDTO.getPageable("cno");

        Page<Contract> result = contractSearch.search(keyword,type,state,pageable);

        List<Contract> list = result.getContent();
        return ContractPageResponseDTO.<Contract>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(list)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<Partner> list(PageRequestDTO pageRequestDTO) {

        String keyword = pageRequestDTO.getKeyword();

        String type = pageRequestDTO.getType();

        Pageable pageable = pageRequestDTO.getPageable("ptno");

        Page<Partner> result = partnerSearch.search(keyword, type, pageable);

        List<Partner> list = result.getContent();

        return PageResponseDTO.<Partner>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(list)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void insert(ContractDTO contractDTO) {

        Contract contract = modelMapper.map(contractDTO, Contract.class);

        contractRepository.save(contract);
    }

    @Override
    public ContractDTO selectOne(String ccode) {

        Contract contract = contractRepository.selectOne(ccode);

        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);

        return contractDTO;
    }

    @Override
    public void update(String cstate, String ccode) {

        Optional<Contract> result = contractRepository.findByCcode(ccode);

        Contract contract = result.orElseThrow();

        contract.update(cstate);

        contractRepository.save(contract);
    }
}
