<template>
  <div class="limiter">
    <van-dropdown-menu>
      <van-dropdown-item title="搜索" ref="item">
        <van-search
			v-model="value"
			show-action
			:left-icon="searchIcon"
			placeholder="请输入搜索关键词"
			@search="onSearch"
			@cancel="onCancel"
		/>
      </van-dropdown-item>
    </van-dropdown-menu>
    <van-contact-card
      type="add"
      @click="onAdd"
      add-text="添加好友"
      :icon="addIcon"
      :right-icon="rightIcon"
    />
    <van-contact-card
      type="edit"
      :name="login.nickname"
      :tel="login.email"
      :icon="login.avatar"
      :editable="false"
      add-text="个人名片"
    />
    <div v-for="(guy, index) in list" :key="index">
      <router-link
        :to="{ name: 'WrapperIndex', params: { hash: guy.mosUser.hash } }"
      >
        <van-contact-card
          type="edit"
          :icon="guy.mosUser.avatar"
          :name="guy.mosUser.nickname"
          :tel="guy.mosUser.phone"
          @click="onEdit"
          add-text="我的好友"
          :right-icon="rightIcon"
        />
      </router-link>
    </div>
  </div>
</template>

<!-- https://youzan.github.io/vant/#/zh-CN/index-bar -->
<script>
import md5 from "js-md5";
import { reactive } from "vue";
export default {
  name: "relation",
  emits: ["toggle"],
  data() {
    return {
	  value: '',
      addIcon: "/assets/img/add-icon.png",
      rightIcon: "/assets/images/go.png",
	  searchIcon: "/assets/img/lupa.png",
      login: {
        avatar: "/assets/images/avatar-01.jpg",
        nickname: "现在去请登录",
        email: "Your Email",
      },
      list: [],
      currentContact: {
        name: "张三",
        tel: "13000000000",
      },
    };
  },
  methods: {
	onSearch(val) {
      console.log(val);
    },
    onCancel() {
      this.$refs.item.toggle();
    },
    onEdit() {
      
    },
    onAdd() {
		this.$router.push('/chat/base/social')
	},
    async friend(value) {
      console.log(value);
      var result = await this.$api.friend(value);

      //失败
      if (result.status != 200) {
        console.log(result);
        //失败
        this.$notify({
          type: "warning",
          message: result.msg,
          duration: 1500,
        });
        return;
      }

      console.log(result.data);

      //成功
      this.$notify({
        type: "success",
        message: result.msg,
        duration: 1000,
        onClose: () => {
          //转化为响应式数据
          const obj = reactive(result.data);
          this.list = obj;
          //缓存
          this.$store.commit("listData", obj);
        },
      });
    },
  },
  created() {
    //触发父组件的事件 在登录界面不需要显示底部
    this.$emit("toggle", true);
    if (this.$cookies.get("LoginUser") != undefined) {
      this.login = this.$cookies.get("LoginUser");
    }
  },
  mounted() {
    let hash = md5(this.$store.state.auth.hash);
    //如果store缓存的list.data是对象
    if (typeof this.$store.state.list.data != "object") {
      //如果store中的hash加密等于传过来的加密hash
      if (Object.is(hash, this.$route.params.id)) {
        //获取登陆id
        var id = this.$cookies.get("LoginUser")["id"];
        this.friend(id);
      }
    } else {
      console.log(this.$store.state.list.data);
      this.list = this.$store.state.list.data;
    }
  },
};
</script>

<style scoped>
@import url("/public/assets/css/login/util.css");
@import url("/public/assets/css/login/main.css");
</style>