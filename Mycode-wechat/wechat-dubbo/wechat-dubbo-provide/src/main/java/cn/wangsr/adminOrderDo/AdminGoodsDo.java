package cn.wangsr.adminOrderDo;

import java.io.Serializable;

public class AdminGoodsDo implements Serializable {

    private static final long serialVersionUID = 2320968813970948475L;
    private Integer id;
    private String goodsName;
    private String goodsImage;
    private String goodsPrice;
    private String goodsDescription;
    private String goodsTags;

    private Integer goodsItemId; //所属类别

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getGoodsTags() {
        return goodsTags;
    }

    public void setGoodsTags(String goodsTags) {
        this.goodsTags = goodsTags;
    }

    public Integer getGoodsItemId() {
        return goodsItemId;
    }

    public void setGoodsItemId(Integer goodsItemId) {
        this.goodsItemId = goodsItemId;
    }

    @Override
    public String toString() {
        return "GoodsDto [id=" + id + ", goodsName=" + goodsName + ", goodsImage=" + goodsImage + ", goodsPrice="
                + goodsPrice + ", goodsDescription=" + goodsDescription + ", goodsTags=" + goodsTags + ", goodsItemId="
                + goodsItemId + "]";
    }


}
