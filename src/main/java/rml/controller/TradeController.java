package rml.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rml.service.SkuService;
import rml.service.SupplierService;
import rml.service.TradeService;
import rml.service.VenderService;
import rml.util.DateUtil;
import rml.vo.Factory.TradeFactory;
import rml.vo.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by linzhongxia on 2017/10/17.
 */
@Controller
@RequestMapping("/trade")
public class TradeController {
    @Resource
    TradeService tradeService;
    @Resource
    SkuService skuService;
    @Resource
    VenderService venderService;
    @Resource
    SupplierService supplierService;

    //预占管理
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView init(TradeVO tradeVO) {
        tradeVO.setMarketDay(tradeVO.getMarketDay() == null ? DateUtil.formatDate(new Date(), DateUtil.TIME_FROMAT_DAY) : tradeVO.getMarketDay());
        PageVO page = new PageVO();
        ModelAndView view = new ModelAndView("tradeList");
        long count = tradeService.getCount(tradeVO);
        if (count > 0) {
            List<TradeVO> tradeVOList = tradeService.getList(tradeVO);
            view.addObject("tradeVOList", tradeVOList);
        }
        page.calculate(tradeVO.getPage(), tradeVO.getPageSize(), count);
        view.addObject("venderList", venderService.getAllList());
        view.addObject("supplierList", supplierService.getAllList());
        view.addObject("page", page);
        view.addObject("tradeVO", tradeVO);
        return view;
    }

    @RequestMapping(value = "/in/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView inInit(TradeVO param) {
        param.setMarketDay(param.getMarketDay() == null ? DateUtil.formatDate(new Date(), DateUtil.TIME_FROMAT_DAY) : param.getMarketDay());
        PageVO page = new PageVO();
        ModelAndView view = new ModelAndView("tradeInList");
        long count = tradeService.getSkuCount(param);
        if (count > 0) {
            List<TradeVO> tradeVOList = tradeService.getSkuList(param);
            view.addObject("tradeVOList", tradeVOList);
        }
        page.calculate(param.getPage(), param.getPageSize(), count);
        view.addObject("supplierList", supplierService.getAllList());
        view.addObject("page", page);
        view.addObject("tradeVO", param);
        return view;
    }


    @RequestMapping(value = "/in/report", method = {RequestMethod.GET})
    public void inReport(TradeVO param, HttpServletResponse response) {
        param.setMarketDay(param.getMarketDay() == null ? DateUtil.formatDate(new Date(), DateUtil.TIME_FROMAT_DAY) : param.getMarketDay());
        OutputStream out = null;
        FileInputStream inputStream = null;
        try {
            Workbook fbytes = tradeService.inReportExcel(param);
            ;
            String downloadFileName = param.getMarketDay() + "提货单";
            out = response.getOutputStream();
            response.setHeader("Content-Type", "charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(downloadFileName + ".xlsx", "UTF-8"));
            response.setContentType("application/msexcel;charset=UTF-8");
            fbytes.write(out);
        } catch (Exception e) {
            throw new RuntimeException("下载文件异常", e);
        }
    }

    @RequestMapping(value = "/in/print", method = {RequestMethod.GET})
    public ModelAndView inReport(TradeVO param) {
        param.setMarketDay(param.getMarketDay() == null ? DateUtil.formatDate(new Date(), DateUtil.TIME_FROMAT_DAY) : param.getMarketDay());
        Map<String, List<TradeVO>> reportDate = tradeService.getInReportDate(param);
        Set<String> styleSet = reportDate.keySet();
        List<PrintVO> printList = new ArrayList<PrintVO>();
        if (!CollectionUtils.isEmpty(styleSet)) {
            for (String styleName : styleSet) {
                List<TradeVO> tradeVOList = reportDate.get(styleName);
                TradeVO tradeVO = tradeVOList.get(0);
                PrintVO print = new PrintVO();
                print.setName(styleName);
                print.setWareId(tradeVO.getWareId());
                print.setImg(tradeVO.getImg()[0]);
                print.setSupplier(String.valueOf(tradeVO.getSupplierId()));
                //size排序
                TradeFactory.INSTANCE.sortBySize(tradeVOList);
                StringBuffer detail = new StringBuffer();
                int index = 0;
                for (TradeVO sizeVO : tradeVOList) {
                    if (index % 3 == 0 && index != 0) detail.append("<br>");
                    detail.append("," + sizeVO.getNum() + "(" + sizeVO.getSize() + ")");
                    index++;
                }
                print.setDetail(detail.toString().substring(1));

                printList.add(print);
            }
        }
        ModelAndView view = new ModelAndView("printInList");
        view.addObject("printList", printList);
        return view;
    }


    @RequestMapping(value = "/out/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView outInit(TradeVO param) {
        param.setMarketDay(param.getMarketDay() == null ? DateUtil.formatDate(new Date(), DateUtil.TIME_FROMAT_DAY) : param.getMarketDay());
        PageVO page = new PageVO();
        ModelAndView view = new ModelAndView("tradeOutList");
        long count = tradeService.getVenderSkuCount(param);
        if (count > 0) {
            List<TradeVO> tradeVOList = tradeService.getVenderSkuList(param);
            view.addObject("tradeVOList", tradeVOList);
        }
        page.calculate(param.getPage(), param.getPageSize(), count);
        view.addObject("venderList", venderService.getAllList());
        view.addObject("supplierList", supplierService.getAllList());
        view.addObject("page", page);
        view.addObject("tradeVO", param);
        return view;
    }

    @RequestMapping(value = "/out/print", method = {RequestMethod.GET})
    public ModelAndView outReport(TradeVO param) {
        param.setMarketDay(param.getMarketDay() == null ? DateUtil.formatDate(new Date(), DateUtil.TIME_FROMAT_DAY) : param.getMarketDay());
        Map<String, List<TradeVO>> reportDate = tradeService.getOutReportDate(param);
        Set<String> styleSet = reportDate.keySet();
        List<PrintVO> printList = new ArrayList<PrintVO>();
        if (!CollectionUtils.isEmpty(styleSet)) {
            for (String styleName : styleSet) {
                List<TradeVO> tradeVOList = reportDate.get(styleName);
                TradeVO tradeVO = tradeVOList.get(0);
                PrintVO print = new PrintVO();
                print.setVender("[" + tradeVO.getVenderId() + "]" + tradeVO.getVenderName());
                print.setName(tradeVO.getWareName() + " 【" + tradeVO.getColour() + "】");
                print.setImg(tradeVO.getImg()[0]);
                print.setSupplier(String.valueOf(tradeVO.getSupplierId()));
                StringBuffer detail = new StringBuffer();
                //size排序
                TradeFactory.INSTANCE.sortBySize(tradeVOList);
                int index = 0;
                for (TradeVO sizeVO : tradeVOList) {
                    if (index % 3 == 0 && index != 0) detail.append("<br>");
                    detail.append("," + sizeVO.getNum() + "(" + sizeVO.getSize() + ")");
                    index++;
                }
                print.setDetail(detail.toString().substring(1));

                printList.add(print);
            }
        }
        ModelAndView view = new ModelAndView("printOutList");
        view.addObject("printList", printList);
        return view;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(TradeVO tradeVO) throws Exception {
        Result result = new Result();

        SkuVO skuVO = skuService.getSkuVO(tradeVO.getWareId(), tradeVO.getColour(), tradeVO.getSize());
        if (skuVO == null) {
            result.setSuccess(false);
            result.setMsg("没有匹配SKU添加预占");
            return result;
        }
        tradeVO.setSkuId(skuVO.getSkuId());
        tradeVO.setSupplierId(skuVO.getSupplierId());
        tradeVO.setMarketDay(DateUtil.formatDate(new Date(), DateUtil.TIME_FROMAT_DAY));
        //保存商品信息
        boolean addResult = tradeService.add(tradeVO);
        if (!addResult) {
            result.setSuccess(false);
            result.setMsg("保存预占信息出现异常");
            return result;
        }

        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(Long tradeId) throws Exception {
        Result result = new Result();
        boolean deleteResult = tradeService.deleteById(tradeId);
        if (!deleteResult) {
            result.setSuccess(false);
            result.setMsg("删除交易记录失败");
            return result;
        }
        result.setSuccess(true);
        return result;
    }


}
