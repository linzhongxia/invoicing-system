package rml.vo;

/**
 * Created by linzhongxia on 2017/10/11.
 */
public class VenderVO extends BaseVO {

    private Long venderId;
    private String name;
    private String qq;
    private String wx;
    private String telephone;
    private String modified;
    private String creaded;

    public Long getVenderId() {
        return venderId;
    }

    public void setVenderId(Long venderId) {
        this.venderId = venderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getCreaded() {
        return creaded;
    }

    public void setCreaded(String creaded) {
        this.creaded = creaded;
    }
}
