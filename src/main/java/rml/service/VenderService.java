package rml.service;

import rml.model.Vender;
import rml.vo.VenderVO;

import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
public interface VenderService {

    VenderVO getById(Long id);

    long getCount(VenderVO vo);

    List<VenderVO> getList(VenderVO vo);

    List<VenderVO> getAllList();

    void add(Vender vender);
}
