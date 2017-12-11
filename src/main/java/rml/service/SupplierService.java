package rml.service;

import rml.model.Supplier;
import rml.model.Vender;
import rml.vo.SupplierVO;
import rml.vo.VenderVO;

import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
public interface SupplierService {

    long getCount(SupplierVO vo);

    List<SupplierVO> getList(SupplierVO vo);

    List<SupplierVO> getAllList();

    boolean add(Supplier supplier);

    boolean update(Supplier supplier);
}
