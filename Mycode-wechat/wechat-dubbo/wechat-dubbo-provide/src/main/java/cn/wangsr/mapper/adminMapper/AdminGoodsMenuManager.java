package cn.wangsr.mapper.adminMapper;

import cn.wangsr.adminOrderDo.AdminGoodsClassifyDo;
import cn.wangsr.adminOrderDo.AdminGoodsDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminGoodsMenuManager {

    /**
     * 获取商品分类
     * @return
     */
    List<AdminGoodsClassifyDo> getGoodsClassify();


    /**
     *  添加分类
     * @param adminGoodsClassifyDo
     * @return
     */
    int insertGoodsClassify(AdminGoodsClassifyDo adminGoodsClassifyDo);

    /**
     * 添加商品信息
     * @param adminGoodsDo
     * @return
     */
    int insertGoodsInfo(AdminGoodsDo adminGoodsDo);


    /**
     * 删除商品（下架商品）
     * @param goodsId
     * @return
     */
    int deleteGoodsById(@Param("goodsId") Integer goodsId);


}
