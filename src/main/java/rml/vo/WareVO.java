package rml.vo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public class WareVO extends BaseVO {

    private Long wareId;
    private String name;
    private Long supplierId;
    private Double[] sizes;
    private List<StyleVO> styleList;
    private String[] imgs;
    private Integer status;
    private Date modified;
    private Date created;

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

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

    public Double[] getSizes() {
        return sizes;
    }

    public void setSizes(Double[] sizes) {
        this.sizes = sizes;
    }

    public void buildStyeList() {
        if (!CollectionUtils.isEmpty(styleList)) {
            List<StyleVO> list = new ArrayList<StyleVO>();
            for (StyleVO vo : styleList) {
                if (vo.getName() != null) {
                    list.add(vo);
                }
            }
            styleList = list;
        }
    }

    public List<StyleVO> getStyleList() {
        return styleList;
    }

    public void setStyleList(List<StyleVO> styleList) {
        this.styleList = styleList;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
