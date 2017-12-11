package rml.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by linzhongxia on 2017/10/11.
 */
public class Vender extends BasePO{

    private String name;
    private String qq;
    private String wx;
    private String telephone;

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

}
