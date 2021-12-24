<template>
  <div class="article-container">
    <el-row>
      <el-col :span="4">
        <div class="grid-content bg-purple-light"
             style="cursor: pointer;">
          <span class="toHome" @click="gotoHome">返回主页</span>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple-light">
          <span class="article-title">标题: {{ article.title }}</span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple-light">
          <span class="article-authorInfo">
            作者ID: {{ article.authorId }}
          </span><br/>
          <span class="article-createDate">
            发表时间: {{ format(article.createDate) }}
          </span>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple-light article-content">
          <v-md-preview :text="article.content"
                        right-toolbar="preview toc sync-scroll fullscreen"></v-md-preview>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple">
          评论模块
        </div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
export default {
  name: "Read",
  props: {
    id: {
      type: String,
      default: '1'
    }
  },
  data() {
    return {
      article: {}
    }
  },
  created() {
    if (this.isLogin())
      this.getArticleContent(this.id)
    else {
      this.$message.error("登陆后才可以查看文章.")
      this.$router.replace("/login")
    }
  },
  methods: {
    format(time) {
      return this.$formatTime(time)
    },
    isLogin() {
      return this.$store.state.userinfo.token &&
          this.$store.state.userinfo.token.length > 0
    },
    getArticleContent(id) {
      this.$getRequest(`/article/view/${id}`).then(response => {
        if (response) {
          let json = JSON.stringify(response.data.article)
          this.article = JSON.parse(json)
        } else {
          this.$message.error("出错了.")
        }
      })
    },
    gotoHome() {
      this.$router.replace("/index")
      this.$message.success("成功返回首页")
    }
  }
}
</script>

<style scoped>

.article-container{
  padding-top: 35px;
  margin-left: 30px;
  font-family: 楷体,system-ui;
}
.toHome .article-authorInfo .article-createDate{
  font-size: 20px;
}
.article-title{
  font-size: 25px;
}

.toHome{
  color: blue;
}
.el-row {
  margin-bottom: 20px;

&
:last-child {
  margin-bottom: 0;
}

}
.el-col {
  border-radius: 4px;
  cursor: default;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  /*background: #e5e9f2;*/
  background: transparent;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.article-title {

}
</style>