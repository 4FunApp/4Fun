# 4Fun #

一款基于网络爬虫技术和网络抓包技术，针对特定的网站爬取信息放入数据库并提供给 app 端的开源项目。

## preview ##

![](http://i1.piimg.com/4851/bf0666fa4bf0564c.png)


![](http://i1.piimg.com/4851/1918037836fa523f.png)


![](http://i1.piimg.com/4851/0d1598e4a94c8c98.png)


![](http://i1.piimg.com/4851/7ba34443ee988111.png)


![](http://i1.piimg.com/4851/4f9e8d92c0b32108.png)


![](http://i1.piimg.com/4851/163f41838492aca5.png)


![](http://i1.piimg.com/4851/c7580d3d7ca57676.png)


![](http://i1.piimg.com/4851/b7ce6b65916a54dd.png)


![](http://i1.piimg.com/4851/03714008cfdf6ddf.png)


![](http://i1.piimg.com/4851/7a04dc3e2bfae86c.png)

## library ##

- [RxJava 2.0](https://github.com/ReactiveX/RxJava/tree/2.x)

- [Retrofit](https://github.com/square/retrofit)

- [Glide](https://github.com/bumptech/glide)

- [Dagger2](https://github.com/google/dagger)

- [BoomMenu](https://github.com/Nightonke/BoomMenu)

- [PhotoView](https://github.com/chrisbanes/PhotoView)

- [Fragmentataion](https://github.com/YoKeyword/Fragmentation)

- [MaterialEditText](https://github.com/rengwuxian/MaterialEditText)

- [FlatUI](https://github.com/eluleci/FlatUI)

- [MaterialDialogs](https://github.com/afollestad/material-dialogs)

- [Butterknife](https://github.com/JakeWharton/butterknife)

## points ##

- 后端使用 Jsoup 爬取网站信息

- 使用 RxJava 配合 Retrofit2 做网络请求

- 使用 RxUtil 对线程操作和网络请求结果处理做了封装

- 使用 okhttp3 对网络返回内容做缓存，还有日志、超时重连、头部消息的配置

- 使用 Material Design 控件和动画

- 使用MVP架构整个项目，对应于 model、ui、presenter 三个包

- 使用Dagger2将M层注入P层，将P层注入V层，无需new，直接调用对象

- 使用Glide做图片的处理和加载

- 使用Fragmentation简化Fragment的操作和懒加载

## TODO ##

- 夜间模式实现

- 收藏和点赞以及其他功能实现

- EventBus 解耦

- 等待RxBinding 适配 RxJava2.0，重构项目
