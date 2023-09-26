package ez.en.support.controller;

import ez.en.stock.domain.Dailystock;
import ez.en.stock.repository.DailyStockRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chart")
@Log4j2
public class ChartController {

    @Autowired
    DailyStockRepository repository;

    @GetMapping("/chart")
    public void chart(Model model){
        List<Dailystock> list = repository.findByDscodeOrderByDsDateAsc("P20230914T01M01CP01");
        List<Integer> dscount = new ArrayList<>();
        List<String> dsdate = new ArrayList<>();
        for (Dailystock dailystock:list
             ) {
            String date = dailystock.getDsDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            dscount.add(dailystock.getDscount());
            dsdate.add(date);
        }
        model.addAttribute("dscount",dscount);
        model.addAttribute("dsdate",dsdate);
    }

    @ResponseBody
    @PostMapping("/show")
    public Map<String, Object> list(@RequestParam("data")String data){

        LocalDate date = LocalDate.parse(data);

        String last = date.withDayOfMonth(date.lengthOfMonth()).toString();

        List<Dailystock> list = repository.list(data, last, "P20230914T01M01CP01");

        List<Integer> dsCount = new ArrayList<>();
        List<String> dsDate = new ArrayList<>();

        Map<String, Object>map = new HashMap<>();

        for (Dailystock dailystock:list){
            dsCount.add(dailystock.getDscount());
            dsDate.add(dailystock.getDsDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        map.put("dsDate", dsDate);
        map.put("dsCount", dsCount);
        return map;
    }

}
