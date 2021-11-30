<template>
  <!-- 基本资料 -->
  <div class="myBox" :style="{background:'url('+LoginUser.cover+')'}">
    <div class="L">
      <img :src="LoginUser.avatar" />
    </div>
    <div class="C">
      <div class="tit">{{LoginUser.nickname}}</div>
      <div class="sub">{{LoginUser.createtime}}</div>
    </div>
    <div class="R">
      <img src="/assets/images/go.png" />
    </div>
  </div>
  <div class="clear"></div>

  <!-- 菜单 -->
  <div class="allkbox"></div>

  <div class="myboxcon" v-if="LoginUser.auth == 0" @click="EmailCheck">
    <p>邮箱认证</p>
    <img src="/assets/images/go.png" />
  </div>

  <div class="myboxcon">
    <router-link to="/user/base/profile">
      <p>个人资料</p>
      <img src="/assets/images/go.png" />
    </router-link>
  </div>
  <div class="myboxcon">
    <router-link to="/user/address/index">
      <p>收货地址</p>
      <img src="/assets/images/go.png" />
    </router-link>
  </div>
  <div class="myboxcon">
    <p>我的订单</p>
    <img src="/assets/images/go.png" />
  </div>
  <div class="myboxcon">
    <p>我的消费记录</p>
    <img src="/assets/images/go.png" />
  </div>
  <div class="myboxcon" @click="logout">
    <p>退出登录</p>
    <img src="/assets/images/go.png" />
  </div>
  
  <div class="clear"></div>
  <div class="h54"></div>
</template>

<script>
  export default {
    name: "BaseIndex",
    emits: ['toggle'], //定义说明父组件的自定义事件
    created()
    {
      //触发父组件的事件
      this.$emit('toggle', true)
    },
    data()
    {
      return {
        LoginUser: this.$cookies.get('LoginUser')
      }
    },
    methods:{
      logout()
      {
        //弹出确认对话框
        this.$dialog.confirm({
          'title':'退出',
          'message': '是否确认退出'
        })
        .then(() => {
          //删除cookie
          this.$cookies.remove('LoginUser')

          //页面跳转
          this.$router.push('/user/base/login')

          return
        })
        .catch(() => {})
      },
      EmailCheck()
      {
        //弹出确认对话框发送请求
        this.$dialog.confirm({
          title: '邮箱验证',
          message: '是否确认发送邮件'
        })
        .then(async () => {
          //点击确认按钮

          //调用接口
          var success = await this.$api.email({userid: this.LoginUser.id})

          if(success.result)
          {
            this.$notify({
                type: 'success',
                message: success.msg,
                duration: 1000,
            })
          }else
          {
            this.$notify({
                type: 'danger',
                message: success.msg,
                duration: 1000,
            })
          }
        })
        .catch(() => {
          //点击取消按钮
        })
        
      }
    }
  };
</script>


<style>
  .myboxcon a{
    display: block;
    width: 100%;
    height: 100%;
  }
</style>