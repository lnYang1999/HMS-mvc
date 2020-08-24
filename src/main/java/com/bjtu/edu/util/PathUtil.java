package com.bjtu.edu.util;

/**
 * @project: o2o
 * @description: 路径工具类，
 * 提供两类的路径-依据执行环境的不同提供不同的根路径（项目的所有图片所需要存放的路径）
 * @author: ysp
 * @create: 2020/07/22
 */
public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

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

    public static String getStudentImagePath(long studentId){
        String imagePath = "/imageUpload/item/student/" + studentId + "/";
        return imagePath.replace("/",seperator);
    }
    public static String getTeacherImagePath(long teacherId) {
        String imagePath = "/imageUpload/item/teacher/"+ teacherId + "/";
        return imagePath.replace("/", seperator);
    }
    public static String getAdminImagePath(long adminId) {
        String imagePath = "/imageUpload/item/admin/"+ adminId + "/";
        return imagePath.replace("/", seperator);
    }

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

    public static String getHomeworkFilePath(long homeworkId){
        String imagePath = "/fileUpload/item/homework/" + homeworkId + "/";
        return imagePath.replace("/",seperator);
    }
    public static String getStudentHomeworkFilePath(long studentHomeworkId) {
        String imagePath = "/fileUpload/item/studentHomework/"+ studentHomeworkId + "/";
        return imagePath.replace("/", seperator);
    }
}
