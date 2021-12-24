<template>
  <div id="home">
    <el-container>

      <base-header :activeIndex="activeIndex"></base-header>

      <router-view class="me-container"/>

      <base-footer class="footer" v-show="footerShow"></base-footer>

    </el-container>

  </div>

</template>

<script>

import BaseHeader from "@/components/header/BaseHeader";
import BaseFooter from "@/components/footer/BaseFooter";

export default {
  name: 'Home',
  data() {
    return {
      activeIndex: '/index',
      footerShow: true
    }
  },
  components: {
    'base-header': BaseHeader,
    'base-footer': BaseFooter
  },
}
</script>

<style>

.me-container {
  margin: 100px auto 140px;
}

.footer {
  position: fixed;
}
</style>
