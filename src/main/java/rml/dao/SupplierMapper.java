package rml.dao;

import rml.model.Supplier;
import rml.model.Vender;
import rml.vo.SupplierVO;
import rml.vo.VenderVO;

import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
public interface SupplierMapper {

//    Vender getById(Long id);

    Long getCount(SupplierVO supplierVO);

    List<Supplier> getList(SupplierVO supplierVO);

    List<Supplier> getAll();

    void add(Supplier supplier);

    int update(Supplier supplier);
}
