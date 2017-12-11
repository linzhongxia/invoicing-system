package rml.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rml.dao.VenderMapper;
import rml.model.Vender;
import rml.service.VenderService;
import rml.util.SeqEnum;
import rml.vo.Factory.VenderFactory;
import rml.vo.VenderVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
@Service("venderService")
public class VenderServiceImpl extends BaseServiceImpl implements VenderService {
    @Resource
    private VenderMapper venderMapper;

    @Override
    public VenderVO getById(Long id) {
        Vender vender = venderMapper.getById(id);
        if (vender == null) {
            return null;
        }

        return VenderFactory.INSTANCE.convertToVenderVO(vender);
    }


    @Override
    public long getCount(VenderVO vo) {
        Long count = venderMapper.getCount(vo);
        if (count == null) {
            return 0;
        }
        return count;
    }

    @Override
    public List<VenderVO> getList(VenderVO vo) {
        List<Vender> venderList = venderMapper.getList(vo);
        if (CollectionUtils.isEmpty(venderList)) {
            return null;
        }
        List<VenderVO> list = new ArrayList<VenderVO>();
        for (Vender vender : venderList) {
            list.add(VenderFactory.INSTANCE.convertToVenderVO(vender));
        }
        return list;
    }


    @Override
    public List<VenderVO> getAllList() {
        List<Vender> venderList = venderMapper.getAll();
        if (CollectionUtils.isEmpty(venderList)) {
            return null;
        }
        List<VenderVO> list = new ArrayList<VenderVO>();
        for (Vender vender : venderList) {
            list.add(VenderFactory.INSTANCE.convertToVenderVO(vender));
        }
        return list;
    }

    @Override
    public boolean add(Vender vender) {
        Long venderId = null;
        int tiems = 0;
        while (venderId == null) {
            if (tiems > 3) return false;
            venderId = getNextSequence(SeqEnum.VENDER_ID.getKey());
            tiems++;
        }
        vender.setId(venderId);
        venderMapper.add(vender);
        return true;
    }

    @Override
    public boolean update(Vender vender) {
        venderMapper.update(vender);
        return true;
    }
}
