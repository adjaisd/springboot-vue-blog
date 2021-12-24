<template>
  <div>
    <el-form label-width="70px" label-position="left" :rules="rules"
             v-loading="loading"
             element-loading-text="正在载入..."
             element-loading-spinner="el-icon-loading"
             element-loading-background="rgb(0, 0, 0, 0.8)"
             ref="articleForm" :model="articleForm"
             class="articleContainer">
      <h3 class="articleTitle">用户注册</h3>
      <el-form-item label="昵&emsp;称" prop="nickname">
        <el-input type="text" v-model="articleForm.nickname"
                  autocomplete="false"
                  placeholder="请输入昵称"></el-input>
      </el-form-item>
      <el-form-item label="账&emsp;户" prop="username">
        <el-input type="text" v-model="articleForm.username"
                  autocomplete="false"
                  placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密&emsp;码" prop="password">
        <el-input type="text" v-model="articleForm.password"
                  autocomplete="false"
                  placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item label="邮&emsp;箱" prop="email">
        <el-input type="text" v-model="articleForm.email"
                  autocomplete="false"
                  placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input type="text" v-model="articleForm.code"
                  placeholder="点击图片更换验证码"
                  style="width: 150px; margin-right: 5px;"></el-input>
        <img :src="verifyCodeUrl" @click="updateVerifyCodeUrl"/>
      </el-form-item>

      <div class="login" @click="gotoLogin">
        <span>返回登陆</span>
      </div>

      <el-button type="primary" style="width: 100%"
                 @click="submitArticle">注册
      </el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "article",
  data(){
    return {
      loading: false,
      verifyCodeUrl: '/user/verifycode?time=' + new Date(),
      articleForm: {
        username: '',
        password: '',
        email: '',
        // createDate: new Date(),
        nickname: '',
        avatar: '',
        code: ''
      },
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        nickname: [{required: true, message: '请输入昵称', trigger: 'blur'}],
        code: [{required: true, message: '请输入验证码', trigger: 'blur'}],
      }
    }
  },
  methods: {
    updateVerifyCodeUrl() { // 更新验证码
      this.verifyCodeUrl = '/user/verifycode?time=' + new Date()
    },
    submitArticle() { // 提交注册表单
      this.$refs.articleForm.validate((valid) => {
        if (valid) {
          this.$postRequest('/user/register', this.articleForm)
              .then(response => {
                if (response) {
                  this.$router.push("/login")
                }
              })
        } else {
          this.$message.error("请输入所有字段")
          return false;
        }
      });
    },
    gotoLogin(){
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

.articleContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.articleTitle {
  margin: 0 auto 40px auto;
  text-align: center;
}

.el-form-item__content {
  display: block;
  align-items: center;
}

.login {
  float: right;
  color: blue;
  font-size: 15px;
  cursor: pointer;
}

</style>