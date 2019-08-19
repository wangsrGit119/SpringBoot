package cn.wangsr.api.adminApi;

import cn.wangsr.adminOrderDto.AdminGoodsClassifyDto;
import cn.wangsr.adminOrderDto.AdminGoodsDto;

import java.util.List;

public interface GoodsMenuManagerApi {

    /**
     * 获取菜单分类
     * @return
     */
   List<AdminGoodsClassifyDto> getAllGoodsClassify();

    /**
     * 添加菜单分类
     * @param goodsClassifyDto
     * @return
     */
   String insertGoodsClassify(AdminGoodsClassifyDto goodsClassifyDto);


    /**
     * 添加商品
     * @param adminGoodsDto
     * @return
     */
   String insertGoodsInfo(AdminGoodsDto adminGoodsDto);

    /**
     * 根据id下架商品（删除商品）
     * @param goodsId
     * @return
     */
   String deleGoodsByGoodsId(Integer goodsId);

}
