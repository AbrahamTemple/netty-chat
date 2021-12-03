<template>
    <!-- 渲染组件 -->
    <router-view @toggle="IsShow"></router-view>

    <!-- 底部 -->
    <tab v-show="show"></tab>
</template>

<script>  
  // import tab from './components/common/tab.vue'
  import tab from './components/common/tabbar.vue'
  export default {
    data()
    {
      return {
        show: true
      }
    },
    methods:{
      //事件对应的方法
      IsShow(value)
      {
        this.show = value
      }
    },
    components: {
      tab
    }
  }
</script>