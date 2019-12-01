# AndroidDefineUIUtil
自定义UI库


## 功能项

- 自定义继承 recyclerview （ [ZtxSlideRecyclerView](https://github.com/Milk-Fun/AndroidSimpleUIUtil/blob/master/ui/src/main/java/com/milkz/ui/other/ZtxSlideRecyclerView.java)），实现侧滑编辑删除功能 
- 自定义 toast （[ZToast](https://github.com/Milk-Fun/AndroidSimpleUIUtil/blob/master/ui/src/main/java/com/milkz/ui/toast/ZToast.java)），实现可以自定义显示时间
- 菊花进度条 [ZJuHuaProgress](https://github.com/Milk-Fun/AndroidSimpleUIUtil/blob/master/ui/src/main/java/com/milkz/ui/progress/ZJuHuaProgress.java)
- 可拖动按钮 [ZMoveButton](https://github.com/Milk-Fun/AndroidSimpleUIUtil/blob/master/ui/src/main/java/com/milkz/ui/button/ZMoveButton.java)
- 可拖动悬浮按钮 [ZMoveFloatButton](https://github.com/Milk-Fun/AndroidSimpleUIUtil/blob/master/ui/src/main/java/com/milkz/ui/button/ZMoveFloatButton.java)
- 完成对勾动画 [ZFinishImage](https://github.com/Milk-Fun/AndroidSimpleUIUtil/blob/master/ui/src/main/java/com/milkz/ui/status/ZFinishImage.java)

## 使用

- gradle中配置

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

- 在app gradle中引用

```
dependencies {
	implementation 'com.github.Milk-Fun:AndroidSimpleUIUtil:Tag'
}
```
TAG为版本号，当前最新版本为<font color=red>1.0.3</font>
