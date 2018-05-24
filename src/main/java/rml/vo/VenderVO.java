package rml.vo;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VenderVO venderVO = (VenderVO) o;
        return Objects.equals(venderId, venderVO.venderId) &&
                Objects.equals(name, venderVO.name) &&
                Objects.equals(qq, venderVO.qq) &&
                Objects.equals(wx, venderVO.wx) &&
                Objects.equals(telephone, venderVO.telephone) &&
                Objects.equals(modified, venderVO.modified) &&
                Objects.equals(creaded, venderVO.creaded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venderId, name, qq, wx, telephone, modified, creaded);
    }
}
