<template>
	<!-- <van-index-bar :index-list="indexList" highlight-color="#67C23A"> -->
	  <!-- <div v-for="(v,i) in indexList" :key="i"> -->
		  <!-- <van-index-anchor index="1">好友列表</van-index-anchor> -->
		 
		  
<!-- 	  </div> -->
	<!-- </van-index-bar> -->
	<div class="limiter">
		<div v-for="(guy,index) in list" :key="index">
			<router-link :to="{name:'WrapperIndex', params:{hash:guy.mosUser.hash}}">
			<van-cell
				:id="guy.friendid"
				:icon="guy.mosUser.avatar"
				:title="guy.mosUser.nickname"
				is-link size="large">
			</van-cell>
			</router-link>
		</div>
		<!-- <van-cell 
			v-for="(guy,index) in list" :key="index"
			:id="guy.friendid"
			:title-class="{cell:true}"
			:value="guy.mosUser.nickname" 
			is-link size="large">
		  <view slot="title">
		    <view class="van-cell-text">{{guy.mosUser.nickname}}</view>
		    <van-tag type="danger">标签</van-tag>
		  </view>
		  <van-image
		    width="1.5rem"
		    height="1.5rem"
		    fit="contain"
		    slot="icon"
		    :src="guy.mosUser.avatar"
		  />
		</van-cell> -->
	</div>
</template>

<!-- https://youzan.github.io/vant/#/zh-CN/index-bar -->
<script>
  import md5 from 'js-md5';
  import {reactive} from 'vue';
  export default {
    name: "relation",
	emits: ["toggle"],
	data() {
	    return {
	      indexList: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
		  list: []
	    };
	},
	methods:{
		async friend(value)
		{
			console.log(value)
		    var result = await this.$api.friend(value)
		    
		    //失败
		    if(result.status != 200)
		    {	
				console.log(result);
		        //失败
		        this.$notify({
		            type: 'warning',
		            message: result.msg,
		            duration: 1500
		        })
		        return
		    }
			
			console.log(result.data);

			//成功
			this.$notify({
			    type: 'success',
			    message: result.msg,
			    duration: 1000,
			    onClose: () => {
					//转化为响应式数据
					const obj = reactive(result.data); 
					this.list = obj;
					//缓存
					this.$store.commit('listData',obj)
			    }
			})
	
		}
	},
	created(){
		//触发父组件的事件 在登录界面不需要显示底部
    	this.$emit("toggle", true);
	},
	mounted(){
		let hash = md5(this.$store.state.auth.hash)
		//如果store缓存的list.data是对象
		if(typeof this.$store.state.list.data != 'object'){
			//如果store中的hash加密等于传过来的加密hash
			if(Object.is(hash,this.$route.params.id))
			{
				//获取登陆id
				var id = this.$cookies.get('LoginUser')['id']
				this.friend(id)
			}
		} else {
			console.log(this.$store.state.list.data);
			this.list = this.$store.state.list.data;
		}
		
	}
  }
</script>

<style scoped>
	@import url('/public/assets/css/login/util.css');
	@import url('/public/assets/css/login/main.css');
/* 	.cell{
		font-size: 1.5rem;
		line-height: 3rem;
		width: 100vw;
	} */
</style>