# PageStateLayout
a customized view which can control page status</br>
一个可以控制页面状态的自定义View
## 1、效果预览
<img src='http://img.ksban.cn/PageStateLayout01.gif'/><br/> 
## 2、怎么使用？
### 1> 布局
```xml
<com.namcooper.pagestatelayout.PageStateLayout
        android:id="@+id/psl_state"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:text="This Is Content"
            android:textSize="16sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>

    </com.namcooper.pagestatelayout.PageStateLayout>
```
将PageStateLayout作为加载的容器或者界面的根布局，由具体的业务逻辑来决定。
### 2> APIs:
```java
showContent()//展示内容

showLoading()/showLoading(View view)//展示等待布局

showEmpty()/showEmpty(View view)//展示空布局，例如加载列表时服务器返回了空列表

showNetError()/showNetError(View view)//展示网络错误布局，例如手机未接入网络

showDataError()/showDataError(View view)//展示数据异常布局，例如服务器返回的数据格式与约定的不同

showDiyState(View view)//展示完全自定义布局，可以是任何的自定义状态

```
点击回调
```java

    public void setOnPageStateClickListener(OnPageStateClickListener listener) {
        this.mListener = listener;
    }
    
    public interface OnPageStateClickListener {
    
    void onEmpty();

    void onNetError();

    void onDataError();

    void onDiyState();

}
分别对应各个异常状态(除等待和内容外的状态)下，view的点击事件，可以用来做点击重试等业务逻辑
    
```
## 3、注意事项
*① 因为涉及的业务逻辑每个人都不一样，所以这个库不提供依赖，请下载源码导入自己的工程*<br/>
*② 如要使用默认样式，请手动修改每一个布局即可*
<img src='http://img.ksban.cn/PageStateLayout02.png'/>
