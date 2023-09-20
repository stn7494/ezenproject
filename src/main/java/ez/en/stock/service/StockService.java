package ez.en.stock.service;

import ez.en.config.PageRequestDTO;
import ez.en.config.PageResponseDTO;
import ez.en.stock.dto.StockDTO;

public interface StockService {

    int register(StockDTO stockDTO);

    PageResponseDTO<StockDTO> list(PageRequestDTO pageRequestDTO);
}
