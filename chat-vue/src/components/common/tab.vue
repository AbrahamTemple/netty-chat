<template>
  <div class="footerBox">
    <div class="footer">
      <ul>
        <li>
          <router-link :to="{name:'DialogsIndex', params:{id:-1}}">
            <img src="/assets/images/f1.png">
            <p>消息</p>
          </router-link>
        </li>
        <li>
		      <router-link :to="{name:'RelationIndex', params:{id:-1}}">
          <img src="/assets/images/f2.png">
          <p>列表</p>
          </router-link>
        </li>
        <li>
		  <router-link to="/user/base/login">
          <img src="/assets/images/f3.png">
          <p>登陆</p>
          </router-link>
        </li>
        <li>
          <router-link to="/user/home/profile">
            <img src="/assets/images/f4.png">
            <p>我的</p>
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>


<script>
  export default {
    name: "tab",
    data(){
      return{
        id: '',
        hash: ''
      }
    },
    mounted(){
      // this.id = this.$cookies.get('LoginUser')['id'] != null ? this.$cookies.get('LoginUser')['id'] : 0;
      // this.hash = this.$store.state.list.eid != '' ? this.$store.state.list.eid : 0;
    }
  }
</script>