package rml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rml.service.TradeService;
import rml.util.DateUtil;
import rml.vo.TradeVO;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/18.
 */
@Controller
public class DashboardController {
    @Resource
    TradeService tradeService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView init() {

        Long orderNum = tradeService.getOrderNum(new Date());
        Long venderNum = tradeService.getVenderNum(new Date());

        TradeVO param = new TradeVO();
        param.setMarketDay(DateUtil.formatDate(new Date(), DateUtil.TIME_FROMAT_DAY));
        List<TradeVO> skuList = tradeService.getSkuList(param);
        List<TradeVO> venderList = tradeService.getVenderList(param);
        ModelAndView view = new ModelAndView("dashboard");
        view.addObject("orderNum", orderNum);
        view.addObject("venderNum", venderNum);
        view.addObject("skuList", skuList);
        view.addObject("venderList", venderList);
        return view;
    }


}
