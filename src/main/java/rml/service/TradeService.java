package rml.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import rml.vo.TradeVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by linzhongxia on 2017/10/17.
 */
public interface TradeService {

    boolean add(TradeVO tradeVO);

    boolean deleteById(Long tradeId);

    long getOrderNum(Date date);

    long getVenderNum(Date date);

    /**
     * SKU维度统计
     *
     * @param tradeVO
     * @return
     */
    long getSkuCount(TradeVO tradeVO);

    List<TradeVO> getSkuList(TradeVO tradeVO);

    /**
     * 提货单内容
     * @param tradeVO
     * @return
     */
    XSSFWorkbook inReportExcel(TradeVO tradeVO);

    Map<String, List<TradeVO>> getInReportDate(TradeVO param);

    List<TradeVO> getVenderList(TradeVO tradeVO);

    long getVenderSkuCount(TradeVO tradeVO);

    List<TradeVO> getVenderSkuList(TradeVO tradeVO);

    /**
     * 出货单打印内容
     * @param param
     * @return
     */
    Map<String, List<TradeVO>> getOutReportDate(TradeVO param);

    long getCount(TradeVO tradeVO);

    List<TradeVO> getList(TradeVO tradeVO);
}
