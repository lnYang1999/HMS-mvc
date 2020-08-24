# HMS-mvc
作业管理系统-springMVC项目

## 编译环境

1. 编译工具（IDE）：IntelliJ IDEA 2020.2
2. 数据库：MySQL 8.0
3. 服务器：Tomcat 8.5

## 框架

1. 后端：SSM框架（Spring+SpringMVC+MyBatis）
2. 前端UI框架：Layui

## 功能介绍

### 1.管理员界面

功能如下：

- 修改个人信息（包括密码）
- 添加教师信息、修改教师信息、删除教师信息、查看教师信息
- 添加学生信息、修改学生信息、删除学生信息、查看学生信息
- 添加班级信息、修改班级信息、删除班级信息、查看班级信息
- 添加课程信息、修改课程信息、删除课程信息、查看课程信息
- 添加选课信息、修改选课信息、删除选课信息、查看选课信息
- 删除作业信息、查看作业信息
- 删除学生提交作业信息、查看学生提交作业信息

### 2.学生

功能如下：

- 修改个人信息（包括密码）
- 查看自己所属的班级信息
- 查看自己所选的课程信息
- 查看教师所布置的作业信息
- 提交作业、修改已提交的作业

### 3.教师

功能如下：

- 修改个人信息（包括密码）
- 查看自己所教授的课程信息
- 查看选课学生信息
- 发布作业
- 修改已布置的作业
- 删除已布置的作业

## 项目使用说明-必看！！！

### 1.建立数据库

在MySQL数据库中建立一个新的database，名字可以任意，然后在新建好的数据库中运行项目文件里的school.sql文件即可

### 2.修改项目里的PathUtil.java文件

本系统存放文件和图片为两个不同的目录

文件目录主要存放教师或学生上传的文件

图片目录存放教师、学生、管理员的头像

#### （1）修改图片目录

```java
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/projectHMS/image";
        }else {
            basePath = "/Users/work/image";
        }
        basePath = basePath.replace("/",seperator);
        return basePath;
    }
```

```java
if (os.toLowerCase().startsWith("win")){
      basePath = "D:/projectHMS/image";
}else {
      basePath = "/Users/work/image";
}
```

第一个basePath为Windows系统下存放图片的目录

第二个basePath为其他系统存放图片的目录（Mac、Linux等）

#### （2）修改文件目录

```java
    public static String getFileBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/projectHMS/file";
        }else {
            basePath = "/Users/work/file";
        }
        basePath = basePath.replace("/",seperator);
        return basePath;
    }
```

文件目录修改方法与上面相同

### 3.IDEA配置目录

在IDEA的tomcat配置中，需要配置存放图片和文件的上下文路径

点击‘外部源’选择对应的路径

### 4.修改jdbc配置文件

找到项目中的jdbc.properties文件，将相关配置修改为本地数据库的配置
