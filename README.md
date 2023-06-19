## 一个有趣的想法

​	你们说有没有一种可能，我们都是 start 上千项目的贡献者？咱们一起众筹一个项目（就比如这个项目），先把star给它干到上K，再把每个朋友搞成贡献者，这样你就名正言顺的是一个上K star项目的贡献者，参与了开源项目，找工作，写简历的时候是不是又有了一个装逼点？有想法的朋友可以start and fork，我会把你们提交的任何pull request都合并到主项目来，当然了，前提是别修改主要功能内容，你可以提交一些无关紧要的内容。

## chat-pic-preview

一款针对Android平台下的图片、视频预览库，类似于聊天界面打开图片，能够左翻右翻的那种

[![](https://jitpack.io/v/cndest/chat-pic-preview.svg)](https://jitpack.io/#cndest/chat-pic-preview)

[![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen.svg)](https://github.com/cndest)

[![Star](https://img.shields.io/github/stars/cndest/chat-pic-preview.svg)](https://github.com/cndest/chat-pic-preview)

###  1、使用

1. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

2. Add the dependency

```groovy
dependencies {
   implementation 'com.github.cndest:chat-pic-preview:Tag'
}
```

3. Use in code

```java
PicPreview.create(this)
           .setImageEngin((context, imageView, url) -> 	Glide.with(context).load(url).into(imageView))
           .setPreviewHolder((container, viewType) -> {
                   return null})
           .start(6,localMediaList);
```

### 2、功能介绍

- 支持预览图片和视频
- 支持自定义标题栏
- 支持自定义底部菜单栏
- 支持自定义适配器预览类型视图

### 3、效果

| 1                                                            | 2                                                            | 3                                                            |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![](https://github.com/cndest/chat-pic-preview/blob/master/images/1.png) | ![](https://github.com/cndest/chat-pic-preview/blob/master/images/2.png) | ![](https://github.com/cndest/chat-pic-preview/blob/master/images/3.png) |

