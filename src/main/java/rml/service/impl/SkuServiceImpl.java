package rml.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rml.dao.SkuMapper;
import rml.model.Sku;
import rml.service.SkuService;
import rml.vo.Factory.SkuFactory;
import rml.vo.SkuVO;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService {
    @Resource
    private SkuMapper skuMapper;

    @Override
    public boolean addSkuList(List<SkuVO> skuVOList) {

        if (CollectionUtils.isEmpty(skuVOList)) {
            return false;
        }

        List<Sku> skuList = new ArrayList<Sku>();
        for (SkuVO skuVO : skuVOList) {
            skuList.add(SkuFactory.INSTANCE.convertFromSkuVO(skuVO));
        }
        skuMapper.insertByBatch(skuList);

        return true;
    }

    @Override
    public SkuVO getSkuVO(long wareId, String colour, BigDecimal size) {
        Sku param = new Sku();
        param.setColour(colour);
        param.setSize(size);
        param.setWareId(wareId);

        Sku sku = skuMapper.getByObject(param);
        if (sku == null) {
            return null;
        }
        return SkuFactory.INSTANCE.convertToSkuVO(sku);
    }

    @Override
    public SkuVO getById(Long skuId) {
        Sku sku = skuMapper.getById(skuId);
        if (sku == null) {
            return null;
        }
        return SkuFactory.INSTANCE.convertToSkuVO(sku);
    }

    @Override
    public boolean updateWareName(Long wareId, String wareName, Long supplierId) {

        Sku param = new Sku();
        param.setWareId(wareId);
        param.setWareName(wareName);
        param.setSupplierId(supplierId);

        skuMapper.updateWareName(param);
        return true;
    }

    @Override
    public boolean updateSkuList(List<SkuVO> skuVOList) {
        if (CollectionUtils.isEmpty(skuVOList)) {
            return false;
        }

        for (SkuVO skuVO : skuVOList) {
            Sku param = SkuFactory.INSTANCE.convertFromSkuVO(skuVO);
            SkuVO vo = this.getSkuVO(param.getWareId(), param.getColour(), param.getSize());
            if (vo == null) {
                skuMapper.insert(param);
            } else {
                param.setId(vo.getSkuId());
                skuMapper.update(param);
            }
        }

        return true;
    }
}
