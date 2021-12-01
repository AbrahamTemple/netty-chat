<template>
  <aside class="dialogs">
    <h2 class="visually-hidden">Диалоги</h2>

    <header class="dialogs__header">
      <button
        class="dialogs__burger-button"
        id="menu-btn"
        aria-label="Menu"
      ></button>

      <form class="dialogs__search-form">
        <input
          type="search"
          class="dialogs__search-field"
          placeholder="Search"
        />
      </form>
    </header>
    <ul class="dialogs__list">
      <div v-for="(log, index) in logs" :key="index" @click="activeOn(index)">
        <!-- <router-link :to="{name:'WrapperIndex', params:{hash:log.hash}}"> -->
        <li
          :class="{
            dialogs__item: true,
            dialogs__item_active: index == select,
          }"
        >
          <img class="dialogs__avatar" :src="log.avatar" />

          <article class="dialogs__card">
            <h3 class="dialogs__title">{{ log.nickname }}</h3>

            <time class="dialogs__last-message-time">{{ log.createTime }}</time>

            <p class="dialogs__preview">{{ log.content }}</p>
          </article>
        </li>
        <!-- </router-link> -->
      </div>
    </ul>
  </aside>
</template>


<script>
import md5 from "js-md5";
import { reactive } from "vue";
export default {
  name: "dialogs",
  emits: ["toggle"],
  data() {
    return {
      list: [],
      /* 
				me -> friend -> { message }
			 */
      logs: [
        // {
        //   hash: "000000d6abb6c484ded1fba40257b8280962473974ac2d319d7038d6928dd2ee", //
        //   avatar: "/assets/img/1.jpg",
        //   nickname: "Земля китов",
        //   content:
        //     "Photo, Знаменитый норвежский полярный исследователь Руал Амундсен и две его приемные дочери: слева одиннадцатилетняя Камилла Карпендель — родная дочь австралийского торговца Чарли Карпендель, осевшего на Чукотке в 1904 году, и справа четырехлетняя Каконита Амундсен — дочь чукчи Какота, уроженца чукотского Уэлена, участника экспедиции. Этих девочек полярник увез из Чукотки в Норвегию в 1921 году.", //
        //   createTime: "22:04", //
        // },
        {
          hash: "000cd1adb9a3b81ee6f84a8f4b460b3c8a192e998d0b91cf8209b01325e84ac", //
          avatar: "/assets/img/6.jpg",
          nickname: "Инсайдеры Windows 10",
          content:
            "You: Тут был участник Stefan *** или как-то так. Видимо, его тут больше нет, а мне вдруг приспичило спросить, действительно ли его зовут Стефан. ¯_(ツ)_/¯", //
          createTime: "21:03", //
        },
        {
          hash: "00000f57bad0fbb06a176ce8e582694ff27cf6b0ec1d1c3b896397014d79a82d", //
          avatar: "/assets/img/7.jpg",
          nickname: "Telegram",
          content:
            "Актуальная информация о борьбе с COVID-19 в России доступна в канале https://t.me/stopcoronavirusrussia.", //
          createTime: "TUE", //
        },
      ],
      socket: "", //Websocket对象
      connect: {
        addr: "43.225.158.156",
        port: 7000,
        uri: "ws",
      },
      select: 0,
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
				  avatar: "/assets/img/6.jpg",
				  nickname: result.data.nickname,
				  content: message.content,
				  createTime: message.createTime, //
				});
				var _this = this
				Reflect.ownKeys(_this.logs).forEach(function(i){
					if(Object.is(_this.logs[i].hash,log.hash)){
						_this.logs[i] = log
					}else{
						//到了最后都没有在dialogs找到这位朋友，证明它不存在dialogs
						if(Object.is(i,'length')){
							//对象追加
							_this.logs.push(log);
							_this.$store.commit('refreshLogs',_this.logs)
						}
					}
				})
				
		    }
		})
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
    },
    getMessage: function (even) {
      //获取消息
      let data = JSON.parse(event.data);
      console.log(data);
	  // this.decorate({hash:data.hash},data)
    },
    close: function () {
      console.log("socket已经关闭");
    },
    activeOn(index) {
      this.select = index;
      var _this = this;
      setTimeout(function () {
        _this.$router.push({
          name: "WrapperIndex",
          params: { hash: _this.logs[index].hash },
        });
        //缓存active的标记位置
        _this.$store.commit("selectActive", _this.select);
      }, 300);
    },
  },
  created() {
    //触发父组件的事件 在登录界面需要显示底部
    this.$emit("toggle", true);
	
    this.connectWebscoket(
      this.$store.state.auth.hash,
      this.$store.state.auth.token
    );
	
  },
  mounted() {
	//读取缓存的dialogs列表
	if(typeof this.$store.state.ws.logs != 'string'){
		this.logs = this.$store.state.ws.logs
	}
    //如果标记的位置不为空，赋值
    if (typeof this.$store.state.list.active != "string") {
      this.select = this.$store.state.list.active;
    }
  },
  destroyed() {
    this.$store.dispatch("closeSocket", undefined);
  },
};
</script>

<style scoped>
@import url("/public/assets/css/chat/dialogs.css");
</style>