<template>

  <el-container>
    <!--头部-->
    <el-header>

      <!--头部左侧菜单logo-->
        <el-col :span="3" class=" " >
          <i style="font-size: 40px;margin-top: 10px; " class="el-icon-menu"></i>
        </el-col>
        <!--头部用户信息-->
        <el-col :span="4" class="userinfo">
          <el-dropdown trigger="hover">
            <span class="el-dropdown-link userinfo-inner"><img :src="sysUserAvatar" /> {{sysUserName}}</span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>我的消息</el-dropdown-item>
              <el-dropdown-item>设置</el-dropdown-item>
              <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>

    </el-header>
    <el-container>
      <!--菜单栏-->
      <el-aside width="200px">

        <el-menu  default-active="1-1"
                  class="el-menu-vertical-demo"
                  @open="handleOpen"
                  @close="handleClose"
                  @select="handleSelect"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-tickets"></i>订单处理</template>


              <el-menu-item index="1-1">订单处理</el-menu-item>
              <el-menu-item index="1-2">结算订单</el-menu-item>
              <el-menu-item index="1-3">订单统计</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-setting"></i>菜品管理</template>
            <el-menu-item index="2-1">菜品添加</el-menu-item>
            <el-menu-item index="2-2">菜品下架</el-menu-item>
          </el-submenu>
          <!--<el-submenu index="3">-->
            <!--<template slot="title"><i class="el-icon-edit-outline"></i>信息修改</template>-->
          <!--</el-submenu>-->
        </el-menu>

      </el-aside>
      <!--main页面-->
      <el-main>
       <!--面包屑-->
        <el-row>
          <el-col :span="2">
            <strong class="title">{{$route.name}}</strong>
            <audio id="audio" :src="audioSrc"/>
          </el-col>
        </el-row>

        <!--跳转页面-->
        <el-row>
          <el-col :span="24">
            <div class="mainPage" style="" >
              <router-view ></router-view>
            </div>
          </el-col>
        </el-row>



      </el-main>
    </el-container>
  </el-container>

</template>

<script>

  export default {
    name:'home',
    data() {
      return {
        sysUserName: 'wangsr',
        sysUserAvatar: 'https://pic.qqtn.com/up/2019-1/2019010907542363622.jpg',
        websocket:null,
        audioSrc:'http://115.159.127.105/images/handle.mp3',
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleSelect(key, keyPath){
        console.log(key)
        console.log(keyPath)
        switch(key){
          case '1-1':
            this.$router.push('/wechatOrder');
            break;
          case '1-2':
            this.$router.push('/wechatOrderBalance');
            break;
          case '1-3':
            this.$router.push('/orderEcharts');
            break;
          case '2-1':
            this.$router.push('/goodsItemAdd');
            break;
          case '2-2':
            this.$router.push('/goodsItemDel');
            break;
        }
      },
      //退出登录
      logout: function () {
        var _this = this;
        this.$confirm('确认退出吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
          sessionStorage.removeItem('userInfo');
          _this.$router.push('/login');
        }).catch(() => {

        });
      },
        getInitSocket(){
            const mysocket = "ws://115.159.127.105:8089/admin/webSocket";//ws地址
            this.websocket = new WebSocket(mysocket);
            this.websocket.onopen = this.websocketonopen;
            this.websocket.onerror = this.websocketonerror;

            this.websocket.onmessage = this.websocketonmessage;
            this.websocket.onclose = this.websocketonclose;
        },
        websocketonopen (ev) {
            console.log('链接打开');
        },
        websocketonclose(ev) {
            console.log('连接关闭');
        },
        websocketonmessage(ev) {
            console.log('服务端消息：'+ev.data);
            this.warnInfo();
        },
        websocketonerror(){
            console.log('连接错误');
        },
      // 订单提醒
        warnInfo(){
            this.$notify({
                title: '提醒',
                message: '您有新的订单，请尽快处理',
            });
            this.aplayAudio();
        },
        // 语音播放
        aplayAudio () {
          console.log("audio");
            const audio = document.getElementById('audio');
            audio.play()
        }
    },
      mounted(){
        this.getInitSocket();
      }
  };
</script>

<style>
  .el-header {
    background-color: #1aa4d1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    background-color: #545c64;
    color: #333;
  }
  .mainPage{
    margin-top:10px;

  }
  .el-main{
    padding-left: 10px;
    height: 900px;
  }

  .userinfo {
    text-align: right;
    padding-right: 35px;
    float: right;

  }

  .userinfo-inner img {
    width: 40px;
    height: 40px;
    border-radius: 50px;
    margin: 10px 0px 10px 10px;
    float: right;
  }

</style>
