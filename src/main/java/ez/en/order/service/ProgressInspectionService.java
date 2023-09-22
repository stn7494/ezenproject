package ez.en.order.service;


import ez.en.order.dto.OrderDTO;
import ez.en.order.dto.ProgressInspectionDTO;

import java.util.List;

public interface ProgressInspectionService {

    List<ProgressInspectionDTO> popPiList(int ono);

    int save(ProgressInspectionDTO progressInspectionDTO);



}
