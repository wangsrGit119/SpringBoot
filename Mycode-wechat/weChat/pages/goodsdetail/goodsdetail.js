// pages/goodsdetail/goodsdetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodsDetailInfo:null , //商品信息
    goodsResultAmount:0,  //初始结算总价
    goodsCount:1 , //商品数量
    personalValify:null //个人唯一信息
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const that=this;
    const  detailInfo = JSON.parse(options.goodsDetailInfo);
    that.setData({
      goodsDetailInfo: detailInfo,
      goodsResultAmount: detailInfo.goodsPrice*100  //初始化默认数量商品计价
    });

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    const that=this;
    wx.getStorage({
      key: 'onlyId',
      success: function (res) {
        that.setData({
          personalValify: res.data
        });
      },
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

  /**  添加商品 往购物车 */
  addItems: function (e) {

    var that = this;
    const goodsInfo = that.data.goodsDetailInfo;
    const goodsNum = that.data.goodsCount;
    wx.showModal({
      content: '确认加入购物车？',
      success: function (res) {
        if (res.confirm) {
           //确认加入后执行储存数据
          console.log("开始写入数据库");
          var myCartInfo = { onlyId: that.data.personalValify, goodsId: goodsInfo.id, goodsCount: goodsNum };
          console.log(myCartInfo)
          wx.request({
            url: 'http://115.159.127.105:8089/wechatApi/addInfoInMyCart',
          method: 'post',
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          dataType: 'json',
          data: {
            myCartInfo: JSON.stringify(myCartInfo)
          },
          success: function (res) {
            if(res.data.code==200){
              wx.showToast({
                icon:'success',
                title: '添加成功',
              })
              
            }
            console.log(res);
          }
        })

        }
        
     
      
        
       
      

      }
    })
   
  },
   /**商品选择数量加减 */
  onChangePlusorMinus(e){
      const that=this;
      let goodsInfo=e.currentTarget.dataset.goodsInfo;
      //渲染初始变量
      that.setData({
        goodsResultAmount: e.detail * goodsInfo.goodsPrice*100,
        goodsCount: e.detail
      });
  },
  /**返回菜单页面 */
  onClickLeft(e){
    wx.navigateBack({
    })
  }
})