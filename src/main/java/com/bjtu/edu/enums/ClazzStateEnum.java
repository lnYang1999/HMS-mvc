package com.bjtu.edu.enums;

/**
 * @project: HMS-mvc
 * @description: Clazz状态枚举类
 * @author: ysp
 * @create: 2020/08/3
 */
public enum ClazzStateEnum {
    /**
     * @author: ysp
     * @description: 状态枚举
     * @createTime: 2020/8/3 19:29
     */
    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY_LIST(-1002, "添加数少于1"),
    EMPTY(-1003, "班级信息为空");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private ClazzStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * @author: ysp
     * @description: 依据传入的state返回相应的enum值
     *
     * @param state
     * @return ClazzStateEnum
     */
    public static ClazzStateEnum stateOf(int state){
        for (ClazzStateEnum stateEnum : values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
