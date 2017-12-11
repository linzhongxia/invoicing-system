package rml.dao;

import rml.model.Sku;
import rml.vo.SkuVO;

import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public interface SkuMapper {

    int insert(Sku sku);

    int insertByBatch(List<Sku> skuList);

    Sku getById(Long id);

    Sku getByObject(Sku param);

    int updateWareName(Sku param);

    int update(Sku sku);

}
