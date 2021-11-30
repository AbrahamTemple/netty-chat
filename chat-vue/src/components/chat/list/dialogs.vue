<template>
  <aside class="dialogs">
  	<h2 class="visually-hidden">Диалоги</h2>
  	
  	<header class="dialogs__header">
  		<button class="dialogs__burger-button" id="menu-btn" aria-label="Menu"></button>
  		
  		<form class="dialogs__search-form">
  			<input type="search" class="dialogs__search-field" placeholder="Search">
  		</form>
  	</header>
  	<ul class="dialogs__list">
		<div v-for="(log,index) in logs" :key="index" @click="activeOn(index)">
			<!-- <router-link :to="{name:'WrapperIndex', params:{hash:log.hash}}"> -->
			<li :class="{dialogs__item:true,dialogs__item_active:index == select}">
				<img class="dialogs__avatar" :src="log.avatar">
				
				<article class="dialogs__card">
					<h3 class="dialogs__title">{{log.nickname}}</h3>
					
					<time class="dialogs__last-message-time">{{log.createTime}}</time>
					
					<p class="dialogs__preview">{{log.content}}</p>
				</article>
			</li>
			<!-- </router-link> -->
		</div>
  	</ul>
  </aside>
</template>


<script>
  import md5 from 'js-md5';
  import { reactive } from 'vue'; 
  export default {
    name: "dialogs",
	emits: ['toggle'],
	data(){
		return{
			list: [],
			/* 
				me -> friend -> { message }
			 */
			logs: [
				{
					hash: '000000d6abb6c484ded1fba40257b8280962473974ac2d319d7038d6928dd2ee', //
					avatar: '/assets/img/1.jpg',
					nickname: 'Земля китов',
					content: 'Photo, Знаменитый норвежский полярный исследователь Руал Амундсен и две его приемные дочери: слева одиннадцатилетняя Камилла Карпендель — родная дочь австралийского торговца Чарли Карпендель, осевшего на Чукотке в 1904 году, и справа четырехлетняя Каконита Амундсен — дочь чукчи Какота, уроженца чукотского Уэлена, участника экспедиции. Этих девочек полярник увез из Чукотки в Норвегию в 1921 году.', //
					createTime: '22:04' //
				},
				{
					hash: '000cd1adb9a3b81ee6f84a8f4b460b3c8a192e998d0b91cf8209b01325e84ac', //
					avatar: '/assets/img/6.jpg',
					nickname: 'Инсайдеры Windows 10',
					content: 'You: Тут был участник Stefan *** или как-то так. Видимо, его тут больше нет, а мне вдруг приспичило спросить, действительно ли его зовут Стефан. ¯\_(ツ)_/¯', //
					createTime: '21:03' //
				},
				{
					hash: '00000f57bad0fbb06a176ce8e582694ff27cf6b0ec1d1c3b896397014d79a82d', //
					avatar: '/assets/img/7.jpg',
					nickname: 'Telegram',
					content: 'Актуальная информация о борьбе с COVID-19 в России доступна в канале https://t.me/stopcoronavirusrussia.', //
					createTime: 'TUE' //
				}
			],
			select: 0
		}
	},
	methods:{
		async message(value)
		{
			console.log(value)
		    var result = await this.$api.message(value)
		    
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
			
			//成功
			this.$notify({
			    type: 'success',
			    message: result.msg,
			    duration: 500,
			    onClose: () => {
					const obj = reactive(result.data)
					this.list = obj;
					console.log(obj);
			    }
			})

		},
		activeOn (index)
		{
			this.select = index
			var _this = this
			setTimeout(function(){
				_this.$router.push({name:'WrapperIndex', params:{hash:_this.logs[index].hash}})
				//缓存active的标记位置
				_this.$store.commit('selectActive',_this.select)
			},300);
		}
	},
	created(){
		//触发父组件的事件 在登录界面需要显示底部
		this.$emit('toggle', true)
	},
	mounted(){
		//如果标记的位置不为空，赋值
		if(typeof this.$store.state.list.active != 'string'){
			this.select = this.$store.state.list.active
		}
	}
  }
</script>

<style scoped>
    @import url('/public/assets/css/chat/dialogs.css');
</style>