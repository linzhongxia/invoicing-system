package rml.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public class Ware extends BasePO{

    private String name;
    private Long supplierId;
    private String sizes;
    private String colours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getColours() {
        return colours;
    }

    public void setColours(String colours) {
        this.colours = colours;
    }
}
