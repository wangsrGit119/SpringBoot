package cn.wangsr.adminOrderDo;

import java.io.Serializable;

public class AdminGoodsClassifyDo implements Serializable {


    private static final long serialVersionUID = 7163118346252411245L;
    private  Integer classifyId;
    private  String  classifyName;

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    @Override
    public String toString() {
        return "GoodsClassifyDto{" +
                "classifyId=" + classifyId +
                ", classifyName='" + classifyName + '\'' +
                '}';
    }
}
