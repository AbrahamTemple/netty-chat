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
		<div>
			<router-link :to="{name:'WrapperIndex', params:{hash:'123456'}}">
			<li class="dialogs__item">
				<img class="dialogs__avatar" src="/assets/img/1.jpg">
				
				<article class="dialogs__card">
					<h3 class="dialogs__title">Земля китов</h3>
					
					<time class="dialogs__last-message-time">22:04</time>
					
					<p class="dialogs__preview">Photo, Знаменитый норвежский полярный исследователь Руал Амундсен и две его приемные дочери: слева одиннадцатилетняя Камилла Карпендель — родная дочь австралийского торговца Чарли Карпендель, осевшего на Чукотке в 1904 году, и справа четырехлетняя Каконита Амундсен — дочь чукчи Какота, уроженца чукотского Уэлена, участника экспедиции. Этих девочек полярник увез из Чукотки в Норвегию в 1921 году.</p>
				</article>
			</li>
			</router-link>
		</div>
  		
		<div>
			<li class="dialogs__item dialogs__item_active">
				<img class="dialogs__avatar" src="/assets/img/6.jpg">
				
				<article class="dialogs__card">
					<h3 class="dialogs__title">Инсайдеры Windows 10</h3>
					
					<time class="dialogs__last-message-time">21:03</time>
					
					<p class="dialogs__preview">You: Тут был участник Stefan *** или как-то так. Видимо, его тут больше нет, а мне вдруг приспичило спросить, действительно ли его зовут Стефан. ¯\_(ツ)_/¯</p>
				</article>
			</li>
		</div>
  		
		<div>
			<li class="dialogs__item">
				<img class="dialogs__avatar" src="/assets/img/7.jpg">
				
				<article class="dialogs__card">
					<h3 class="dialogs__title">Telegram</h3>
					
					<time class="dialogs__last-message-time">Tue</time>
					
					<div class="dialogs__preview-wrapper">
						<p class="dialogs__preview">Актуальная информация о борьбе с COVID-19 в России доступна в канале https://t.me/stopcoronavirusrussia.</p>				
					</div>
				</article>
			</li>
		</div>
  	</ul>
  </aside>
</template>


<script>
  import md5 from 'js-md5';
  export default {
    name: "dialogs",
	data(){
		return{
			list: [],
		}
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
			
			//成功
			this.$notify({
			    type: 'success',
			    message: result.msg,
			    duration: 1000,
			    onClose: () => {
					this.list = result.data;
					console.log(result.data);
			    }
			})

		}
	},
	mounted(){
		let hash = md5(this.$store.state.auth.hash)
		//如果store中的hash加密等于传过来的加密hash
		if(hash == this.$route.params.id){
			//获取登陆id
			// var id = this.$cookies.get('LoginUser')['id']
			var id = 1
			this.friend(id)
		}
	}
  }
</script>

<style scoped>
    @import url('/public/assets/css/chat/dialogs.css');
</style>