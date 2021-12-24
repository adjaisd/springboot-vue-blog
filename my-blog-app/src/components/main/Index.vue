<template>
  <div>
    <el-container>

      <el-main class="me-articles">

        <article-scroll-page></article-scroll-page>

      </el-main>

      <!--      <el-aside>-->

      <!--        <card-me class="me-area"></card-me>-->
      <!--        <card-tag :tags="hotTags"></card-tag>-->

      <!--        <card-article cardHeader="最热文章" :articles="hotArticles"></card-article>-->

      <!--        <card-archive cardHeader="文章归档" :archives="archives"></card-archive>-->

      <!--        <card-article cardHeader="最新文章" :articles="newArticles"></card-article>-->

      <!--      </el-aside>-->

    </el-container>
  </div>
</template>

<script>

import ArticleScrollPage
  from "@/components/main/common/ElMain/ArticleScrollPage";

export default {
  name: "Index",
  components: {
    ArticleScrollPage,
  },
}
</script>

<style scoped>

.el-container {
  width: 960px;
}

.el-aside {
  margin-left: 20px;
  width: 260px;
}

.el-main {
  padding: 0px;
  line-height: 16px;
}

.el-card {
  border-radius: 0;
}

.el-card:not(:first-child) {
  margin-top: 20px;
}
</style>