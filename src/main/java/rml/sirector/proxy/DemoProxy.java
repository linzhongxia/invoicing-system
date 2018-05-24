package rml.sirector.proxy;

import com.jd.m.sirector.wrapper.event.EventProxy;
import rml.service.SupplierService;
import rml.service.VenderService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class DemoProxy implements EventProxy {

    @Resource
    VenderService venderService;
    @Resource
    SupplierService supplierService;

    public Object getVenderList(Object object) {
        System.err.println(Thread.currentThread().getName()+ " getVenderList 进来了");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return venderService.getAllList();
    }

    public Object getSupplierList(Object object) {
        System.err.println(Thread.currentThread().getName()+ " getSupplierList 进来了");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return supplierService.getAllList();

    }

    public Object mergeResult(Object object, Object[] objects) {
        System.err.println(Thread.currentThread().getName()+"开始聚合了");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("venderList",objects[0]);
        map.put("supplierList",objects[1]);

        return map;
    }

}
