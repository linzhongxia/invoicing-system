package rml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rml.model.Vender;
import rml.service.VenderService;
import rml.vo.Factory.VenderFactory;
import rml.vo.PageVO;
import rml.vo.Result;
import rml.vo.VenderVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
@Controller
@RequestMapping("/vender")
public class VenderController {
    @Resource
    private VenderService venderService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView init(VenderVO venderVO) {
        PageVO page = new PageVO();
        ModelAndView view = new ModelAndView("venderList");
        long count = venderService.getCount(venderVO);
        if (count > 0) {
            List<VenderVO> venderList = venderService.getList(venderVO);
            view.addObject("venderList", venderList);
        }
        page.calculate(venderVO.getPage(), venderVO.getPageSize(), count);
        view.addObject("page", page);
        view.addObject("venderVO", venderVO);
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(VenderVO venderVO) {
        Vender vender = VenderFactory.INSTANCE.convertFromVenderVO(venderVO);
        venderService.add(vender);
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
}
