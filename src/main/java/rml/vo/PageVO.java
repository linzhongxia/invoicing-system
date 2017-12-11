package rml.vo;

/**
 * Created by linzhongxia on 2017/10/23.
 */
public class PageVO {

    private long index = 1;
    private long size = 20;
    private long totalNum = 0;
    private long totalIndex = 0;

    public void calculate(long index, long size, long totalNum) {
        this.index = index;
        this.size = size;
        this.totalNum = totalNum;
        this.totalIndex = totalNum % size == 0 ? totalNum / size : totalNum / size + 1L;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public long getTotalIndex() {
        return totalIndex;
    }

    public void setTotalIndex(long totalIndex) {
        this.totalIndex = totalIndex;
    }
}
