//index.js
//获取应用实例
import Notify from '../../components/dist/notify/notify';
import Toast from '../../components/dist/toast/toast';
const app = getApp()

Page({
  data: {
    motto: 'Hello!',
    userInfo: '',
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    onClickRightBar:'登录',
    background: [{ id: '1', image: 'https://ws1.sinaimg.cn/large/006olMfyly1fz2olvwb5qj30sg0nxdj8.jpg' }, { id: '2', image: 'https://ws1.sinaimg.cn/large/006olMfyly1fz2on3fzubj30k40sgn0t.jpg' }, { id: '3', image:'https://ws1.sinaimg.cn/large/006olMfyly1fz2oooypfuj30sg0lc77e.jpg'}],
    indicatorDots: true,
    vertical: false,
    interval: 2000,
    duration: 500,
    searResult:[]
  },
  //事件处理函数
  bindViewTap: function() {
    const that=this;
    console.log(that.data.userInfo);
    // wx.navigateTo({
    //   url: '../logs/logs'
    // })
  },
 
  onLoad: function () {
    const that=this;

    //首页加载就初始化用户唯一Id  每次进如小程序则初始化 
    wx.setStorage({
      key: 'onlyId',
      data: null,
    })

    // 查看是否授权
    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: function (res) {
              console.log("用户已授权")
              wx.setStorage({
                key: 'userInfo',
                data: res.userInfo,
              })
             //通知消息
              Notify({
                text: '用户已授权',
                duration: 1000,
                selector: '#myself_set_notice',
                backgroundColor: '#1989fa'
              });

            }
          })
        }
      }
    })


    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }

  /**测试我的页面  用户未授权 */
    // wx.setStorage({
    //   key: 'userInfo',
    //   data: that.data.userInfo,
    // })
  

  },
  
 

  getUserInfo: function(e) {
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  /**首页 返回按钮 */
  onClickLeft:function(e){
    const that=this;
    console.log(that.data.userInfo);
  },
  /**首页  登录按钮 */
  onClickRight:function(e){
    const that = this;
    var JSCODE="";
    var userInfo =JSON.stringify(that.data.userInfo);
    console.log(e);
    wx.login({
      success:function(res){
        // const APPID ="wx98e9bd4331328790";
        // const SECRET ="ed247ab3badac7073b4dae53ae316114";
        // const GRANT_TYPE ="authorization_code";
        console.log(res);
        if(res.code){
          JSCODE=res.code
          
        //小程序本地测试
          // wx.request({
          //   url: 'https://api.weixin.qq.com/sns/jscode2session?appid='+APPID+'&secret='+SECRET+'&js_code='+JSCODE+'&grant_type='+GRANT_TYPE,
          //   success:function(res){
          //     console.log(res);
          //   }
          // })
        wx.request({
          url: 'http://115.159.127.105:8089/wechatApi/user/getCodeWithUserInfo',
          method:'post',
          header:{
            "Content-Type": "application/x-www-form-urlencoded"
          },
          data:{
            code: JSCODE,
            userInfo: userInfo
          },
          success:function(res){
            console.log(res.data)
            if (res.data.data.indexOf("errcode") >=0){
              that.setData({
                onClickRightBar: '登录失败'
              });
              wx.setStorage({
                key: 'onlyId',
                data: null,
              })
            }else{

              that.setData({
                onClickRightBar: '登录成功'
              });
              wx.showToast({
                title: '登录成功！',
              })
              wx.setStorage({
                key: 'onlyId',
                data: JSON.parse(res.data.data).openId,
              })
            }
            console.log(res);
          }
        })

        }else{
          console.log("登录失败");
        }

      }
    });

   
  },
  onSearch:function(e){
    const that=this;
    // console.log(e)
    wx.request({
      url: 'http://115.159.127.105:8089/wechatApi/getGoodsListByKeys?keys='+e.detail,
      success:function(res){
        console.log(res);
        if(res.statusCode==200){
          wx.navigateTo({
            url: '../../pages/searchresult/searchresult?searchResult=' + res.data.data,
          })
        }
      }
    })
  },
  forShopRate:function(e){
    Toast('感谢您为本店评分');
  }
})
