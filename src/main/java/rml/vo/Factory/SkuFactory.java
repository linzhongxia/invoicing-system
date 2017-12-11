package rml.vo.Factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import rml.model.Sku;
import rml.vo.SkuVO;
import rml.vo.StyleVO;
import rml.vo.WareVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public class SkuFactory {

    public static final SkuFactory INSTANCE = new SkuFactory();

    private SkuFactory() {
    }

    public List<SkuVO> convertFromWareVO(WareVO wareVO) {
        List<SkuVO> skuVOList = new ArrayList<SkuVO>();

        for (Double size : wareVO.getSizes()) {
            for (StyleVO styleVO : wareVO.getStyleList()) {
                SkuVO vo = new SkuVO();
                vo.setName(new StringBuilder().append(wareVO.getName())
                        .append("【" + styleVO.getName() + "】")
                        .append("【" + size + "】").toString());
                vo.setColour(styleVO.getName());
                vo.setImg(styleVO.getImgurl());
                vo.setSize(new BigDecimal(size));
                vo.setWareId(wareVO.getWareId());
                vo.setWareName(wareVO.getName());
                vo.setSupplierId(wareVO.getSupplierId());

                skuVOList.add(vo);
            }
        }

        return skuVOList;
    }

    public Sku convertFromSkuVO(SkuVO skuVO) {
        Sku sku = new Sku();
        sku.setId(skuVO.getSkuId());
        sku.setWareId(skuVO.getWareId());
        sku.setWareName(skuVO.getWareName());
        sku.setSupplierId(skuVO.getSupplierId());
        sku.setSize(skuVO.getSize());
        sku.setColour(skuVO.getColour());
        if (!ArrayUtils.isEmpty(skuVO.getImg())) {
            sku.setImg(JSON.toJSONString(skuVO.getImg()));
        }

        return sku;
    }

    public SkuVO convertToSkuVO(Sku sku) {
        SkuVO vo = new SkuVO();
        vo.setSkuId(sku.getId());
        vo.setWareId(sku.getWareId());
        vo.setWareName(sku.getWareName());
        vo.setSupplierId(sku.getSupplierId());
        vo.setColour(sku.getColour());
        vo.setSize(sku.getSize());
        vo.setImg(getImgArray(sku.getImg()));
        return vo;
    }

    private String[] getImgArray(String imgStr) {
        if (StringUtils.isBlank(imgStr)) {
            return null;
        }
        JSONArray jsonArray = JSONArray.parseArray(imgStr);
        String[] imgArray = new String[jsonArray.size()];
        jsonArray.toArray(imgArray);
        return imgArray;
    }

}
