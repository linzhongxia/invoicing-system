package rml.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rml.dao.WareMapper;
import rml.model.Ware;
import rml.service.WareService;
import rml.util.SeqEnum;
import rml.vo.Factory.WareFactory;
import rml.vo.WareVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
@Service("wareService")
public class WareServiceImpl extends BaseServiceImpl implements WareService {
    @Resource
    private WareMapper wareMapper;

    @Override
    public WareVO getById(Long wareId) {
        Ware ware = wareMapper.getById(wareId);
        if (ware == null) {
            return null;
        }
        return WareFactory.INSTANCE.convertToWareVO(ware);
    }

    @Override
    public long getCount(WareVO wareVO) {
        Long count = wareMapper.getCount(wareVO);
        if (count == null) {
            return 0;
        }
        return count;
    }

    @Override
    public List<WareVO> getList(WareVO wareVO) {

        List<Ware> wareList = wareMapper.getList(wareVO);
        if (CollectionUtils.isEmpty(wareList)) {
            return null;
        }
        List<WareVO> wareVOList = new ArrayList<WareVO>();
        for (Ware ware : wareList) {
            wareVOList.add(WareFactory.INSTANCE.convertToWareVO(ware));
        }
        return wareVOList;
    }

    @Override
    public boolean add(WareVO wareVO) {
        Long wareId = null;
        int tiems = 0;
        while (wareId == null) {
            if (tiems > 3) return false;
            wareId = getNextSequence(SeqEnum.WARE_ID.getKey());
            tiems++;
        }
        wareVO.setWareId(wareId);

        Ware ware = WareFactory.INSTANCE.convertFromWareVO(wareVO);
        wareMapper.insert(ware);

        return true;
    }

    @Override
    public boolean update(WareVO wareVO) {
        Ware ware = WareFactory.INSTANCE.convertFromWareVO(wareVO);
        wareMapper.update(ware);
        return true;
    }
}
