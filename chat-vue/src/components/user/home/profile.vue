<template>
  <div class="mobile-menu-wrp">
    <div class="mobile-menu">
      <div class="menu-header">
        <div class="menu-header__top">
          <label for="dropdown-profiles-btn" class="profile-pic kupchic-boss">
            <img :src="login.avatar"/>
          </label>
          <div class="saved-ms"></div>
        </div>
  
        <label for="dropdown-profiles-btn" class="boss-info">
          <div class="menu-header__bottom">
            <h2>{{login.nickname}}</h2>
            <h3>+{{login.email}}</h3>
          </div>
          <button id="dropdown-profiles-btn"></button>
        </label>
      </div>
      <ul class="profiles-list mobile-list">
        <li class="mobile-list__item"><a href="#" class="mobile-list__link "> <span
              class="active-profile profile-pic kupchic-boss"></span> <span>{{login.nickname}}</span> </a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">增加新的账号</a></li>
      </ul>
      <ul class="mobile-list">
  
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">新的群组</a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">系统通知</a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">联系人</a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">社交电话</a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">夜间模式</a></li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'BaseProfile',
    data(){
      return{
        login: {
          avatar: '/assets/images/avatar-01.jpg',
          nickname: '现在去请登录',
          email: 'Your Email'
		    }
      }
    },
    mounted(){
		if(this.$cookies.get('LoginUser') != undefined){
			this.login = this.$cookies.get('LoginUser');
		}
    }
  }
</script>

<style scoped>
    @import url('/public/assets/css/chat/mobile.css');
</style>