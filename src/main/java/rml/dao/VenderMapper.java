package rml.dao;

import rml.model.MUser;
import rml.model.Vender;
import rml.vo.VenderVO;

import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
public interface VenderMapper {

    Vender getById(Long id);

    Long getCount(VenderVO venderVO);

    List<Vender> getList(VenderVO venderVO);

    List<Vender> getAll();

    void add(Vender vender);
}
