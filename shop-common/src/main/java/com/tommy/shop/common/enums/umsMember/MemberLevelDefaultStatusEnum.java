package com.tommy.shop.common.enums.umsMember;

/**
 * 是否为默认等级：0->不是；1->是
 *
 * @author chengk
 * @date 2020/7/16
 */
public enum MemberLevelDefaultStatusEnum {

    /**
     * 不是
     */
    NO(0,"不是"),

    /**
     * 是
     */
    YES(1,"是"),
    ;

    /** 字段值 */
    private Integer value;

    /** 字段值的实际意义*/
    private String valueInFact;

    public Integer getValue(){
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getValueInFact() {
        return valueInFact;
    }

    public void setValueInFact(String valueInFact) {
        this.valueInFact = valueInFact;
    }

    MemberLevelDefaultStatusEnum(Integer value, String valueInFact){
        this.value=value;
        this.valueInFact=valueInFact;
    }



}
