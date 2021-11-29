<template>
	<main class="chat-logs">
		<h2 class="visually-hidden">Инсайдеры Windows 10</h2>
		
		<header class="chat-logs__header">
			<article class="chat-logs__chat-info">
				<h3 class="chat-logs__chat-title">Инсайдеры Windows 10</h3>
			
				<p class="chat-logs__chat-description">749 members</p>
			</article>

			<div class="chat-logs__buttons">
				<button class="chat-logs__search-button" aria-label="Search"></button>
				
				<button class="chat-logs__info-button" aria-label="Info"></button>
				
				<button class="chat-logs__more-button" aria-label="More"></button>
			</div>
		</header>
		
		<div class="chat-logs__logs-wrapper">
			<a href="#message1">
				<div class="chat-logs__pinned-message">
					<blockquote class="chat-logs__quote chat-logs__quote_pinned">
						<cite class="chat-logs__quote-autor">Alexandr Petnitsky</cite>

						<p class="chat-logs__quote-text">На одном мониторе кнопки с текстом, на другом без</p>

						<button class="chat-logs__close-button" aria-label="Close"></button>
					</blockquote>
				</div>
			</a>
			
			<div class="chat-logs__logs">
				<div class="chat-logs__info-wrapper">
					<time class="chat-logs__info chat-logs__info_date">March 23, 2018</time>
				</div>

				<div class="chat-logs__messages-wrapper">
					<img class="chat-logs__avatar" src="/assets/img/2.jpg">

					<div class="chat-logs__message-group">
						<article id="message1" class="chat-logs__message">
							<h3 class="chat-logs__sender chat-logs__sender сhat-logs__sender_ee4928">
								<a href=#>Alexandr Petnitsky</a>
							</h3>

							<p class="chat-logs__message-text">На одном мониторе кнопки с текстом, на другом без</p>

							<span class="chat-logs__reply-button">Reply</span>

							<div class="chat-logs__details">
								<div class="chat-logs__edition-mark">edited</div>
								<time class="chat-logs__message-time">19:35</time>
							</div>
						</article>

						<article id="message2" class="chat-logs__message chat-logs__message_last_in_group">
							<h3 class="chat-logs__sender chat-logs__sender_hidden">
								<a href=#>Alexandr Petnitsky</a>						
							</h3>

							<p class="chat-logs__message-text">Кнопки приложений на панели задачи</p>

							<span class="chat-logs__reply-button">Reply</span>

							<div class="chat-logs__details">
								<time class="chat-logs__message-time">19:35</time>
							</div>
						</article>		
					</div>
				</div>

				<div class="chat-logs__info-wrapper">
					<span class="chat-logs__info"><a href="#" class="chat-logs__user-link">Diana Mar</a> joined the group via invite link</span>
				</div>

				<div class="chat-logs__messages-wrapper">
					<img class="chat-logs__avatar" src="/assets/img/3.jpg">
					
					<div class="chat-logs__message-group">
						<article class="chat-logs__message">
							<h3 class="chat-logs__sender сhat-logs__sender_41a903">
									<a href=#>Niks</a>
							</h3>

							<div class="chat-logs__quote-wrapper">
								<a href="#message2">
									<blockquote class="chat-logs__quote">
										<cite class="chat-logs__quote-autor">Alexandr Petnitsky</cite>

										<p class="chat-logs__quote-text chat-logs__quote-text_oveflow">Кнопки приложений на панели задачи</p>
									</blockquote>
								</a>

								<p class="chat-logs__message-text">Feedback, my dear friend, did you send it?</p>
							</div>

							<span class="chat-logs__reply-button">Reply</span>

							<div class="chat-logs__details">
								<time class="chat-logs__message-time">19:44</time>
							</div>
						</article>
					</div>
				</div>
					

				<div class="chat-logs__messages-wrapper">
					<img class="chat-logs__avatar" src="/assets/img/4.jpg">

					<article class="chat-logs__message chat-logs__message_with_sticker">

						<h3 class="chat-logs__sender chat-logs__sender_hidden">Елена Лиафель</h3>

						<div>
							<img class="chat-logs__sticker" src="/assets/img/sticker.png">
						</div>

						<div class="chat-logs__details">
							<time class="chat-logs__message-time">19:57</time>
						</div>
					</article>
				</div>

				<div class="chat-logs__messages-wrapper">
					<img class="chat-logs__avatar" src="/assets/img/5.jpg">
					
					<div class="chat-logs__message-group chat-logs__message-group_my">
						<article class="chat-logs__message chat-logs__message_my">

							<h3 class="chat-logs__sender chat-logs__sender_hidden">me</h3>

							<div>
								<p class="chat-logs__message-text">Тут был участник Stefan *** или как-то так. Видимо, его тут больше нет, а мне вдруг приспичило спросить, действительно ли его зовут Стефан. ¯\_(ツ)_/¯</p>
							</div>

							<div class="chat-logs__details">
								<div class="chat-logs__edition-mark">edited</div>

								<time class="chat-logs__message-time">21:03</time>

								<div class="chat-logs__read-mark"></div>
							</div>
						</article>
					</div>
				</div>
			</div>
		</div>

		
		<footer class="chat-logs__posting">
			<form class="chat-logs__posting-form">
				<label class="chat-logs__attachment-button">
					<input type="file" class="chat-logs__file-input">
				</label>
				
				<input class="chat-logs__posting-field" type="text" placeholder="Write a message...">
				
				<input class="chat-logs__posting-submit" type="submit">
			</form>
			
			<div class="chat-logs__posting-features">
				<button class="chat-logs__emoji-button" aria-label="Emoticons"></button>
				
				<button class="chat-logs__voice-message-button" aria-label="Voice Message"></button>
			</div>
		</footer>
	</main>
</template>


<script>
  export default {
    name: "wrapper",
	emits: ['toggle'],
	data(){
		return{
			friendHash: '',
			socket: '',
			connect: {
				addr: '43.225.158.156',
				port: 7000,
				uri: 'ws'
			}
		}
	},
	methods:{
		async connectWebscoket(myHash, token){
			if(!Object.is(myHash,'') && !Object.is(token,'')){
				const addr = this.connect.addr;
        		const port = this.connect.port;
        		const uri = this.connect.uri;
        		const token = this.$store.state.auth.token;
        		//新建socket对象
        		this.socket = new WebSocket(`ws://${addr}:${port}/${uri}?token=${token}`);

				this.socket.onopen = this.open
				this.socket.onerror = this.error
				this.socket.onclose = this.close
				
				this.socket.onmessage = this.getMessage
			}
		},
		open: function () {
            console.log("socket连接成功")
        },
        error: function () {
            console.log("连接错误")
        },
        getMessage: function (even) {
            let data = JSON.parse(event.data);
           	console.log(data);
        },
        send: function () {
            let data  = eval({
                "me" : this.$store.state.auth.hash,
                "friend"  : this.friendHash,
                "content" : '',
                "type": 1
            });
            console.log(data);
            this.socket.send(JSON.stringify(data))
        },
        close: function () {
            console.log("socket已经关闭")
        }
	},
	created()
	{
	    //触发父组件的事件 在登录界面不需要显示底部
	    this.$emit('toggle', false)
		//获取我的hsan与好友hash
		this.friendHash = this.$route.params.hash
		console.log(this.friendHash);
		this.connectWebscoket(this.$store.state.auth.hash, this.$store.state.auth.token)
	},
  }
</script>

<style scoped>
    @import url('/public/assets/css/chat/chat-logs.css');
</style>