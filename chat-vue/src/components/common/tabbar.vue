<template>
  <div class="footerBox">
    <div class="footer">
      <van-tabbar v-model="active">
		<van-tabbar-item icon="friends-o" badge="5">
			<router-link to="/chat/list/dialogs">消息</router-link>
		</van-tabbar-item>
        <van-tabbar-item icon="search">
			<router-link to="/chat/list/dialogs">好友</router-link>
		</van-tabbar-item>
        <van-tabbar-item icon="setting-o">
			<router-link to="/chat/list/dialogs">我的</router-link>
		</van-tabbar-item>
      </van-tabbar>
	</div>
  </div>
</template>


<script scoped>
  export default {
    name: "tabbar"
  }
</script>