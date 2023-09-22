package ez.en.order.service;

import ez.en.login.domain.Login;
import ez.en.order.domain.Orders;
import ez.en.order.domain.Progressinspection;
import ez.en.order.dto.ProgressInspectionDTO;
import ez.en.order.repository.ProgressInspectionRepository;
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

    private final ProgressInspectionRepository progressInspectionRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ProgressInspectionDTO> popPiList(int ono) {
        log.info("popPiList ono : "+ono);
        List<Progressinspection> result = progressInspectionRepository.popPiList(ono);
        List<ProgressInspectionDTO> dtoList = result.stream()
                .map(progressinspection -> modelMapper.map(progressinspection, ProgressInspectionDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public int save(ProgressInspectionDTO progressInspectionDTO) {
        progressInspectionDTO.setOrders(Orders.builder().ono(progressInspectionDTO.getOno()).build());
        progressInspectionDTO.setLogin(Login.builder().email(progressInspectionDTO.getEmail()).build());
        if(progressInspectionDTO.getPino()!=0){
            Optional<Progressinspection> result = progressInspectionRepository.findById(progressInspectionDTO.getPino());
            Progressinspection progressinspection = result.orElseThrow();
            progressinspection.change(
                    progressInspectionDTO.getPidate(),
                    progressInspectionDTO.getPidetail(),
                    progressInspectionDTO.getPiprogress(),
                    progressInspectionDTO.getPicomplete(),
                    progressInspectionDTO.getLogin()
            );
            int pino = progressInspectionRepository.save(progressinspection).getPino();
            return pino;
        }else {
            log.info("save DTO: "+progressInspectionDTO);
            Progressinspection progressinspection = modelMapper.map(progressInspectionDTO, Progressinspection.class);
            int pino = progressInspectionRepository.save(progressinspection).getPino();
            return pino;
        }
    }

}
