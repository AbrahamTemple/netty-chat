<template>
  <div class="footerBox">
    <div class="footer">
      <van-tabbar v-model="active">
        <van-tabbar-item :badge="this.badge" @click="routerTo('/chat/list/dialogs',undefined)">
          <span>消息</span>
          <template #icon="props">
            <img :src="props.active ? icon.message.active : icon.message.inactive" />
          </template>
        </van-tabbar-item>
        <van-tabbar-item @click="routerTo('RelationIndex',{id:-1})">
          <span>列表</span>
          <template #icon="props">
            <img :src="props.active ? icon.user.active : icon.user.inactive" />
          </template>
        </van-tabbar-item>
        <van-tabbar-item @click="routerTo('/user/home/profile',undefined)">
          <span>设置</span>
          <template #icon="props">
            <img :src="props.active ? icon.setting.active : icon.setting.inactive" />
          </template>
        </van-tabbar-item>
      </van-tabbar>
	</div>
  </div>
</template>


<script scoped>
  export default {
    name: "tabbar",
    data() {
      return {
        active: 1,
        icon: {
          user:{
            active: 'https://img01.yzcdn.cn/vant/user-active.png',
            inactive: 'https://img01.yzcdn.cn/vant/user-inactive.png',
          },
          message:{
            active: 'http://www.moskid.asia/images/2021-12-2/message-active.png',
            inactive: 'http://www.moskid.asia/images/2021-12-2/message-inactive.png',
          },
          setting:{
            active: 'http://www.moskid.asia/images/2021-12-2/setting-active.png',
            inactive: 'http://www.moskid.asia/images/2021-12-2/setting-inactive.png',
          }
        },
        badge: ''
      };
    },
    methods: {
      routerTo (url,param){
        if(param == undefined){
            this.$router.push(url);
        }else{
            this.$router.push({name:url,params:param});
        }
      }
    },
	computed:{
		wsValue(){
			return this.$store.state.ws.value //把消息总数当计算属性
		}
	},
	watch: {
		wsValue(newVal,oldVal){
			this.badge = newVal; //监听计算属性
		}
	},
  }
</script>