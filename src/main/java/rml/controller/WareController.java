package rml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rml.service.SkuService;
import rml.service.SupplierService;
import rml.service.VenderService;
import rml.service.WareService;
import rml.util.Constants;
import rml.vo.Factory.SkuFactory;
import rml.vo.PageVO;
import rml.vo.SkuVO;
import rml.vo.VenderVO;
import rml.vo.WareVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
@Controller
@RequestMapping("/ware")
public class WareController {
    @Resource
    private WareService wareService;
    @Resource
    private SkuService skuService;
    @Resource
    private VenderService venderService;
    @Resource
    private SupplierService supplierService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView init(WareVO wareVO) {
        PageVO page = new PageVO();
        ModelAndView view = new ModelAndView("wareList");
        long count = wareService.getCount(wareVO);
        if (count > 0) {
            List<WareVO> wareVOList = wareService.getList(wareVO);
            List<VenderVO> venderList = venderService.getAllList();
            view.addObject("wareVOList", wareVOList);
            view.addObject("venderList", venderList);
        }
        view.addObject("supplierList", supplierService.getAllList());
        page.calculate(wareVO.getPage(), wareVO.getPageSize(), count);
        view.addObject("page", page);
        view.addObject("wareVO", wareVO);
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addInit() {
        ModelAndView view = new ModelAndView("wareInfo");
        view.addObject("sizes", Constants.shoesSizes);
        view.addObject("supplierList", supplierService.getAllList());
        view.addObject("wareVO", new WareVO());
        return view;
    }

    @RequestMapping(value = "/edite", method = RequestMethod.GET)
    public ModelAndView edite(Long wareId) {
        ModelAndView view = new ModelAndView("wareInfo");
        WareVO wareVO = wareService.getById(wareId);
        view.addObject("sizes", Constants.shoesSizes);
        view.addObject("supplierList", supplierService.getAllList());
        view.addObject("wareVO", wareVO);
        return view;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(WareVO wareVO) throws Exception {
        wareVO.buildStyeList();
        if(CollectionUtils.isEmpty(wareVO.getStyleList())){
            throw new Exception("商品款式信息为空，请确认后提交！");
        }
        if (wareVO.getWareId() == null) {
            this.saveWareInfo(wareVO);
        } else {
            this.updateWareInfo(wareVO);
        }
        return this.init(new WareVO());
    }

    private void saveWareInfo(WareVO wareVO) throws Exception {
        //保存商品信息
        boolean addResult = wareService.add(wareVO);
        if (!addResult) {
            throw new Exception("保存商品信息出现异常");
        }
        //转换成SKU
        List<SkuVO> skuVOList = SkuFactory.INSTANCE.convertFromWareVO(wareVO);
        boolean addSkuVOResult = skuService.addSkuList(skuVOList);
        if (!addSkuVOResult) {
            throw new Exception("保存商品SKU信息出现异常");
        }
    }

    private void updateWareInfo(WareVO wareVO) throws Exception {
        //保存商品信息
        boolean updateResult = wareService.update(wareVO);
        if (!updateResult) {
            throw new Exception("更新商品信息出现异常");
        }
        //更新SKU信息中WARENAME
        updateResult = skuService.updateWareName(wareVO.getWareId(),wareVO.getName(),wareVO.getSupplierId());
        if (!updateResult) {
            throw new Exception("更新SKU商品名称出现异常");
        }
        //转换成SKU
        List<SkuVO> skuVOList = SkuFactory.INSTANCE.convertFromWareVO(wareVO);
        updateResult = skuService.updateSkuList(skuVOList);
        if (!updateResult) {
            throw new Exception("更新SKU信息出现异常");
        }
    }

}
