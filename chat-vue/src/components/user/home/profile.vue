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
              class="active-profile profile-pic kupchic-boss"></span> <span>Aleksandr Kupchenya</span> </a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">Add Account</a></li>
      </ul>
      <ul class="mobile-list">
  
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">New Group</a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">New Channel</a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">Contacts</a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">Calls</a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">Settings</a></li>
        <li class="mobile-list__item"><a href="#" class="mobile-list__link">Night Mode</a></li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'BaseProfile',
    data(){
      return{
        login: {}
      }
    },
    mounted(){
        this.login = this.$cookies.get('LoginUser');
    }
  }
</script>

<style scoped>
    @import url('/public/assets/css/chat/mobile.css');
</style>