package rml.dao;

import rml.model.Ware;
import rml.vo.WareVO;

import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public interface WareMapper {

    Ware getById(Long wareId);

    int insert(Ware ware);

    int update(Ware ware);

    Long getCount(WareVO wareVO);

    List<Ware> getList(WareVO wareVO);


}
