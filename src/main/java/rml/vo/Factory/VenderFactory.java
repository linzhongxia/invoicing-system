package rml.vo.Factory;

import rml.model.Vender;
import rml.vo.VenderVO;

/**
 * Created by linzhongxia on 2017/10/13.
 */
public class VenderFactory {

    public static final VenderFactory INSTANCE = new VenderFactory();

    private VenderFactory() {
    }

    public Vender convertFromVenderVO(VenderVO vo){
        Vender vender = new Vender();
        vender.setId(vo.getVenderId());
        vender.setName(vo.getName());
        vender.setQq(vo.getQq());
        vender.setWx(vo.getWx());
        vender.setTelephone(vo.getTelephone());

        return vender;
    }

    public VenderVO convertToVenderVO(Vender vender){
        VenderVO vo = new VenderVO();
        vo.setVenderId(vender.getId());
        vo.setName(vender.getName());
        vo.setQq(vender.getQq());
        vo.setWx(vender.getWx());
        vo.setTelephone(vender.getTelephone());

        return vo;
    }
}
