package ez.en.support.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.support.domain.Contract;
import ez.en.support.domain.Partner;
import ez.en.support.dto.ContractPageRequestDTO;
import ez.en.support.dto.ContractPageResponseDTO;

public interface ContractService {

    ContractPageResponseDTO<Contract> list(ContractPageRequestDTO pageRequestDTO);

    PageResponseDTO<Partner> list(PageRequestDTO pageRequestDTO);
}
