<template>
    <div class="limiter">
        <div class="container-login100" style="background-image: url('/assets/images/img-01.jpg');">
            <div class="wrap-login100 p-t-190 p-b-30">
                <van-form @submit="login" class="login100-form validate-form">
                    <div class="login100-form-avatar">
                        <img src="/assets/images/avatar-01.jpg" alt="AVATAR">
                    </div>

                    <span class="login100-form-title p-t-20 p-b-45"></span>
                    
                    <!-- 用户名 -->
                    <div class="wrap-input100 validate-input m-b-10">
                        <van-field 
                            v-model="user.username"
                            name="username"
                            placeholder="请输入注册账号"
                            :left-icon="icon.user"
                            :rules="rules.username"
                        />
                    </div>
                    
                    <!-- 密码 -->
                    <div class="wrap-input100 validate-input m-b-10">
                        <van-field
                            type="password"
                            v-model="user.password"
                            name="password"
                            placeholder="请输入密码"
                            :left-icon="icon.pwd"
                            :rules="rules.password"
                        />
                    </div>

                    <!-- 提交按钮 -->
                    <div class="container-login100-form-btn p-t-10">
                        <van-button class="login100-form-btn" type="primary" native-type="submit">
                            登 录
                        </van-button>
                    </div>

                    <div class="text-center w-full p-t-25">
                        <router-link class="txt1" to="/user/base/register">
                            立即注册
                        </router-link>
                    </div>
                </van-form>
            </div>
        </div>
    </div>
</template>


<script>
	import md5 from 'js-md5';
    export default {
        name: 'BaseRegister',
        emits: ['toggle'],
        created()
        {
            //触发父组件的事件 在登录界面不需要显示底部
            this.$emit('toggle', false)
        },
        data()
        {
            return {
                user: {
                    username: '',
                    password: ''
                },
                //表单的验证规则
                rules: {
                    //邮箱
                    username: [
                        {
                            required: true,  //必填
                            message: '请输入用户名',
                        },
                    ],
                    //密码
                    password: [
                        {
                            required: true, //必填
                            message: '请输入密码'
                        }
                    ]
                },
                icon:{
                    user: "/assets/images/f4.png",
                    pwd: "/assets/images/f1.png"
                }
            }
        },
        methods:{
            async login(value)
            {
				console.log(value)
                var result = await this.$api.login(value)
                
                //失败
                if(result.status != 200)
                {	
					console.log(result);
                    //失败
                    this.$notify({
                        type: 'warning',
                        message: '登录失败',
                        duration: 1500
                    })
                    return
                }
				
				console.log(result);
                
				
				//将机密的数据token与hash存储到store
				this.$store.commit('updateToken', result.data.token);
				this.$store.commit('bindHash',result.data.hash);
				
				//剔除该json对象机密的数据
				delete result.data.token;
				delete result.data.hash;
				
				//成功 设置cookie信息
				this.$cookies.set('LoginUser', result.data)
				
                this.$notify({
                    type: 'success',
                    message: '登陆成功',
                    duration: 1000,
                    onClose: () => {
						let hash = md5(this.$store.state.auth.hash)
                        this.$router.replace({name: 'RelationIndex', params: {id: hash}})
                    }
                })
                return
            }
        }
    }
</script>

<style>
    @import url('/public/assets/css/login/util.css');
    @import url('/public/assets/css/login/main.css');
</style>