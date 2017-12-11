package rml.service;

import rml.vo.WareVO;

import java.util.List;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public interface WareService {

    WareVO getById(Long wareId);

    long getCount(WareVO wareVO);

    List<WareVO> getList(WareVO wareVO);

    boolean add(WareVO wareVO);

    boolean update(WareVO wareVO);
}
