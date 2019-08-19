// pages/my/my.js
var areaList = require('../../utils/area.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
      userInfo:null,
      my_info_shopping:{account:100,redPackets:25,receiptAddress:'山东省 青岛市 崂山区 '},
      activeNames: ['3'],
      show:false,
      editAddress:'',
      areaList: areaList.default,
      onlyId:null  //唯一id
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
    wx.getStorage({
      key: 'userInfo',
      success: function(res) {
        console.log(res.data);
        if (res.data==''){
          wx.showModal({
            showCancel:false,
            confirmText:"我知道了",
            content: '您还没有授权,请重新授权',
            success:function(res){
              //重新授权
                if(res.confirm){
                  wx.getUserInfo({
                    success: res => {
                      console.log(res);
                    }
                  })
                  console.log("ok")
                }
            }
          })
        }
        that.setData({
          userInfo:res.data
        })
      },
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

    const that = this;
    wx.getStorage({
      key: 'onlyId',
      success: function (res) {
        if (res.data == null) {
          wx.showToast({
            icon: 'loading',
            title: '用户未登录',
          })
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
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },
  /**折叠版事件 */
  onChange(event) {
    this.setData({
      activeNames: event.detail
    });
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
 /**显示编辑弹窗 */
 editAddress:function(e){
   const that=this;
   that.setData({
     show:true
   });

 },
  //取消按钮  
  onClose:function(e) {
    console.log(e);
    this.setData({ show: false });
  },
  //确认  提交信息
  onConfirm: function (e) {
    const that=this;
    // console.log(e.detail.detail)
    const province = e.detail.detail.province;
    const city = e.detail.detail.city;
    const county = e.detail.detail.county;
    const address = province + " " + city + " " + county
    this.setData({
      show: false,
      editAddress: address
    })
    console.log(address);
    //表单提交
    var useriNfo = { account: null, redPackets: null, receiptAddress: address, openId: that.data.onlyId}
    wx.request({
      url: 'http://115.159.127.105:8089/wechatApi/user/editMyInfo',
      method: 'post',
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      data: {
        userInfo:JSON.stringify(useriNfo)
      },
      success: function (res) {
        console.log(res)
      }
    })


    // wx.request({
    //   url: '',
    //   header:{
    //     modal 
    //   },
    //   method:'post',
    //   data:{receiptAddress:that.data.editAddress},
    //   success:function(res){}
    // })
  },

  getMyOrderInfo:function(e){
    const that=this;
    wx.navigateTo({
      url: '../../pages/orderdetail/orderdetail?onlyId='+that.data.onlyId,
    })
  }

 

})