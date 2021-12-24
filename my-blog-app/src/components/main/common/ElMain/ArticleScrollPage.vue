<template>
  <scroll-page :loading="loading" :offset="offset" :no-data="noData"
               v-on:load="load">
    <article-item v-for="a in articles" :key="a.id" v-bind="a"></article-item>
  </scroll-page>
</template>

<script>

import ArticleItem from "@/components/main/common/ElMain/article/ArticleItem";
import ScrollPage from "@/components/main/common/ElMain/scrollpage";

export default {
  name: "ArticleScrollPage",
  mounted() {
    window.addEventListener('scroll', this.handleScroll, false);
  },
  beforeUnmount() {
    window.removeEventListener('scroll', this.handleScroll)
  },
  props: {
    offset: {
      type: Number,
      default: 100
    },
    page: {
      type: Object,
      default() {
        return {}
      }
    },
    query: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  watch: {
    'query': {
      handler() {
        this.noData = false
        this.articles = []
        this.innerPage.pageNumber = 1
        this.getArticles()
      },
      deep: true
    },
    'page': {
      handler() {
        this.noData = false
        this.articles = []
        this.innerPage = this.page
        this.getArticles()
      },
      deep: true
    }
  },
  created() {
    this.getArticles()
  },
  data() {
    return {
      loading: false,
      noData: false,
      innerPage: {
        pageSize: 5,
        pageNumber: 1,
        sort: 'desc',
      },
      articles: []
    }
  },
  methods: {
    view(id) {
      this.$router.push({path: `/view/${id}`})
    },
    load() {
      this.getArticles()
    },
    getArticles() {
      let that = this
      that.loading = true
      let page = that.innerPage
      let params = {
        pageNumber: page.pageNumber,
        pageSize: page.pageSize,
        sort: page.sort,
      }
      this.$getRequest('/article/list', params).then(data => {
        let newArticles = data.data.articles
        if (newArticles && newArticles.length > 0) {
          that.innerPage.pageNumber += 1
          that.articles = that.articles.concat(newArticles)
        } else {
          that.noData = true
        }
      }).catch(error => {
        if (error !== 'error') {
          that.$message({type: 'error', message: '文章加载失败!', showClose: true})
        }
      }).finally(() => {
        that.loading = false
      })
    }
  },
  components: {
    'article-item': ArticleItem,
    'scroll-page': ScrollPage
  }

}
</script>

<style scoped>
.el-card {
  border-radius: 0;
}

.el-card:not(:first-child) {
  margin-top: 10px;

}
</style>
