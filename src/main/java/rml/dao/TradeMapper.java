package rml.dao;

import rml.model.Trade;
import rml.model.Ware;
import rml.vo.TradeVO;
import rml.vo.WareVO;

import java.util.Date;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public interface TradeMapper {

    int insert(Trade trade);

    int deleteById(Long tradeId);

    Long getOrderNum(Date date);

    Long getVenderNum(Date date);

    Long getSkuCount(TradeVO tradeVO);

    List<Trade> getSkuList(TradeVO tradeVO);

    List<Trade> getVenderList(TradeVO tradeVO);

    /**
     * 商家维度SKU统计
     * @param tradeVO
     * @return
     */
    Long getVenderSkuCount(TradeVO tradeVO);

    /**
     * 商家维度SKU统计
     * @param tradeVO
     * @return
     */
    List<Trade> getVenderSkuList(TradeVO tradeVO);

    Long getCount(TradeVO tradeVO);

    List<Trade> getList(TradeVO tradeVO);
}
