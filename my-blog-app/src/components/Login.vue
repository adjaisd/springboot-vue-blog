<template>
  <div>
    <el-form label-width="70px" label-position="left" :rules="rules"
             v-loading="loading"
             element-loading-text="正在登陆..."
             element-loading-spinner="el-icon-loading"
             element-loading-background="rgb(0, 0, 0, 0.8)"
             ref="loginForm" :model="loginForm"
             class="loginContainer">
      <h3 class="loginTitle">用户登陆</h3>
      <el-form-item label="账&emsp;户" prop="username">
        <el-input type="text" v-model="loginForm.username"
                  autocomplete="false"
                  placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密&emsp;码" prop="password">
        <el-input type="password" v-model="loginForm.password"
                  autocomplete="false"
                  placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input type="text" v-model="loginForm.code"
                  placeholder="点击图片更换验证码"
                  style="width: 150px; margin-right: 5px;"></el-input>
        <img :src="verifyCodeUrl" @click="updateVerifyCodeUrl"/>
      </el-form-item>
      <el-checkbox v-model="checked"
                   class="loginRemember">记住我
      </el-checkbox>
      <div class="register" @click="gotoRegister">
        <span>注册</span>
      </div>

      <el-button type="primary" style="width: 100%"
                 @click="submitLogin">登陆
      </el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loading: false,
      verifyCodeUrl: '/user/verifycode?time=' + new Date(),
      checked: true,
      loginForm: {
        username: '',
        password: '',
        code: ''
      },
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        code: [{required: true, message: '请输入验证码', trigger: 'blur'}],
      }
    }
  },
  methods: {
    updateVerifyCodeUrl() { // 更新验证码
      this.verifyCodeUrl = '/user/verifycode?time=' + new Date()
    },
    submitLogin() { // 提交登陆表单
      this.$refs.loginForm.validate((valid) => {
        let that = this
        if (valid) {
          this.$postRequest('/user/login', this.loginForm)
              .then(response => {
                if (response) {
                  let user = {
                    id : response.data['userId'],
                    token : response.data['token'],
                    username : that.loginForm.username,
                    avatar : that.loginForm.avatar,
                  }
                  this.$store.commit("setUserInfo", user)
                  this.$router.push("/")
                }
              })
        } else {
          this.$message.error("请输入所有字段")
          return false;
        }
      });
    },
    gotoRegister() {
      this.$router.push('/register')
    }
  }
}
</script>

<style scoped>

.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.loginTitle {
  margin: 0 auto 40px auto;
  text-align: center;
}

.loginRemember {
  height: 30px;
  width: 50px;
  text-align: left;
  margin: 0 0 15px 0;
}

.el-form-item__content {
  display: block;
  align-items: center;
}

.register {
  float: right;
  color: blue;
  font-size: 15px;
  cursor: pointer;
}
</style>