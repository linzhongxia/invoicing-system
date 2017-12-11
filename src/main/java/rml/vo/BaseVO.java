package rml.vo;

import lombok.Data;

/**
 * Created by linzhongxia on 2017/10/11.
 */
public class BaseVO {

    private Integer page = 1;
    private Integer pageSize = 20;

    public Integer getIndexNum(){
        return (page-1)*pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
