<template>
  <el-header class="me-area">
    <el-row class="me-header">

      <el-col :span="4" class="me-header-left">
        <router-link to="#" class="me-title">
          <img src="@/assets/medias/logo.png"/>
        </router-link>
      </el-col>

      <el-col v-if="!simple" :span="16">
        <el-menu :router=true menu-trigger="click"
                 :default-active="activeIndex"
                 mode="horizontal">
          <el-menu-item index="/index">首页</el-menu-item>
          <el-menu-item index="/tag">类别</el-menu-item>
          <el-menu-item index="/log">日志</el-menu-item>
          <el-menu-item index="/messageBoard">留言板</el-menu-item>

          <el-col :span="3" :offset="3">
<!--            <el-menu-item index="/article/write">-->
<!--              写文章-->
<!--            </el-menu-item>-->
            <el-menu-item index="/article/editor">
              写文章
            </el-menu-item>
          </el-col>
        </el-menu>
      </el-col>

      <template v-else>
        <slot></slot>
      </template>


      <el-col :span="4">

        <template v-if="!isLogin">
          <el-menu :router=true menu-trigger="click" mode="horizontal"
                   active-text-color="#5FB878">
            <el-menu-item index="/login">
              <el-button type="text">登录</el-button>
            </el-menu-item>
            <el-menu-item index="/register">
              <el-button type="text">注册</el-button>
            </el-menu-item>
          </el-menu>
        </template>

        <template v-else>
          <el-dropdown trigger="click" @command="handleCommand">
            <img class="me-header-picture"
                 src="@/assets/medias/avatar.jpg"
                 style="cursor: pointer;"/>
            <span style="cursor: default;">
              {{ userinfo.username }}
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="a">

                </el-dropdown-item>
                <el-dropdown-item command="b">
                  <i class="el-icon-back"></i>退出
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>

        </template>
      </el-col>

      <!--      <el-col :span="4">-->
      <!--      <el-menu :router=true menu-trigger="click" mode="horizontal"-->
      <!--               active-text-color="#5FB878">-->

      <!--        <template v-if="!isLogin">-->
      <!--          <el-menu-item index="/login">-->
      <!--            <el-button type="text">登录</el-button>-->
      <!--          </el-menu-item>-->
      <!--          <el-menu-item index="/register">-->
      <!--            <el-button type="text">注册</el-button>-->
      <!--          </el-menu-item>-->
      <!--        </template>-->

      <!--        <template v-else>-->

      <!--          <el-sub-menu index="">-->
      <!--            <el-menu-item>-->
      <!--              <img class="me-header-picture"-->
      <!--                   src="@/assets/medias/avatar.jpg"/>-->
      <!--            </el-menu-item>-->
      <!--            <el-menu-item>-->
      <!--              <i class="el-icon-back"></i>退出-->
      <!--            </el-menu-item>-->
      <!--          </el-sub-menu>-->

      <!--        </template>-->
      <!--      </el-menu>-->
      <!--      </el-col>-->

    </el-row>
  </el-header>
</template>

<script>

export default {
  name: 'BaseHeader',
  props: {
    activeIndex: {
      type: String,
      default: '/index'
    },
    simple: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    isLogin() {
      return this.$store.state.userinfo.token !== ""
    },
    userinfo() {
      return this.$store.state.userinfo
    },
  },
  methods: {
    handleCommand(command) {
      if (command === 'b') {
        this.logout()
        this.$router.replace("/")
      }
    },
    logout() { // 退出登陆
      console.log(this.$store.state.userinfo.token !== "");
      this.$store.commit("delUserInfo")
      this.$message.success("成功退出登陆.")
      this.$router.replace("/")
    },
  },
}
</script>

<style>

.el-header {
  position: fixed;
  z-index: 1024;
  min-width: 100%;
  box-shadow: 0 2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
}

.me-title {
  margin-top: 10px;
  font-size: 24px;
}

.me-header-left {
  margin-top: 10px;
}

.me-title img {
  max-height: 2.4rem;
  max-width: 100%;
}

.me-header-picture {
  width: 36px;
  height: auto;
  margin: 10px;
  border: 1px solid #ddd;
  border-radius: 50%;
  vertical-align: middle;
  background-color: #5fb878;
}

</style>
