package rml.vo.Factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import rml.model.Ware;
import rml.vo.StyleVO;
import rml.vo.WareVO;

import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public class WareFactory {

    public static final WareFactory INSTANCE = new WareFactory();

    private WareFactory() {
    }

    public Ware convertFromWareVO(WareVO vo) {
        Ware ware = new Ware();
        ware.setId(vo.getWareId());
        ware.setName(vo.getName());
        ware.setSupplierId(vo.getSupplierId());
        if (!ArrayUtils.isEmpty(vo.getSizes())) {
            String sizesStr = ArrayUtils.toString(vo.getSizes());
            ware.setSizes(sizesStr.substring(1, sizesStr.length() - 1));
        }
        if (!CollectionUtils.isEmpty(vo.getStyleList())) {
            ware.setColours(JSON.toJSONString(vo.getStyleList()));
        }

        return ware;
    }

    public WareVO convertToWareVO(Ware ware) {

        WareVO vo = new WareVO();
        vo.setWareId(ware.getId());
        vo.setName(ware.getName());
        vo.setSupplierId(ware.getSupplierId());
        vo.setSizes(getWareSizeArray(ware.getSizes()));
        vo.setStyleList(getWareStyleList(ware.getColours()));
        vo.setImgs(CollectionUtils.isEmpty(vo.getStyleList()) ? null : vo.getStyleList().get(0).getImgurl());
        vo.setStatus(ware.getStatus());
        vo.setModified(ware.getModified());
        vo.setCreated(ware.getCreated());

        return vo;
    }

    private Double[] getWareSizeArray(String sizes) {

        if (StringUtils.isBlank(sizes)) {
            return null;
        }
        String[] sizeStrArray = sizes.split(",");
        Double[] sizeDoubleArray = new Double[sizeStrArray.length];
        for (int i = 0; i < sizeStrArray.length; i++) {
            sizeDoubleArray[i] = Double.valueOf(sizeStrArray[i]);
        }
        return sizeDoubleArray;
    }

    private List<StyleVO> getWareStyleList(String colours) {

        if (StringUtils.isBlank(colours)) {
            return null;
        }
        return JSONObject.parseArray(colours, StyleVO.class);
    }

}
