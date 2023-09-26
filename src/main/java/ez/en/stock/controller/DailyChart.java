package ez.en.stock.controller;

import ez.en.stock.domain.Dailystock;
import ez.en.stock.service.DailyStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class DailyChart {

    private final DailyStockService dailyStockService;

    @GetMapping("/")
    public ModelAndView getChartData() {

        ModelAndView mav = new ModelAndView();
        List<Dailystock> list = dailyStockService.listAll();
        List<Dailystock> list2 = new ArrayList<>();
        for (int i = list.size() - 6; i < list.size(); i++) {
            list2.add(list.get(i));
        }
        mav.addObject("list", list2);
        mav.setViewName("/index");

        return mav;
    }


}
