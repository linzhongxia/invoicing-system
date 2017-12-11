package rml.util;

/**
 * Created by linzhongxia on 2017/12/11.
 */
public enum SeqEnum {

    WARE_ID("WARE_ID"),
    VENDER_ID("VENDER_ID"),
    SUPPLIER_ID("SUPPLIER_ID");

    private String key;

    public String getKey() {
        return key;
    }

    SeqEnum(String key) {
        this.key = key;
    }
}
