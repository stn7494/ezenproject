package ez.en.order.service;

import ez.en.login.domain.Login;
import ez.en.order.domain.Orders;
import ez.en.order.domain.ProgressInspection;
import ez.en.order.dto.PopContractDTO;
import ez.en.order.dto.ProgressInspectionDTO;
import ez.en.order.repository.ProgressInspectionRepository;
import ez.en.support.domain.Contract;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ProgressInspectionServiceImpl implements ProgressInspectionService{

    private ProgressInspectionRepository progressInspectionRepository;
    private ModelMapper modelMapper;


    @Override
    public List<ProgressInspectionDTO> popPiList(int ono) {
        log.info("popPiList ono : "+ono);
        List<ProgressInspection> result = progressInspectionRepository.popPiList(ono);

        List<ProgressInspectionDTO> dtoList = result.stream()
                .map(progressInspection -> modelMapper.map(progressInspection, ProgressInspectionDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public int save(ProgressInspectionDTO progressInspectionDTO) {
        progressInspectionDTO.setOrders(Orders.builder().ono(progressInspectionDTO.getOno()).build());
        progressInspectionDTO.setLogin(Login.builder().email(progressInspectionDTO.getEmail()).build());
        if(progressInspectionDTO.getPino()!=0){
            Optional<ProgressInspection> result = progressInspectionRepository.findById(progressInspectionDTO.getPino());
            ProgressInspection progressInspection = result.orElseThrow();
            progressInspection.change(
                    progressInspectionDTO.getPidate(),
                    progressInspection.getPidetail(),
                    progressInspection.getPiprogress(),
                    progressInspection.getPicomplete(),
                    progressInspectionDTO.getLogin()
            );
            int ono = progressInspectionRepository.save(progressInspection).getOrders().getOno();
            return ono;
        }else {
            ProgressInspection progressInspection = modelMapper.map(progressInspectionDTO, ProgressInspection.class);
            int ono = progressInspectionRepository.save(progressInspection).getOrders().getOno();
            return ono;
        }
    }

}
