# chat-pic-preview
Just preivew pictrue and video in chat page for android

###  1、使用

[![](https://jitpack.io/v/cndest/chat-pic-preview.svg)](https://jitpack.io/#cndest/chat-pic-preview)

1. Add it in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. Add the dependency

```
	dependencies {
	         implementation 'com.github.cndest:chat-pic-preview:Tag'
	}
```

3. Use in code

```
PicPreview.create(this)
                .setImageEngin((context, imageView, url) -> Glide.with(context).load(url).into(imageView))
                .setPreviewHolder((container, viewType) -> {
                    if (viewType == PicpConstant.MimeType_Video) {
                        View inflate = LayoutInflater.from(container.getContext())
                                           .inflate(R.layout.custom_item_preview_video,
                                        container,
                                        false);
                        return new CustomVideoHolder(inflate);
                    }
                    return null;
                })
                .start(6,localMediaList);
```



### 2、功能介绍

- 支持预览图片和视频
- 支持自定义标题栏
- 支持自定义底部菜单栏
- 支持自定义适配器预览类型视图