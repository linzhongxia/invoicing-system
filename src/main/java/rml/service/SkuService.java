package rml.service;

import rml.vo.SkuVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public interface SkuService {

    boolean addSkuList(List<SkuVO> skuVOList);

    boolean updateWareName(Long wareId, String wareName, Long supplierId);

    boolean updateSkuList(List<SkuVO> skuVOList);

    SkuVO getSkuVO(long wareId, String colour, BigDecimal size);

    SkuVO getById(Long skuId);
}
