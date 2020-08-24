package com.bjtu.edu.enums;

/**
 * @project: HMS-mvc
 * @description: StudentHomework状态枚举类
 * @author: ysp
 * @create: 2020/08/3
 */
public enum StudentHomeworkStateEnum {
    /**
     * @author: ysp
     * @description: 状态枚举
     * @createTime: 2020/8/3 19:29
     */
    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY(-1002, "作业内容为空");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private StudentHomeworkStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * @author: ysp
     * @description: 依据传入的state返回相应的enum值
     *
     * @param state
     * @return StudentHomeworkStateEnum
     */
    public static StudentHomeworkStateEnum stateOf(int state){
        for (StudentHomeworkStateEnum stateEnum : values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
