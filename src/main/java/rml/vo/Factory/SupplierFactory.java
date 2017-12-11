package rml.vo.Factory;

import rml.model.Supplier;
import rml.model.Vender;
import rml.vo.SupplierVO;
import rml.vo.VenderVO;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public class SupplierFactory {

    public static final SupplierFactory INSTANCE = new SupplierFactory();

    private SupplierFactory() {
    }

    public Supplier convertFromSupplierVO(SupplierVO vo){
        Supplier supplier = new Supplier();
        supplier.setId(vo.getSupplierId());
        supplier.setName(vo.getName());

        return supplier;
    }

    public SupplierVO convertToSupplierVO(Supplier supplier){
        SupplierVO vo = new SupplierVO();
        vo.setSupplierId(supplier.getId());
        vo.setName(supplier.getName());

        return vo;
    }
}
