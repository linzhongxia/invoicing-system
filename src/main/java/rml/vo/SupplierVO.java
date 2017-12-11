package rml.vo;

/**
 * Created by linzhongxia on 2017/10/11.
 */
public class SupplierVO extends BaseVO {

    private Long supplierId;
    private String name;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
