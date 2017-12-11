package rml.vo.Factory;

import rml.model.Trade;
import rml.util.DateUtil;
import rml.vo.TradeVO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/17.
 */
public class TradeFactory {
    public static final TradeFactory INSTANCE = new TradeFactory();

    private TradeFactory() {
    }

    public Trade convertFromTradeVO(TradeVO vo) {
        Trade trade = new Trade();
        trade.setId(vo.getId());
        trade.setVenderId(vo.getVenderId());
        trade.setSkuId(vo.getSkuId());
        trade.setWareId(vo.getWareId());
        trade.setNum(vo.getNum());
        trade.setRemark(vo.getRemark());
        trade.setStatus(vo.getStatus());
        trade.setMarketDay(DateUtil.strToDate(vo.getMarketDay(),DateUtil.TIME_FROMAT_DAY));
        return trade;
    }

    public TradeVO convertToTradeVO(Trade trade){
        TradeVO vo = new TradeVO();
        vo.setId(trade.getId());
        vo.setVenderId(trade.getVenderId());
        vo.setSkuId(trade.getSkuId());
        vo.setWareId(trade.getWareId());
        vo.setNum(trade.getNum());
        vo.setRemark(trade.getRemark());
        vo.setStatus(trade.getStatus());
        vo.setMarketDay(DateUtil.formatDate(trade.getMarketDay(),DateUtil.TIME_FROMAT_DAY));
        vo.setCreated(DateUtil.formatDate(trade.getCreated(),DateUtil.TIME_FORMAT_FULL));
        return vo;
    }

    public void sortBySize(List<TradeVO> list){
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                TradeVO u1 = (TradeVO) o1;
                TradeVO u2 = (TradeVO) o2;
                int i = u1.getSize().compareTo(u2.getSize());
                return i;
            }
        });
    }
}
