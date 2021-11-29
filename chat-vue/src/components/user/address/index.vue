<template>
  <!-- 导航栏 -->
  <van-nav-bar 
    title="我的收货地址"
    left-text="返回"
    left-arrow
    @click="Back"
  />

  <!-- 收货列表 -->
  <van-address-list
    v-model="AddressDefault"
    :list="AddressList"
    default-tag-text="默认"
    @add="onAdd"
  />


</template>


<script>
  export default {
    name: "AddressIndex",
    emits: ['toggle'], //定义说明父组件的自定义事件
    data()
    {
      return {
        LoginUser: this.$cookies.get('LoginUser'),
        AddressList: [], //收货列表
        AddressDefault: 0,  //默认的收货地址
      }
    },
    created()
    {
      //触发父组件的事件
      this.$emit('toggle', false)

      //一进入就去请求收货地址列表
      this.AddressData()
    },
    methods:{
      Back()
      {
        //返回上一层界面
        this.$router.go(-1)
      },
      async AddressData()
      {
        var result = await this.$api.AddressIndex({userid: this.LoginUser.id})

        var data = result.data
        var list = []

		console.log(result);
        //先判断是否有收货地址
        if(data.length <= 0)
        {
          //没有收货地址
          this.$notify({
            message:'暂无收货地址',
            duration:1000,
          })

          return false
        }
		
        //找出默认收货地址
        for(var index in data)
        {
		  if(Object.is(index,'length')){
			  continue;
		  }
		  let item = data[index];
          if(item.status == 1)
          {
            this.AddressDefault = item.id
          }

          //组装收货地址列表
          var address = {
            id: item.id,
            name: item.consignee,
            tel: item.mobile,
            isDefault: item.status == 1 ? true : false
          }
		
          //详细地址
          var info = ''

          if(item.province)
          {
            info += `${item.provinces.name}-`
          }

          if(item.city)
          {
            info += `${item.citys.name}-`
          }

          if(item.district)
          {
            info += `${item.districts.name} `
          }

          if(item.address)
          {
            info += `${item.address}`
          }

          address.address = info
          list.push(address)
        }

        this.AddressList = list
      },
      onAdd()
      {
        this.$router.push('/user/address/add')
      }
    }
  }
</script>