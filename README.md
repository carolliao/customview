# customview
Android自定义控件练习

自定义组合控件的开发流程
  根据控件样式编写XML布局文件--->编写自定义属性文件--->编写自定义控件类--->在别的地方使用自定义控件
1、XML布局文件
  尽量减少布局的嵌套层数
2、自定义属性文件
    2-1、存放目录在res/values下
    2-2、属性文件名字可任意
    ![](https://github.com/carolliao/customview/blob/master/README.img/3.png)
    只是在使用自定义控件属性时要匹配
    ！[](https://github.com/carolliao/customview/blob/master/README.img/1.png)
    2-3、自定义属性被多个控件共用
    将需要共用的属性放在<declare-styleable></declare-styleable>之外
    ![](https://github.com/carolliao/customview/blob/master/README.img/2.png)
    2-4、属性中format值的说明
    dimension：
    color：
    string：
    boolean：
    enum：
    flag：
    float：
    fraction：
    integer：
    reference：
 3、编写自定义控件
    3-1、怎么来选择该继承哪个View？
 4、使用
    提示：使用之前，先编译一下工程，不然在xml布局预览中看不到控件
    
  
