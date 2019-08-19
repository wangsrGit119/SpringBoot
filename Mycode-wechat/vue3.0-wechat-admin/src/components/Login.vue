<template>
  <div class="login-div">
    <label class="admin-label">小程序订单处理商家后台</label>
  <el-form :model="userInfo" :rules="rules" ref="userInfo" label-position="left" label-width="0px" class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="userInfo.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="userInfo.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked  class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="submit" :loading="logining">登录</el-button>
    </el-form-item>
  </el-form>
  </div>
</template>

<script>



    export default {
    name:'Login',
    data() {
      return {
        logining: false,
        userInfo: {
          username: 'admin',
          password: 'wangsr01'
        },
        rules: {
          username: [
            { required: true, message: '请输入账号', trigger: 'blur' },
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
          ]
        },
        checked: false
      };
    },
    methods: {
        submit(ev) {
        var _this = this;
          _this.$refs.userInfo.validate((valid) => {
            console.log("val:",valid);
          if (valid) {
              _this.logining = true;
            var loginParams = { username: _this.userInfo.username, password: _this.userInfo.password };
            if(loginParams.username == 'admin' && loginParams.password == 'wangsr'){
                sessionStorage.setItem('userInfo', JSON.stringify(loginParams));
                _this.$router.push({ path: '/' });
                return false;
            }
              _this.$message({
                  message: '用户名或密码错误',
                  type: 'error'
                });
              _this.logining = false;
          } else {
            console.log('error submit!!');
            return false;
            }
        });
      }
    }
  }

</script>

<style >
  body{
    margin:0px;
    padding:0px;
  }
  /*登录背景*/
  .login-div{
    margin: 0;
    padding: 0;
    z-index:0;
    height:100%;
    background: url("../assets/bg-1.jpg") no-repeat;
    -webkit-filter: brightness(70%) ; /* Chrome, Safari, Opera */
    filter:  brightness(70%) ;
    text-align: left;
  }
  /*标签*/
  .login-div .admin-label{
    font-weight: bold;
    -webkit-filter:  brightness(300%); /* Chrome, Safari, Opera */
    filter:  brightness(300%);
    font-size: 40px;
    text-shadow: 0px 0px 2px #686868,
    0px 1px 1px #ddd,
    0px 2px 1px #d6d6d6,
    0px 3px 1px #ccc,
    0px 4px 1px #c5c5c5,
    0px 5px 1px #c1c1c1,
    0px 6px 1px #bbb,
    0px 7px 1px #777,
    0px 8px 3px rgba(100, 100, 100, 0.4),
    0px 9px 5px rgba(100, 100, 100, 0.1),
    0px 10px 7px rgba(100, 100, 100, 0.15),
    0px 11px 9px rgba(100, 100, 100, 0.2),
    0px 12px 11px rgba(100, 100, 100, 0.25),
    0px 13px 15px rgba(100, 100, 100, 0.3);

    color: black;
    margin-top:20px;
  }
  /*登录框*/
  .login-div .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    -webkit-filter:  brightness(110%); /* Chrome, Safari, Opera */
    filter:  brightness(110%);
  }

  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .remember {
    margin: 0px 0px 35px 0px;
  }




</style>
