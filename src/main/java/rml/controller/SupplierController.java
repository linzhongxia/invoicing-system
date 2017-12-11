package rml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rml.model.Supplier;
import rml.model.Vender;
import rml.service.SupplierService;
import rml.service.VenderService;
import rml.vo.Factory.SupplierFactory;
import rml.vo.Factory.VenderFactory;
import rml.vo.PageVO;
import rml.vo.Result;
import rml.vo.SupplierVO;
import rml.vo.VenderVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Resource
    private SupplierService supplierService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView init(SupplierVO supplierVO) {
        PageVO page = new PageVO();
        ModelAndView view = new ModelAndView("supplierList");
        long count = supplierService.getCount(supplierVO);
        if (count > 0) {
            List<SupplierVO> supplierList = supplierService.getAllList();
            view.addObject("supplierList", supplierList);
        }
        page.calculate(supplierVO.getPage(), supplierVO.getPageSize(), count);
        view.addObject("page", page);
        view.addObject("supplierVO", supplierVO);
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(SupplierVO supplierVO) {
        Supplier supplier = SupplierFactory.INSTANCE.convertFromSupplierVO(supplierVO);
        supplierService.add(supplier);
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/edite", method = RequestMethod.POST)
    @ResponseBody
    public Result edite(SupplierVO supplierVO) throws Exception {
        Supplier supplier = SupplierFactory.INSTANCE.convertFromSupplierVO(supplierVO);
        if(!supplierService.update(supplier)){
            throw new Exception("更新供货商信息出现异常");
        }
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
}
