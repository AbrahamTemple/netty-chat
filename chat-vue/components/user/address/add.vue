<template>
  <!-- 导航栏 -->
  <van-nav-bar 
    title="新增收货地址"
    left-text="返回"
    left-arrow
    @click="Back"
  />

  收货地址添加
</template>


<script>
  export default {
    name: "AddressAdd",
    emits: ['toggle'], //定义说明父组件的自定义事件
    data()
    {
      return {
        LoginUser: this.$cookies.get('LoginUser'),
      }
    },
    created()
    {
      //触发父组件的事件
      this.$emit('toggle', false)
    },
    methods:{
      Back()
      {
        //返回上一层界面
        this.$router.go(-1)
      },
    }
  }
</script>