# Immersive
![](https://img.shields.io/badge/platform-android-orange.svg)
![](https://img.shields.io/badge/language-java-yellow.svg)
![](https://jitpack.io/v/iwdael/immersive.svg)
![](https://img.shields.io/badge/build-passing-brightgreen.svg)
![](https://img.shields.io/badge/license-apache--2.0-green.svg)
![](https://img.shields.io/badge/api-19+-green.svg)

一行代码实现沉浸式，适配android4.4到8.0+，沉浸式前所未有的简单。
</br></br>
![Image Text](https://github.com/iwdael/immersive/blob/master/immersive.gif)
## 代码示例

使用以下方法代替Activity.setContentView()，即可实现沉浸式。
```Java
    /**
     * @param activity 当前需要使用沉浸式的Activity
     * @param layoutRes 当前activity使用的布局资源Id
     * @param statusRes 状态栏颜色资源Id
     * @param navigationRes 底部导航栏颜色Id
     * @param hideStatusBar 是否隐藏状态栏
     * @param hideNavigationBar 是否隐藏导航栏
     */
    ImmersiveKt.setContentView(Activity activity, @LayoutRes int layoutRes, int statusRes, int navigationRes, boolean hideStatusBar, boolean hideNavigationBar)
```
动态修改状态栏颜色，前提是必须使用ImmersiveKt.setContentView设置布局。
```Java
    ImmersiveKt.setStatusBarColor(Activity activity, int color)
```
动态修改导航栏颜色，前提是必须使用ImmersiveKt.setContentView设置布局。
```Java
    ImmersiveKt.setNavigationBarColor(Activity activity, int color)
```
 隐藏/展示状态栏，前提是必须使用ImmersiveKt.setContentView设置布局。
```Java
     ImmersiveKt.hideStatusBar(Activity activity)
     ImmersiveKt.showStatusBar(Activity activity)
```
 隐藏/展示导航栏，前提是必须使用ImmersiveKt.setContentView设置布局。
```Java
     ImmersiveKt.hideNavigationBar(Activity activity)
     ImmersiveKt.showNavigationBar(Activity activity)
```
#### 适配问题
如果遇见导航栏或者状态栏未显示正确，请根据机型注册PhoneRom修复BUG,欢迎提交issue,附上PhoneRom源码帮助更多的人适配该机型。
##### 注册PhoneRom
推荐在Applicaiton.create中注册
```Java
     Immersive.registerPhoneRom()
```
实现 PhoneRom , 示例
```Java
class GooglePhoneRom : PhoneRom {
    companion object {
        private const val CONTENT_KEY = "force_fsg_nav_bar"
    }

    override fun isCurrentPhoneRom() = BRAND_LOWER_CASE.contains("google")


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            Settings.Global.getInt(activity.contentResolver, CONTENT_KEY) == 0
        } catch (e: Exception) {
            defaultPhoneRom.navigationBarExist(activity)
        }
    }
}
```
更多
```Java
interface PhoneRom {
    /**
     * 根据屏幕旋转角度判断当前状态所在的位置，绝大多数不用修改
     */
    fun gravityOfStatusBar(orientation: Orientation) = Gravity.TOP

    /**
     * 根据屏幕旋转角度判断当前导航所在的位置，绝大多数不用修改
     */
    fun gravityOfNavigationBar(orientation: Orientation) = when (orientation) {
        Orientation.ORIENTATION_0 -> Gravity.BOTTOM
        Orientation.ORIENTATION_90 -> Gravity.END
        Orientation.ORIENTATION_180 -> Gravity.BOTTOM
        Orientation.ORIENTATION_270 -> Gravity.START
    }

    /**
     * 判断当前rom是否为定义ROM
     */
    fun isCurrentPhoneRom(): Boolean

    /**
     * 状态栏高度
     */
    fun statusBarHeight(context: Context): Int {
        return context.getSystemComponentDimen("status_bar_height")
    }

    /**
     * 导航栏高度
     */
    fun navigationBarHeight(context: Context): Int {
        return context.getSystemComponentDimen("navigation_bar_height")
    }
    /**
     * 部分手机可隐藏导航栏，是否存在
     */
    fun navigationBarExist(activity: Activity): Boolean
}
```
关于是否可隐藏导航栏，可根据导航栏隐藏前后，adb指令内容区别作为判断标记
```Java
adb shell getprop ro.product.brand //查看品牌
adb shell settings list global  //查看数据库，寻找是否隐藏 navigatiuon
adb shell settings list system
adb shell settings list secure
```
#### 快速引入项目
```Java
	dependencies {
                ...
	        implementation 'com.iwdael:immersive:$version'
	}
```

<br><br><br>
## 感谢浏览
如果你有任何疑问，请加入QQ群，我将竭诚为你解答。欢迎Star和Fork本仓库，当然也欢迎你关注我。
<br>
![Image Text](https://github.com/iwdael/CarouselBanner/blob/master/qq_group.png)
