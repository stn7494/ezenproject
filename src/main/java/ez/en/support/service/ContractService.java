package ez.en.support.service;

import ez.en.support.domain.Contract;
import ez.en.support.dto.ContractPageRequestDTO;
import ez.en.support.dto.ContractPageResponseDTO;

public interface ContractService {

    ContractPageResponseDTO<Contract> list(ContractPageRequestDTO pageRequestDTO);

}
