<template>
  <main class="chat-logs">
    <h2 class="visually-hidden">Инсайдеры Windows 10</h2>

    <header class="chat-logs__header">
      <img src="/assets/images/goback.png" @click="goBack()" />
      &nbsp;&nbsp;
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

            <p class="chat-logs__quote-text">
              На одном мониторе кнопки с текстом, на другом без
            </p>

            <button class="chat-logs__close-button" aria-label="Close"></button>
          </blockquote>
        </div>
      </a>

      <div class="chat-logs__logs">
        <div class="chat-logs__info-wrapper">
          <time class="chat-logs__info chat-logs__info_date"
            >March 23, 2018</time
          >
        </div>

        <div class="chat-logs__messages-wrapper">
          <img class="chat-logs__avatar" src="/assets/img/2.jpg" />

          <div class="chat-logs__message-group">
            <article id="message1" class="chat-logs__message">
              <h3
                class="
                  chat-logs__sender chat-logs__sender
                  сhat-logs__sender_ee4928
                "
              >
                <a href="#">Alexandr Petnitsky</a>
              </h3>

              <p class="chat-logs__message-text">
                На одном мониторе кнопки с текстом, на другом без
              </p>

              <span class="chat-logs__reply-button">Reply</span>

              <div class="chat-logs__details">
                <div class="chat-logs__edition-mark">edited</div>
                <time class="chat-logs__message-time">19:35</time>
              </div>
            </article>

            <article
              id="message2"
              class="chat-logs__message chat-logs__message_last_in_group"
            >
              <h3 class="chat-logs__sender chat-logs__sender_hidden">
                <a href="#">Alexandr Petnitsky</a>
              </h3>

              <p class="chat-logs__message-text">
                Кнопки приложений на панели задачи
              </p>

              <span class="chat-logs__reply-button">Reply</span>

              <div class="chat-logs__details">
                <time class="chat-logs__message-time">19:35</time>
              </div>
            </article>
          </div>
        </div>

        <div class="chat-logs__info-wrapper">
          <span class="chat-logs__info"
            ><a href="#" class="chat-logs__user-link">Diana Mar</a> joined the
            group via invite link</span
          >
        </div>

        <div class="chat-logs__messages-wrapper">
          <img class="chat-logs__avatar" src="/assets/img/3.jpg" />

          <div class="chat-logs__message-group">
            <article class="chat-logs__message">
              <h3 class="chat-logs__sender сhat-logs__sender_41a903">
                <a href="#">Niks</a>
              </h3>

              <div class="chat-logs__quote-wrapper">
                <a href="#message2">
                  <blockquote class="chat-logs__quote">
                    <cite class="chat-logs__quote-autor"
                      >Alexandr Petnitsky</cite
                    >

                    <p
                      class="
                        chat-logs__quote-text chat-logs__quote-text_oveflow
                      "
                    >
                      Кнопки приложений на панели задачи
                    </p>
                  </blockquote>
                </a>

                <p class="chat-logs__message-text">
                  Feedback, my dear friend, did you send it?
                </p>
              </div>

              <span class="chat-logs__reply-button">Reply</span>

              <div class="chat-logs__details">
                <time class="chat-logs__message-time">19:44</time>
              </div>
            </article>
          </div>
        </div>

        <div class="chat-logs__messages-wrapper">
          <img class="chat-logs__avatar" src="/assets/img/4.jpg" />

          <article class="chat-logs__message chat-logs__message_with_sticker">
            <h3 class="chat-logs__sender chat-logs__sender_hidden">
              Елена Лиафель
            </h3>

            <div>
              <img class="chat-logs__sticker" src="/assets/img/sticker.png" />
            </div>

            <div class="chat-logs__details">
              <time class="chat-logs__message-time">19:57</time>
            </div>
          </article>
        </div>

        <div class="chat-logs__messages-wrapper">
          <img class="chat-logs__avatar" src="/assets/img/5.jpg" />

          <div class="chat-logs__message-group chat-logs__message-group_my">
            <article class="chat-logs__message chat-logs__message_my">
              <h3 class="chat-logs__sender chat-logs__sender_hidden">me</h3>

              <div>
                <p class="chat-logs__message-text">
                  Тут был участник Stefan *** или как-то так. Видимо, его тут
                  больше нет, а мне вдруг приспичило спросить, действительно ли
                  его зовут Стефан. ¯\_(ツ)_/¯
                </p>
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
      <form class="chat-logs__posting-form" @submit="submitOff" disabled>
        <label class="chat-logs__attachment-button">
          <input type="file" class="chat-logs__file-input" />
        </label>

        <input
          class="chat-logs__posting-field"
          type="text"
          placeholder="Write a message..."
          ref="message"
        />

        <input
          class="chat-logs__posting-submit"
          type="submit"
          @click="sendMessage()"
        />
      </form>

      <div class="chat-logs__posting-features">
        <button class="chat-logs__emoji-button" aria-label="Emoticons"></button>

        <button
          class="chat-logs__voice-message-button"
          aria-label="Voice Message"
        ></button>
      </div>
    </footer>
  </main>
</template>


<script>
export default {
  name: "wrapper",
  emits: ["toggle"],
  data() {
    return {
      friendHash: "",
      socket: "", //Websocket对象
      connect: {
        addr: "43.225.158.156",
        port: 7000,
        uri: "ws",
      },
      logs: []
    };
  },
  methods: {
    async decorate(value,message) {
      var result = await this.$api.decorate(value);
      await this.waitResult(result,message)
    },
	  async waitResult(result,message){
		  if (result.status != 200) {
		  //失败
		  this.$notify({
		      type: 'warning',
		      message: '错误消息',
		      duration: 500
		  })
		  return
		}
		
		//成功
		this.$notify({
		    type: 'success',
		    message: '新的消息',
		    duration: 500,
		    onClose: () => {
				let log = eval({
				  hash: message.hash, //
				  avatar: result.data.avatar,
				  nickname: result.data.nickname,
				  content: message.content,
				  createTime: message.createTime, //
				});
        for(var i in this.logs){
          if(Object.is(this.logs[i].hash,log.hash)){
						this.logs[i] = log
            return
					}else{
						//到了最后都没有在dialogs找到这位朋友，证明它不存在dialogs
						if(Object.is(this.logs.length,(Number(i)+1))){
							//对象追加
							this.logs.push(log);
							this.$store.commit('refreshLogs',this.logs)
              return
						}
					}
        }
      }
		})
	  },
    goBack: function () {
      this.$router.go(-1);
      //如果Websocket打开，退出时必须关闭
      //socket.readyState:
      //0 - 表示连接尚未建立，1 - 表示连接已建立，可以进行通信，2 - 表示连接正在进行关闭，3 - 表示连接已经关闭或者连接不能打开
      if (this.socket.readyState === 1) {
        this.socket.close();
        this.socket.onclose = this.close;
      }
    },
    refresh: function(){
      for(var i in this.logs){
        if(Object.is(this.logs[i].hash,this.friendHash)){
          console.log(this.logs[i]);
        }
      }
    },
    async connectWebscoket(myHash, token) {
      if (!Object.is(myHash, "") && !Object.is(token, "")) {
        const addr = this.connect.addr;
        const port = this.connect.port;
        const uri = this.connect.uri;
        const token = this.$store.state.auth.token;
        //新建Websocket对象，并缓存到store

        this.socket = new WebSocket(
          `ws://${addr}:${port}/${uri}?token=${token}`
        );

        this.$store.commit("initSocket", this.socket);

        this.socket.onopen = this.open;
        this.socket.onerror = this.error;

        this.socket.onmessage = this.getMessage;
      }
    },
    open: function () {
      console.log("socket连接成功");
    },
    error: function () {
      console.log("连接错误");
      this.goBack();
      this.decorate({hash:data.hash},data)
    },
    getMessage: function (even) {
      //获取消息
      let data = JSON.parse(event.data);
      console.log(data);
    },
    send: function (message) {
      let data = eval({
        me: this.$store.state.auth.hash,
        friend: this.friendHash,
        content: message,
        type: 1,
      });
      console.log(data);
      //如果Websocket开启才能发送
      if (this.socket.readyState === 1) {
        this.socket.send(JSON.stringify(data));
      }
    },
    close: function () {
      console.log("socket已经关闭");
    },
    sendMessage: function () {
      //获取并发送消息
      console.log(this.$refs.message.value);
      this.send(this.$refs.message.value);
      this.$refs.message.value = "";
    },
    submitOff: function (e) {
      //阻止表单提交
      e.preventDefault();
    },
  },
  created() {
    //触发父组件的事件 在登录界面不需要显示底部
    this.$emit("toggle", false);
    //获取我的hsah与好友hash
    this.friendHash = this.$route.params.hash;
    console.log(this.friendHash);
    if (typeof this.$store.state.ws.socket != "object") {
      this.connectWebscoket(
        this.$store.state.auth.hash,
        this.$store.state.auth.token
      );
    }
    if (typeof this.$store.state.ws.logs == "object") {
      this.logs = this.$store.state.ws.logs
    }
  },
  mounted() {
    this.refresh();
    this.socket = this.$store.state.ws.socket;
    this.socket.onopen = this.open;
    this.socket.onerror = this.error;

    this.socket.onmessage = this.getMessage;
  },
  destroyed() {
    this.goBack();
  },
};
</script>

<style scoped>
@import url("/public/assets/css/chat/chat-logs.css");
</style>