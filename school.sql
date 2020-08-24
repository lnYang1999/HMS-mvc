/*
 Navicat Premium Data Transfer

 Source Server         : MySQL数据库
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : school

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 24/08/2020 10:00:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(20) NOT NULL COMMENT '管理员ID号',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '12345' COMMENT '密码',
  `admin_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员姓名',
  `profile_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `email` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `admin_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime(6) NULL DEFAULT NULL COMMENT '最近一次修改时间',
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `key_admin`(`admin_id`) USING BTREE COMMENT '设置admin_id为唯一标识'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (2333, 'ysp1999', 'HHH', '\\imageUpload\\item\\admin\\2333\\2020082409094521909.png', '男', 'lnyang1999@163.com', '超级管理员-HHH', '2020-08-24 09:09:00.000000', '2020-08-24 09:09:45.884000');
INSERT INTO `admin` VALUES (9180, '111111', 'Ysp', '\\imageUpload\\item\\admin\\9180\\2020081723005727652.png', '男', '13624387341@163.com', '超级管理员-杨烁平', '2020-08-02 13:36:32.000000', '2020-08-17 23:00:57.506000');

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`  (
  `clazz_id` int(20) NOT NULL COMMENT '班级ID号',
  `clazz_number` int(4) NOT NULL COMMENT '班级人数',
  `clazz_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime(6) NULL DEFAULT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`clazz_id`) USING BTREE,
  UNIQUE INDEX `key_clazz_id`(`clazz_id`) USING BTREE COMMENT '设置clazz_id为唯一标识'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES (1701, 40, '软件学院1701班', '2020-08-02 13:42:19.000000', NULL);
INSERT INTO `clazz` VALUES (1702, 40, '软件学院1702班', '2020-08-02 13:42:41.000000', NULL);
INSERT INTO `clazz` VALUES (1703, 45, '软件学院1703班', '2020-08-16 20:44:24.824000', '2020-08-16 20:50:40.660000');
INSERT INTO `clazz` VALUES (1704, 55, '软件学院1704班', '2020-08-24 09:51:51.467000', NULL);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '课程ID号',
  `course_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `course_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `teacher_id` int(20) NOT NULL COMMENT '教师ID号',
  `course_size` int(4) NOT NULL COMMENT '课程容量',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime(6) NULL DEFAULT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `fk_teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `fk_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '操作系统', '学习本门课程的目标是了解并掌握计算机操作系统的基本概念、基本理论和基础技术。', 11, 100, '2020-08-02 13:48:07.000000', NULL);
INSERT INTO `course` VALUES (2, '软件工程', '软件工程是一门研究用工程化方法构建和维护有效的、实用的和高质量的软件的学科。', 11, 80, '2020-08-02 13:48:29.000000', NULL);
INSERT INTO `course` VALUES (3, '软件测试', '研究各种软件测试方法，如黑盒测试、白盒测试等', 22, 60, '2020-08-17 17:07:05.000000', NULL);
INSERT INTO `course` VALUES (4, 'JavaEE', '学习各种框架，spring、springmvc等', 555, 50, '2020-08-12 17:12:43.000000', '2020-08-17 17:45:50.388000');
INSERT INTO `course` VALUES (5, '数据库设计', '对于一个给定的应用环境，构造最优的数据库模式，建立数据库及其应用系统，使之能够有效地存储数据，满足各种用户的应用需求', 22, 60, '2020-08-17 17:45:39.921000', NULL);
INSERT INTO `course` VALUES (6, '软件体系结构', '软件体系结构是具有一定形式的结构化元素，即构件的集合，包括处理构件、数据构件和连接构件。处理构件负责对数据进行加工，数据构件是被加工的信息，连接构件把体系结构的不同部分组合连接起来。这一定义注重区分处理构件、数据构件和连接构件，这一方法在其他的定义和方法中基本上得到保持。相比较于“软件架构”,“软件体系结构”一词多用于学术研究领域使用，“软件架构”多用于工程实践领域，二者的外文名都是“software architecture”，在IEEE中的定义均为：“一个系统的基础组织，包含各个构件、构件互相之间与环境的关系，还有指导其设计和演化的原则。', 33, 50, '2020-08-24 09:47:03.519000', NULL);
INSERT INTO `course` VALUES (7, '计算机网络', '计算机网络是指将地理位置不同的具有独立功能的多台计算机及其外部设备，通过通信线路连接起来，在网络操作系统，网络管理软件及网络通信协议的管理和协调下，实现资源共享和信息传递的计算机系统。', 2222, 44, '2020-08-24 09:49:49.609000', NULL);

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
  `homework_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '作业ID号',
  `homework_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业名称',
  `homework_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业详细要求',
  `course_id` int(20) NOT NULL COMMENT '课程ID号',
  `homework_file` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业要求文件',
  `submit_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业提交截至时间',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime(6) NULL DEFAULT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`homework_id`) USING BTREE,
  INDEX `fk_homework_course`(`course_id`) USING BTREE,
  CONSTRAINT `fk_homework_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES (1, '操作系统第一次作业', '书上P33 2. 3. 4. P44 2. 8. 9. 10.', 1, NULL, '2020年8月30日8时', '2020-08-02 13:55:15.000000', '2020-08-21 22:15:28.620000');
INSERT INTO `homework` VALUES (2, '软件测试第一次作业', '编写一个程序对一元二次方程根的几种情况进行单元测试', 3, '测试', '8月30日 11:00', '2020-08-18 11:35:52.064000', '2020-08-18 11:35:52.082000');
INSERT INTO `homework` VALUES (3, '操作系统第二次作业', '编写一个程序模拟哲学家进餐问题', 1, '\\fileUpload\\item\\homework\\3\\2020082019174368303.htm', '2020年9月1日7时', '2020-08-18 16:48:19.225000', '2020-08-22 23:39:18.766000');
INSERT INTO `homework` VALUES (6, '软件工程的第一次作业', '书上题目\nP33 2. 5. 7. 10. \nP55 11. 22.\n准备明天小测', 2, '\\fileUpload\\item\\homework\\6\\2020082012382551462.htm', '2020年9月1日9时', '2020-08-20 12:38:25.118000', '2020-08-21 22:15:01.007000');
INSERT INTO `homework` VALUES (7, '软件工程大作业', '整理软件工程所学知识点，画一个思维导图，并写一个不少于1000字的论文\n要求：不得抄袭，抄袭者0分！！！\n作业发到邮箱，标注学号+姓名+软件工程大作业', 2, '\\fileUpload\\item\\homework\\7\\2020082122213466832.txt', '2020年9月4日8时', '2020-08-21 22:21:34.963000', NULL);
INSERT INTO `homework` VALUES (8, '操作系统大作业', '分别采用最佳置换算法、先进先出算法对用户输入的页面号请求序列进行淘汰和置换，从而加深对页面置换的理解。', 1, '\\fileUpload\\item\\homework\\8\\2020082223423698137.docx', '2020年9月5日8时', '2020-08-22 23:42:36.074000', NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int(20) NOT NULL COMMENT '学生ID号',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '12345' COMMENT '密码',
  `student_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `profile_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `email` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `student_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `clazz_id` int(20) NULL DEFAULT NULL COMMENT '班级ID号',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime(6) NULL DEFAULT NULL COMMENT '最近一次修改时间',
  PRIMARY KEY (`student_id`) USING BTREE,
  UNIQUE INDEX `key_student_id`(`student_id`) USING BTREE COMMENT '设置student_id为唯一标识',
  INDEX `fk_student_class`(`clazz_id`) USING BTREE,
  CONSTRAINT `fk_student_class` FOREIGN KEY (`clazz_id`) REFERENCES `clazz` (`clazz_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (11, '111111', '洋洋', '\\imageUpload\\item\\student\\11\\2020082314311673662.png', '男', 'yangyang@edu.cn', 'xx大学xx学院本科生，成绩优异', 1701, '2020-08-02 13:44:32.000000', '2020-08-23 14:31:27.002000');
INSERT INTO `student` VALUES (22, '12345', '哈哈', '\\imageUpload\\item\\student\\22\\2020081614551979149.png', '女', 'xxxx@edu.cn', '大学生，大三', 1701, '2020-08-02 13:56:45.000000', '2020-08-16 14:55:19.697000');
INSERT INTO `student` VALUES (33, '12345', '李华', '\\imageUpload\\item\\student\\33\\2020082409293774996.png', '女', 'lihua@edu.cn', '软件学院大三学生', 1702, '2020-08-24 09:29:37.702000', NULL);
INSERT INTO `student` VALUES (44, '12345', '张三', '\\imageUpload\\item\\student\\44\\2020082409535950414.png', '男', 'zhangsan@bjtu.cn', '软件学院大四学生', 1704, '2020-08-24 09:53:59.671000', NULL);
INSERT INTO `student` VALUES (17301053, '12345', '杨烁平', '\\imageUpload\\item\\student\\17301053\\2020081612312717926.png', '男', 'ysp@bjtu.edu.cn', '大四学生，学习成绩优异', 1702, '2020-08-16 12:31:27.609000', '2020-08-16 13:30:18.833000');

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `student_course_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '学生选课ID号',
  `course_id` int(20) NOT NULL COMMENT '课程ID号',
  `student_id` int(20) NOT NULL COMMENT '学生ID号',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime(6) NULL DEFAULT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`student_course_id`) USING BTREE,
  INDEX `fk_stc_course`(`course_id`) USING BTREE,
  INDEX `fk_stc_student`(`student_id`) USING BTREE,
  CONSTRAINT `fk_stc_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_stc_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (1, 1, 11, '2020-08-02 13:57:07.000000', NULL);
INSERT INTO `student_course` VALUES (2, 2, 11, '2020-08-02 13:57:26.000000', NULL);
INSERT INTO `student_course` VALUES (3, 1, 22, '2020-08-02 13:57:42.000000', NULL);
INSERT INTO `student_course` VALUES (4, 1, 17301053, '2020-08-17 19:32:49.190000', NULL);
INSERT INTO `student_course` VALUES (5, 3, 22, '2020-08-18 17:17:37.000000', NULL);
INSERT INTO `student_course` VALUES (6, 3, 11, '2020-08-18 17:20:36.000000', NULL);
INSERT INTO `student_course` VALUES (7, 4, 22, '2020-08-24 09:31:51.836000', NULL);
INSERT INTO `student_course` VALUES (8, 5, 22, '2020-08-24 09:32:10.177000', NULL);
INSERT INTO `student_course` VALUES (9, 4, 17301053, '2020-08-24 09:32:25.643000', NULL);
INSERT INTO `student_course` VALUES (10, 7, 17301053, '2020-08-24 09:50:22.075000', NULL);
INSERT INTO `student_course` VALUES (11, 6, 33, '2020-08-24 09:50:27.647000', NULL);
INSERT INTO `student_course` VALUES (12, 6, 22, '2020-08-24 09:50:36.000000', NULL);
INSERT INTO `student_course` VALUES (13, 5, 33, '2020-08-24 09:51:12.154000', NULL);

-- ----------------------------
-- Table structure for student_homework
-- ----------------------------
DROP TABLE IF EXISTS `student_homework`;
CREATE TABLE `student_homework`  (
  `student_homework_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '学生作业ID号',
  `homework_id` int(20) NOT NULL COMMENT '作业ID号',
  `student_id` int(20) NOT NULL COMMENT '学生ID号',
  `submit_content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业提交内容',
  `submit_file` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业提交文件',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime(6) NULL DEFAULT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`student_homework_id`) USING BTREE,
  INDEX `fk_sth_student`(`student_id`) USING BTREE,
  INDEX `fk_sth_homework`(`homework_id`) USING BTREE,
  CONSTRAINT `fk_sth_homework` FOREIGN KEY (`homework_id`) REFERENCES `homework` (`homework_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_sth_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_homework
-- ----------------------------
INSERT INTO `student_homework` VALUES (1, 1, 11, '我不会写啊', '\\fileUpload\\item\\studentHomework\\1\\2020082323470290307.png', '2020-08-02 13:58:20.000000', '2020-08-23 23:47:02.696000');
INSERT INTO `student_homework` VALUES (2, 1, 22, '见博客地址：...', NULL, '2020-08-02 13:58:54.000000', NULL);
INSERT INTO `student_homework` VALUES (3, 2, 22, '不会不会', '测试测试', '2020-08-18 11:45:07.989000', NULL);
INSERT INTO `student_homework` VALUES (4, 3, 11, '更改了上传文件w(ﾟДﾟ)w', '\\fileUpload\\item\\studentHomework\\4\\2020081817244292426.pdf', '2020-08-18 17:23:39.000000', '2020-08-23 23:46:02.595000');
INSERT INTO `student_homework` VALUES (6, 8, 11, 'gitHub地址：https://github.com/lnYang1999\n作业见GitHub和提交的文件\n学号：11', '\\fileUpload\\item\\studentHomework\\6\\2020082314390197331.htm', '2020-08-23 14:39:01.081000', NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` int(20) NOT NULL COMMENT '教师ID号',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '12345' COMMENT '密码',
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师姓名',
  `profile_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `email` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `teacher_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime(6) NULL DEFAULT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`teacher_id`) USING BTREE,
  UNIQUE INDEX `key_teacher_id`(`teacher_id`) USING BTREE COMMENT '设置teacher_id为唯一标识'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (11, '111111', '赵刚', '\\imageUpload\\item\\teacher\\11\\2020081922560342420.png', '男', 'zhaogang@edu.cn', 'xx大学软件学院讲师，毕业于xx大学，在xx大学任教四年', '2020-08-02 13:46:00.000000', '2020-08-23 10:28:38.906000');
INSERT INTO `teacher` VALUES (22, '12345', '李刚', NULL, '男', 'ligang@edu.cn', 'xx大学软件学院副教授，毕业于xx大学，在xx大学任教三年', '2020-08-02 13:50:33.000000', '2020-08-14 17:12:52.487000');
INSERT INTO `teacher` VALUES (33, '12345', '哈哈', '\\imageUpload\\item\\teacher\\33\\2020081611271194545.png', '女', 'hh@bjtu.edu.cn', '讲师', '2020-08-16 11:26:32.557000', NULL);
INSERT INTO `teacher` VALUES (555, '12345', '李华', '\\imageUpload\\item\\teacher\\555\\2020081417193082484.png', '女', 'lihua@bjtu.edu.cn', '教授，任职五年', '2020-08-14 17:19:30.418000', NULL);
INSERT INTO `teacher` VALUES (2222, '12345', '张强', '\\imageUpload\\item\\teacher\\2222\\2020082409252932632.png', '男', 'zhangqiang@bjtu.edu.cn', 'xx大学教授，任教4年', '2020-08-24 09:25:29.216000', NULL);

SET FOREIGN_KEY_CHECKS = 1;
