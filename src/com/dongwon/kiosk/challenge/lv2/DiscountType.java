package com.dongwon.kiosk.challenge.lv2;

public enum DiscountType{
    // ordinal은 사용하지 않음.
    VETERAN(1,"국가유공자",0.1), // 국가유공자
    SOLDIER(2,"군인",0.05), // 군인
    STUDENT(3,"학생",0.03), // 학생
    GENERAL(4,"일반",0); // 일반

    private final int displayNum;
    private final String displayName;
    private final double discountRate;

    DiscountType(int displayNum, String displayName, double discountRate) {

        this.displayNum = displayNum;
        this.discountRate = discountRate;
        this.displayName = displayName;
    }

    public static DiscountType get(int input) {
        for (DiscountType value : DiscountType.values()) {
            if(value.displayNum == input) return value;
        }
        throw new IllegalArgumentException(String.valueOf(input));
    }

    public int getDisplayNum() {
        return displayNum;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    @Override
    public String toString() {
        return String.format("%s : %d%%", displayName, (int) (discountRate * 100));
    }

}
