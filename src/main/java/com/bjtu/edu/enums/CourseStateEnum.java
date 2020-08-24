package com.bjtu.edu.enums;

/**
 * @project: HMS-mvc
 * @description: Course状态枚举类
 * @author: ysp
 * @create: 2020/08/3
 */
public enum CourseStateEnum {
    /**
     * @author: ysp
     * @description: 状态枚举
     * @createTime: 2020/8/3 19:29
     */
    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY(-1002, "课程信息为空");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private CourseStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * @author: ysp
     * @description: 依据传入的state返回相应的enum值
     *
     * @param state
     * @return CourseStateEnum
     */
    public static CourseStateEnum stateOf(int state){
        for (CourseStateEnum stateEnum : values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
