<template>
  <div class="article-container">
    <el-form class="row" :rules="rules" v-loading="loading"
             element-loading-text="正在载入..."
             element-loading-spinner="el-icon-loading"
             element-loading-background="rgb(0, 0, 0, 0.8)"
             ref="articleForm" :model="articleForm">
      <div class="col-toHome col">
        <span @click="gotoHome" style="cursor: pointer;"> &lt;回到主页</span>
      </div>

      <div class="col-title col">
        <el-input class="title" type="text"
                  v-model="articleForm.title"
                  placeholder="请输入标题">
        </el-input>
      </div>

      <div class="col-category col">
        <el-input class="category" placeholder="请输入文章内别"
                  v-model="articleForm.categoryName">
        </el-input>
      </div>

      <div class="col-button col">
        <el-button class="button" type="danger"
                   @click="submitArticle"> > 发表文章
        </el-button>
      </div>

    </el-form>

    <v-md-editor class="editor" v-model="articleForm.content"
                 left-toolbar="undo redo clear | h bold italic strikethrough quote | ul ol table hr | link code | emoji"
                 right-toolbar="preview toc sync-scroll fullscreen"
                 height="700px"/>
  </div>

</template>

<script>
export default {
  name: "MarkdownEditor",
  data() {
    return {
      loading: false,
      verifyCodeUrl: '/user/verifycode?time=' + new Date(),
      articleForm: {
        username: this.$store.state.userinfo.username,
        title: '【无标题】',
        summary: '',
        authorId: this.$store.state.userinfo.id,
        categoryName: '【无类别】',
        content: '【请输入文章内容】'
      },
      rules: {
        username: [{required: true, message: '请重新登陆', trigger: 'blur'}],
        title: [{required: true, message: '请输入标题', trigger: 'blur'}],
        authorId: [{required: true, message: '请重新登陆', trigger: 'blur'}],
        categoryName: [{required: true, message: '请输入类别', trigger: 'blur'}],
      }
    }
  },
  methods: {
    submitArticle() { // 提交文章表单
      let that = this
      if (!this.checkValid(this.articleForm.username, "请重新登陆，1秒后跳转到登陆界面") ||
          !this.checkValid(this.articleForm.authorId, "请重新登陆，1秒后跳转到登陆界面")) {
        setTimeout(function () {
          that.$router.push("/login")
        }, 1000)
      } else if (!this.checkValid(this.articleForm.title, "请输入文章标题") ||
          !this.checkValid(this.articleForm.categoryName, "请输入文章类别") ||
          !this.checkValid(this.articleForm.content, "请输入文章内容")) {
        return false
      } else {

        this.$postRequest('/article/write', this.articleForm)
            .then(response => {
              if (response) {
                this.$message.success("1秒后跳转到首页")
                setTimeout(function () {
                  that.$router.push("/index")
                }, 1000)
              }
            })
      }
    },
    gotoHome() {
      this.$router.push('/index')
    },
    checkValid(attr, msg) {
      if (attr.length === 0) {
        this.$message.error(msg)
        return false
      } else return true
    }
  },
}
</script>

<style scoped>

.row {
  height: 40px;
  line-height: 40px;
  display: inline;
  width: 100%;
  alignment: center;
  align-self: center;
  text-align: center;
}

.col {
  display: inline-block;
  padding: 10px;
  margin: 0 auto;
}

.col-toHome {
  min-width: 68px;
  font-size: 15px;
  margin-right: 20px;
}

.col-title {
  border-radius: 20%;
  width: 60%;
}

.title {
  border-radius: 20px;
}

.col-button {
  float: right;
  margin-right: 20px;
}

.button {
  right: 20px;
  border-radius: 20px;
}
</style>