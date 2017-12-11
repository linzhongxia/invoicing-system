package rml.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rml.dao.SupplierMapper;
import rml.model.Supplier;
import rml.service.SupplierService;
import rml.util.SeqEnum;
import rml.vo.Factory.SupplierFactory;
import rml.vo.SupplierVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
@Service("supplierService")
public class SupplierServiceImpl extends BaseServiceImpl implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public long getCount(SupplierVO vo) {
        Long count = supplierMapper.getCount(vo);
        if (count == null) {
            return 0;
        }
        return count;
    }

    @Override
    public List<SupplierVO> getList(SupplierVO vo) {
        List<Supplier> supplierList = supplierMapper.getList(vo);
        if (CollectionUtils.isEmpty(supplierList)) {
            return null;
        }
        List<SupplierVO> list = new ArrayList<SupplierVO>();
        for (Supplier supplier : supplierList) {
            list.add(SupplierFactory.INSTANCE.convertToSupplierVO(supplier));
        }
        return list;
    }

    @Override
    public List<SupplierVO> getAllList() {
        List<Supplier> supplierList = supplierMapper.getAll();
        if (CollectionUtils.isEmpty(supplierList)) {
            return null;
        }
        List<SupplierVO> list = new ArrayList<SupplierVO>();
        for (Supplier supplier : supplierList) {
            list.add(SupplierFactory.INSTANCE.convertToSupplierVO(supplier));
        }
        return list;
    }

    @Override
    public boolean add(Supplier supplier) {
        Long supplierId = null;
        int tiems = 0;
        while (supplierId == null) {
            if (tiems > 3) return false;
            supplierId = getNextSequence(SeqEnum.SUPPLIER_ID.getKey());
            tiems++;
        }
        supplier.setId(supplierId);
        supplierMapper.add(supplier);
        return true;
    }

    @Override
    public boolean update(Supplier supplier) {
        supplierMapper.update(supplier);
        return true;
    }
}
