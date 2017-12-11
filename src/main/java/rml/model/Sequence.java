package rml.model;

/**
 * Created by linzhongxia on 2017/10/14.
 */
public class Sequence {

    private Long id;
    private String name;
    private Long currentNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Long currentNum) {
        this.currentNum = currentNum;
    }
}
