<template>
  <!-- 头部 -->
  <div class="topBox">
    <!-- logo -->
    <div class="L">
      <a href="#">LOGO</a>
    </div>

    <!-- 搜索 -->
    <div class="C">
      <div class="saerchbox">
        <input name="" type="text" placeholder="请输入您要搜索的内容"><img src="/assets/images/ss.png">
      </div>
    </div>

    <!-- 菜单 -->
    <div class="R">
      <img src="/assets/images/tr.png">
    </div>
  </div>
  <div class="clear"></div>

  <!-- 轮播图 -->
  <div class="aui-m-slider">
    <div class="m-slider" data-ydui-slider>
      <div class="slider-wrapper">
        <div class="slider-item"> <a href="javascript:;"> <img src="/assets/images/banner.jpg"> </a> </div>
        <div class="slider-item"> <a href="javascript:;"> <img src="/assets/images/banner.jpg"> </a> </div>
        <div class="slider-item"> <a href="javascript:;"> <img src="/assets/images/banner.jpg"> </a> </div>
        <div class="slider-item"> <a href="javascript:;"> <img src="/assets/images/banner.jpg"> </a> </div>
      </div>
      <div class="slider-pagination"></div>
    </div>
  </div>
  <div class="clear"></div>

  <!-- 商品分类表 -->
  <div class="lqgwBox">
    <ul>
      <li><a href="list.html"><img src="/assets/images/icon_1.png"><p>桌子</p></a></li>
      <li><a href="list.html"><img src="/assets/images/icon_2.png"><p>椅子</p></a></li>
      <li><a href="list.html"><img src="/assets/images/icon_3.png"><p>抽屉</p></a></li>
      <li><a href="list.html"><img src="/assets/images/icon_4.png"><p>沙发</p></a></li>
      <li><a href="list.html"><img src="/assets/images/icon_5.png"><p>灯饰</p></a></li>
      <li><a href="list.html"><img src="/assets/images/icon_6.png"><p>柜子</p></a></li>
      <li><a href="list.html"><img src="/assets/images/icon_7.png"><p>电视柜</p></a></li>
      <li><a href="list.html"><img src="/assets/images/icon_8.png"><p>床</p></a></li>
    </ul>
  </div>
  <div class="clear"></div>

  <!-- 新品 -->
  <div class="contitbox">
    <p>新品<span>•</span>推荐</p>
  </div>
  <ul class="proul">
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/4.jpg">
        <p><span>￥</span>288</p>
        </a>
      </div>
    </li>
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/5.jpg">
        <p><span>￥</span>198</p>
        </a>
      </div>
    </li>
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/6.jpg">
        <p><span>￥</span>2368</p>
        </a>
      </div>
    </li>
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/7.jpg">
        <p><span>￥</span>688</p>
        </a>
      </div>
    </li>
  </ul>
  <div class="adBox">
    <img src="/assets/images/banner.jpg">
  </div>
  <div class="clear"></div>

  <!-- 热销 -->
  <div class="contitbox">
    <p>热销<span>•</span>单品</p>
  </div>
  <ul class="proul_2">
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/12.jpg">
        <p><span>￥</span>288</p>
        </a>
      </div>
    </li>
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/13.jpg">
        <p><span>￥</span>198</p>
        </a>
      </div>
    </li>
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/14.jpg">
        <p><span>￥</span>2368</p>
        </a>
      </div>
    </li>
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/14.jpg">
        <p><span>￥</span>688</p>
        </a>
      </div>
    </li>
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/12.jpg">
        <p><span>￥</span>688</p>
        </a>
      </div>
    </li>
    <li>
      <div class="con">
        <a href="detail.html">
        <img src="/assets/images/13.jpg">
        <p><span>￥</span>688</p>
        </a>
      </div>
    </li>
  </ul>
  <div class="clear"></div>
  <div class="h54"></div>
</template>

<script>
  export default {
    name: 'home',
    emits: ['toggle'], //定义说明父组件的自定义事件
    created()
    {
      //触发父组件的事件
      this.$emit('toggle', true)
    }
  }
</script>