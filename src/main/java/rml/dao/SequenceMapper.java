package rml.dao;

import rml.model.Sequence;

/**
 * Created by linzhongxia on 2017/10/14.
 */
public interface SequenceMapper {

    Long getCurrentNum(Sequence sequence);

    int addCurrentNum(Sequence sequence);
}
