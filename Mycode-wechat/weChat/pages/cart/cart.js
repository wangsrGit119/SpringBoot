// pages/cart/cart.js
import Toast from '../../components/dist/toast/toast';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // goodsResult: [{ id: 1, goodsName: '草莓松饼', goodsPrice: 20, goodsImage: 'https://i.loli.net/2018/12/31/5c2a19eea0926.png', count: 1 }, { id: 2, goodsName: '浇汁臭豆腐', goodsPrice: 60, goodsImage: 'https://i.loli.net/2018/12/31/5c2a1a1098dd2.png', count: 2 }],
    goodsResult:[],
    totalAmount:0,
    isChecked:false,  //是否全选
    onlyId:null , //唯一Id
    myOrderInfo:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    const that=this;
     
  },

  /**
   * 生命周期函数--监听页面显示  页面切换刷新接口
   */
  onShow: function () {
    const that=this;
    wx.getStorage({
      key: 'onlyId',
      success: function (res) {
        if (res.data == null) {
          wx.showToast({
            icon: 'loading',
            title: '用户未登录',
          })
        }else{
          that.getCartInfoByUserOnlyId(res.data);
        }
        that.setData({ onlyId: res.data });
      },
    })
    
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
   * 页面相关事件处理函数--监听用户下拉动作  刷新
   */
  onPullDownRefresh: function () {
    const that = this;
   
    that.getCartInfoByUserOnlyId(that.data.onlyId);
   
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
  /**单个进行选择 */
  goodsAccountChange:function(e){
    console.log(e);
    const that = this;
    let arrAccount = e.detail.value
    var resultTotal = 0;
    var parseArrayItem=null;
    // 获取多选框金额
    for (let i = 0; i < arrAccount.length; i++) {
       parseArrayItem = JSON.parse(arrAccount[i]);
      resultTotal = parseInt(resultTotal) + parseInt(parseArrayItem.goodsPrice * parseArrayItem.goodsCount)
    }
    that.setData({
      totalAmount: resultTotal*100,
      myOrderInfo: arrAccount
    });

  },
  /**  进行结算支付 */
  resultAccount: function (e) {
    const that=this;
    var orderDetail = that.data.myOrderInfo;
    var myOrderInfo = { orderTime: new Date(), orderDetail: orderDetail, userOnlyId: that.data.onlyId };
    if (myOrderInfo.orderDetail==null){
      Toast.fail('至少选择一项');
      return;
    }
    console.log(JSON.stringify(myOrderInfo));
    
    wx.showModal({
      cancelText:"取消",
      content: '请确认是否提交订单',
      success:function(res){
          if(res.confirm){
            // 添加订单接口调用
            wx.request({
              url: 'http://115.159.127.105:8089/wechatApi/addOrderInfo',
              method:'post',
              header:{
                "Content-Type": "application/x-www-form-urlencoded"
              },
              data: { orderInfo: JSON.stringify(myOrderInfo)},
              success:function(res){
                //结算完成订单从购物车消失  调用删除接口
                for (let i = 0; i < orderDetail.length;i++){
                  that.deleteInfoFromCart(JSON.parse(orderDetail[i]).id, that.data.onlyId)
                }
                console.log(res);
              }
            })
            // Toast.success('订单已提交');
            const toast = Toast.loading({
              duration: 0,       // 持续展示 toast
              forbidClick: true, // 禁用背景点击
              loadingType: 'spinner',
              message: '订单已提交'
            });
            wx.startPullDownRefresh({
            })
            let second = 3;
            const timer = setInterval(() => {
              second--;
              if (second) {
                toast.message = `倒计时 ${second} 秒`;
              } else {
                clearInterval(timer);
                Toast.clear();
                wx.navigateTo({
                  url: '../../pages/orderdetail/orderdetail?onlyId=' + that.data.onlyId,
                })
              }
            }, 1000);
           
          }
      }
    })

  },
  /**删除购物车所选商品 */
  deleteFromCart:function(e){
    var that = this;
    const goodsId = e.currentTarget.dataset.goodsId;
    console.log(e);
    wx.showModal({
      content: '确认从购物车中移除该商品',
      success:function(res){
        if(res.confirm){
          // 调用接口删除所选数据
          that.deleteInfoFromCart(goodsId,that.data.onlyId);
        }
      }
    })
    console.log(goodsId);
  },
  /**公共删除接口 */
  deleteInfoFromCart:function(goodsId,onlyId){
    wx.request({
      url: 'http://115.159.127.105:8089/wechatApi/deletInfoFromCart',
      method: 'post',
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      data: {
        goodsId: goodsId,
        userOnlyId: onlyId
      },
      success: function (res) {
        console.log(res);
      }
    })
  },
  /* 公共获取购物车信息接口 */
  getCartInfoByUserOnlyId:function(onlyId){
    console.log(":::")
    const that=this;
    wx.request({
      url: 'http://115.159.127.105:8089/wechatApi/getCartInfoByOnlyId?onlyId=' + onlyId,
      success: function (res) {
        that.setData({
          goodsResult: JSON.parse(res.data.data),
        });
         wx.stopPullDownRefresh();
      }
    })
  }
})