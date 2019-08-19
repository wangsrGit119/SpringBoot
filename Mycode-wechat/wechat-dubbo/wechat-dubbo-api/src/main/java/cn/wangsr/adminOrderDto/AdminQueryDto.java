package cn.wangsr.adminOrderDto;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class AdminQueryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageNum;
    private int pageSize;
    private String keys;// 用户名关键字
    private Date startTime;// 开始时间
    private Date endTime;// 结束时间

    private Set<Byte> allStatus; //订单状态集合不能为空   0等待接单   1 接单 交易成功  2 拒单 交易失败

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Set<Byte> getAllStatus() {
        return allStatus;
    }

    public void setAllStatus(Set<Byte> allStatus) {
        this.allStatus = allStatus;
    }

    @Override
    public String toString() {
        return "AdminQueryDto{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", keys='" + keys + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", allStatus=" + allStatus +
                '}';
    }
}
