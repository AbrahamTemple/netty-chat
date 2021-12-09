<template>
    <div class="limiter">
        <div class="container-login100" style="background-image: url('/assets/images/img-01.jpg');">
            <div class="wrap-login100 p-t-190 p-b-30">
                <van-form @submit="register" class="login100-form validate-form">
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

                    <!-- 邮箱 -->
                    <div class="wrap-input100 validate-input m-b-10">
                        <van-field
                            v-model="user.email"
                            name="email"
                            placeholder="请输入邮箱"
                            :left-icon="icon.mail"
                            :rules="rules.email"
                        />
                    </div>

                    <!-- 手机号 -->
                    <div class="wrap-input100 validate-input m-b-10">
                        <van-field
                            v-model="user.phone"
                            name="phone"
                            placeholder="请输入手机号"
                            :left-icon="icon.phone"
                            :rules="rules.phone"
                        />
                    </div>

                    <!-- 提交按钮 -->
                    <div class="container-login100-form-btn p-t-10">
                        <van-button class="login100-form-btn" type="primary" native-type="submit">
                            注 册
                        </van-button>
                    </div>

                    <div class="text-center w-full p-t-25">
                        <router-link class="txt1" to="/user/base/login">
                            立即登录
                        </router-link>
                    </div>
                </van-form>
            </div>
        </div>
    </div>
</template>


<script>
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
                    password: '',
                    emial: '',
                    phone: ''
                },
                //表单的验证规则
                rules: {
                     //用户名
                    username: [
                        {
                            required: true,  //必填
                            message: '请输入用户名',
                        },
                    ],
                    //邮箱
                    email: [
                        {
                            required: true,  //必填
                            message: '请输入邮箱',
                        },
                        {
                            message: '请输入正确邮箱地址',
                            validator(value)
                            {
                                //正则表达式
                                var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$")

                                return reg.test(value)
                            }
                        },
                    ],
                    //密码
                    password: [
                        {
                            required: true, //必填
                            message: '请输入密码'
                        }
                    ],
                    //用户名
                    phone: [
                        {
                            required: true,  //必填
                            message: '请输入手机号',
                        },
                    ],
                },
                icon:{
                    user: "/assets/images/f4.png",
                    pwd: "/assets/images/f1.png",
                    mail: "/assets/images/sc.png",
                    phone: "/assets/images/f2.png"
                }
            }
        },
        methods:{
            //让方法先变成异步
            async register(values)
            {
                //同步的请求
                var result = await this.$api.register(values)

                if(result.code)
                {
                    //提醒
                    this.$notify({
                        type: 'success',
                        message: "注册成功",
                        duration: 1500
                    })

                    setTimeout(() => {
                        //成功
                        this.$router.push('/user/base/login')
                        return
                    }, 1500)
                }else
                {
                    //失败
                    this.$notify({
                        type: 'warning',
                        message: "注册功能未开放",
                        duration: 1500
                    })
                    return
                }
            }
        }
    }
</script>

<style>
    @import url('/public/assets/css/login/util.css');
    @import url('/public/assets/css/login/main.css');
</style>