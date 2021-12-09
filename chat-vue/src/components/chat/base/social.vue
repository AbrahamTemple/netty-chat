<template>
  <div>
    <van-nav-bar
        title="好友搜索"
        left-text="返回"
        right-text="更多"
        left-arrow
        @click-left="onClickLeft"
        @click-right="onClickRight"
    />
    <div class="limiter p-content">
      <van-form @submit="onSubmit">
        <van-field
          v-model="nickname"
          name="nickname"
          label="用户名"
          placeholder="输入对方大概的昵称"

        />
        <van-field name="switch" label="自动添加">
          <template #input>
            <van-switch v-model="switchChecked" size="20" active-color="#ee0a24" inactive-color="#dcdee0"/>
          </template>
        </van-field>
        <div style="margin: 16px;">
          <van-button round block native-type="submit" type="warning" class="btn">搜查朋友</van-button>
        </div>
      </van-form>
    </div>
    
    <van-card
        :desc="list.content"
        :title="list.nickname"
        :thumb="list.avatar">
        <template #tags>
          <van-tag plain type="danger">{{list.sex}}</van-tag>
        </template>
    </van-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      login: {},
      switchChecked: true,
      nickname: '',
      list:[]
    };
  },
  methods: {
    onClickLeft: function(){
      this.$router.go(-1);
    },
    async onSubmit(values) {
      console.log('submit', values);
      if(!Object.is(values.nickname,'')){
        var result = await this.$api.strange({nickname:this.nickname})
        console.log(result);
        if(result.status == 200){
          this.$notify({
            type: 'success',
            message: '搜索成功',
            duration: 1000,
            onClose: () => {
						  console.log(result.data);
              this.list = result.data
            }
          })
          if(values.switch){
                var result2 = await this.$api.social({userid:this.login.id,friendid:result.data.id})
                console.log(result2);
                if(result.status == 200){
                  this.$notify({
                    type: 'primary',
                    message: '新朋友添加成功',
                    duration: 1500,
                    onClose: () => {
                      console.log(result2);
                      this.list = result.data
                    }
                })
                return
                }
          }
          return
        }
      }
      //失败
      this.$notify({
        type: 'warning',
        message: '该用户不存在',
        duration: 1000
      })
      return
    },
  },
  created(){
    this.login = this.$cookies.get('LoginUser');
  }
};
</script>

<style scoped>
@import url("/public/assets/css/login/util.css");
@import url("/public/assets/css/login/main.css");
.p-content{padding: 6%;}
.btn{background-color: blueviolet;}
</style>