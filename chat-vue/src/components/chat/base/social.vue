<template>
    <van-nav-bar
        title="好友搜索"
        left-text="返回"
        right-text="更多"
        left-arrow
        @click-left="onClickLeft"
        @click-right="onClickRight"
    />
    <div class="limiter p-content">
        <van-contact-edit
        is-edit
        show-set-default
        :contact-info="editingContact"
        set-default-label="设为默认联系人"
        @save="onSave"
        @delete="onDelete"
        />
    </div>
</template>

<script>
export default {
  data() {
    return {
      editingContact: {},
    };
  },
  methods: {
    onSave(contactInfo) {
      console.log('查找');
    },
    onDelete(contactInfo) {
      console.log('重置');
    },
    onClickLeft() {
      console.log('返回');
      this.$router.go(-1)
    },
    onClickRight() {
      console.log('按钮');
    },
  },
};
</script>

<style scoped>
@import url("/public/assets/css/login/util.css");
@import url("/public/assets/css/login/main.css");
.p-content{padding: 6%;}
</style>