package com.tommy.shop.common.enums.umsMember;

/**
 * 会员状态 0 -> 禁用 ; 1 -> 启用
 *
 * @author chengk
 * @date 2020/7/16
 */
public enum MemberStatusEnum {

    /**
     * 禁用
     */
    BAN(0,"禁用"),

    /**
     * 启用
     */
    USING(1,"启用"),
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

    MemberStatusEnum(Integer value, String valueInFact){
        this.value=value;
        this.valueInFact=valueInFact;
    }



}
