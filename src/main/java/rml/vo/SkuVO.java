package rml.vo;

import java.math.BigDecimal;

/**
 * Created by linzhongxia on 2017/10/15.
 */
public class SkuVO {
    private Long skuId;
    private String name;
    private String wareName;
    private Long wareId;
    private Long supplierId;
    private BigDecimal size;
    //颜色款式
    private String colour;
    private String[] img;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getName() {
        if (wareName != null && size != null && colour != null) {
            return wareName + " 【" + colour + "】" + " " + " 【" + size + "】";
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
