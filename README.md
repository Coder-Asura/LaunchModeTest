# LaunchModeTest

### 简书文章传送门
[Activity 的启动模式 launchmode 探索](http://www.jianshu.com/p/4b14e6160cb9)

### 前言
Activity 的 `launchmode` 应该算是基础中的基础了，分为四种：`standard`，`singleTop`，`singleTask`，`singleInstance` 。
但是在初学阶段一直没有很好的理解其中的区别，现在回过头来结合代码打印的日志总结一下，记录下来。

### standard 
* 标准模式，也是 Activity 的默认启动方式。
* 每调用一次 `startActivity(intent)` ，都会在任务栈实例化一个新的 Activity 。

### singleTop 
* 每调用一次 `startActivity(intent)` ,如果任务栈顶没有实例，就会实例化一个新的 activity 对象；
* 如果有，则不创建，并回调 `onNewIntent(intent)` 方法。

### singleTask
1.  如果在清单文件  `AndroidManifest.xml` 中没有定义 `taskAffinity` 属性：
  * 每调用一次 `startActivity(intent)`  ,如果当前任务栈没有实例，就会实例化一个新的 activity 对象；
  * 如果有，则不创建，并回调 `onNewIntent(intent)` 方法,同时会清空栈内其之上的其他activity实例
  * 比如栈里元素 （自下而上）A B C D ,其中 B 是 `singleTask`模式的 , 从 D 跳到 B，则栈元素变成 A B。
2.  如果在清单文件 `AndroidManifest.xml` 中定义了 `taskAffinity` 属性：
   * 则每调用一次 `startActivity(intent)`，如果该任务栈没有创建过，会创建一个新的任务栈；
   * 如果该新任务栈顶内没有实例，就会实例化一个新的 activity 对象；
   * 如果有，则不创建实例，并回调 `onNewIntent(intent)` 方法,同时会清空新栈内其之上的其他activity实例。
   * 比如栈里元素 （自下而上）A B C D ,其中 B 是 `singleTask`模式的 ,从 D 跳到 B，则栈元素变成 A B。

### singleInstance
1. 每调用一次 `startActivity(intent)`,如果新的任务栈不存在，则会创建一个新的任务栈并例化一个新的 activity 对象；
  * 如果已经是在新的任务栈中且有 activity 的实例，则不创建，并回调 `onNewIntent(intent)` 方法。
2. 如果在新的任务栈中再跳转到另一个 `singleInstance` 模式的 Activity 中，则又会创建一个新的任务栈；
3. 也就是说每一个` singleInstance` 模式的 Activity 都会拥有一个单独的任务栈，且任务栈中只有自己一个实例。

### 测试代码
[LaunchModeTest](https://github.com/qq709238339/LaunchModeTest)
