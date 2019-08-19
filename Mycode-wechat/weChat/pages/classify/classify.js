// pages/classify/classify.js

Page({

  /**
   * 页面的初始数据
   */
  data: {
    dataItems: [{ id: 1, name: '糕点类', selectedCount: 0 }, { id: 2, name: '米粉类', selectedCount: 0 }, { id: 3, name: '佳汤类', selectedCount: 0 }, { id: 4, name: '米饭类', selectedCount: 0 }, { id: 5, name: '佳肉类', selectedCount: 0}],
        currentItemId:1,
    foodList: [
      // { id: 1, goodsName: '草莓松饼', goodsPrice:20,goodsImage:'https://i.loli.net/2018/12/31/5c2a19eea0926.png'},
      // { id: 2, goodsName: '浇汁臭豆腐', goodsPrice: 30, goodsImage: 'https://i.loli.net/2018/12/31/5c2a1a1098dd2.png' }
      // , { id: 3, goodsName: '酸酸乳', goodsPrice: 30, goodsImage: 'https://i.loli.net/2018/12/31/5c2a1a1098dd2.png' }
    ],
    active: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成  优先执行接口数据
   */
  onReady: function () {
    const that=this;
    wx.request({
      url: 'http://115.159.127.105:8089/wechatApi/goodsItemsInfo',
      success:function(res){
        console.log(res)
        that.setData({
          dataItems: JSON.parse(res.data.data)
        });
      }
    });

   /** 初始显示列表 第一个 */
    wx.request({
      url: 'http://115.159.127.105:8089/wechatApi/goodsInfoByItemId?itemId=' + 1,
      success: function (res) {
        console.log(res)
        that.setData({
          foodList: JSON.parse(res.data.data)
        });
      }
    })



  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

/**
 *  点击栏目分类 加载当前分类所有商品信息
 */
  goodsItem:function(e){
    var that = this;
    const currentId = e.currentTarget.dataset.typeId;
    that.setData({
          currentItemId:currentId,
        });
    wx.request({
      url: 'http://115.159.127.105:8089/wechatApi/goodsInfoByItemId?itemId=' + currentId,
      success: function (res) {
        console.log(res);
        that.setData({
          foodList: JSON.parse(res.data.data),

        });
      }
    })


  },
   /**去商品详情页面 */
  toDetailInfoPage:function(e){
    console.log(e);
    const goodsDetailInfo = e.currentTarget.dataset.goodsDetailInfo;
    
    wx.navigateTo({
      url: '../../pages/goodsdetail/goodsdetail?goodsDetailInfo='+JSON.stringify(goodsDetailInfo),
    })
  }


})