package rml.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rml.dao.SupplierMapper;
import rml.model.Supplier;
import rml.service.SupplierService;
import rml.vo.Factory.SupplierFactory;
import rml.vo.SupplierVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhongxia on 2017/10/11.
 */
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
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
    public void add(Supplier supplier) {
        supplierMapper.add(supplier);
    }

    @Override
    public boolean update(Supplier supplier) {
        supplierMapper.update(supplier);
        return true;
    }
}
