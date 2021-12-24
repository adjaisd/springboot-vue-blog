<template>
  <div>
    <el-form label-width="70px" label-position="left" :rules="rules"
             v-loading="loading"
             element-loading-text="正在载入..."
             element-loading-spinner="el-icon-loading"
             element-loading-background="rgb(0, 0, 0, 0.8)"
             ref="articleForm" :model="articleForm"
             class="articleContainer">
      <h3 class="articleTitle">写文章</h3>
      <el-form-item label="标&emsp;题" prop="title">
        <el-input type="text" v-model="articleForm.nickname"
                  autocomplete="false"
                  placeholder="请输入标题"></el-input>
      </el-form-item>
      <el-form-item label="类&emsp;别" prop="categoryName">
        <el-input type="text" v-model="articleForm.categoryName"
                  autocomplete="false"
                  placeholder="请输入文章内别"></el-input>
      </el-form-item>
      <el-form-item label="内&emsp;容" prop="content">
        <el-input type="text" v-model="articleForm.content"
                  placeholder="写入文章"
                  style="width: 150px; margin-right: 5px;"></el-input>
      </el-form-item>

      <div class="home" @click="gotoHome">
        <span>回到主页</span>
      </div>

      <el-button type="primary" style="width: 100%"
                 @click="submitArticle">发表文章
      </el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Write",
  data() {
    return {
      loading: false,
      verifyCodeUrl: '/user/verifycode?time=' + new Date(),
      articleForm: {
        username: '',
        title: '',
        summary: '',
        authorId: '',
        categoryName: '',
        content: ''
      },
      rules: {
        title: [{required: true, message: '请输入标题', trigger: 'blur'}],
        categoryName: [{required: true, message: '请输入类别', trigger: 'blur'}],
        content: [{required: true, message: '请输入内容', trigger: 'blur'}],
      }
    }
  },
  methods: {
    submitArticle() { // 提交文章表单
      this.$refs.articleForm.validate((valid) => {
        if (valid) {
          this.$postRequest('/article/write', this.articleForm)
              .then(response => {
                if (response) {
                  this.$router.push("/home")
                }
              })
        } else {
          this.$message.error("请输入所有字段")
          return false;
        }
      });
    },
    gotoHome() {
      this.$router.push('/home')
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

.home {
  float: right;
  color: blue;
  font-size: 15px;
  cursor: pointer;
}

</style>