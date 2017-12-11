package rml.service.impl;

import rml.dao.SequenceMapper;
import rml.model.Sequence;
import rml.service.BaseService;

import javax.annotation.Resource;

/**
 * Created by linzhongxia on 2017/10/14.
 */
public class BaseServiceImpl implements BaseService{
    @Resource
    private SequenceMapper sequenceMapper;
    @Override
    public Long getNextSequence(String key) {
        Sequence sequence = new Sequence();
        sequence.setName(key);
        Long currentNum = sequenceMapper.getCurrentNum(sequence);
        sequence.setCurrentNum(currentNum);
        if(sequenceMapper.addCurrentNum(sequence) == 1){
            return currentNum+1L;
        }
        return null;
    }
}
