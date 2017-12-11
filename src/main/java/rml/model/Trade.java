package rml.model;

import java.util.Date;

/**
 * Created by linzhongxia on 2017/10/17.
 */
public class Trade extends BasePO{

    private Long venderId;
    private Long skuId;
    private Long wareId;
    private Long supplierId;
    private Integer num;
    //交易日
    private Date marketDay;
    private String remark;

    public Long getVenderId() {
        return venderId;
    }

    public void setVenderId(Long venderId) {
        this.venderId = venderId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Date getMarketDay() {
        return marketDay;
    }

    public void setMarketDay(Date marketDay) {
        this.marketDay = marketDay;
    }
}
