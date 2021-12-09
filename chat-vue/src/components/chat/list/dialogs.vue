<template>
  <aside class="dialogs">
    <h2 class="visually-hidden">Mos加密通信</h2>

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
        {
          hash: this.$store.state.auth.hash,
          avatar: "/assets/img/7.jpg",
          nickname: "Mos Chat",
          content:
            "关联系统通知提醒助手",
          createTime: "TUE",
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
				  avatar: result.data.avatar,
				  nickname: result.data.nickname,
				  content: message.content,
				  createTime: message.createTime, //
				});
        for(var i in this.logs){
			if(Object.is(this.logs[i].hash,log.hash)){
				this.logs[i] = log
				this.$store.commit('pushWapper',{hash:message.hash,data:log});
				return
			}else{
				//到了最后都没有在dialogs找到这位朋友，证明它不存在dialogs
				if(Object.is(this.logs.length,(Number(i)+1))){
				//对象追加
					this.logs.push(log);
					this.$store.commit('refreshLogs',this.logs)
					this.$store.commit('calculateVal',this.logs.length)
					this.$store.commit('pushWapper',{hash:message.hash,data:log});
					return
				}
			}
        }
      }
		})
	},
    async connectWebscoket(myHash, token) {
      if (!Object.is(myHash, "") && !Object.is(token, "")) {
        const addr = this.connect.addr;
        const port = this.connect.port;
        const uri = this.connect.uri;
        const token = this.$store.state.auth.token;

        //判断当前页面请求是http还是https
        var ishttps = 'https:' == document.location.protocol ? true : false;

        if (ishttps) {
          this.socket = new WebSocket(
            // `wss://${addr}:${port}/${uri}?token=${token}`
            `wss://www.moskid.asia/${uri}?token=${token}`
          );
        } else {
          this.socket = new WebSocket(
            // `ws://${addr}:${port}/${uri}?token=${token}`
            `ws://www.moskid.asia/${uri}?token=${token}`
          );
        }
        //新建Websocket对象，并缓存到store
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
	    this.decorate({hash:data.hash},data)
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
    if(typeof this.$store.state.ws.logs == 'string'){
      this.$store.commit('refreshLogs',this.logs)
    }
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
