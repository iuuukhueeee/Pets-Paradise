package com.DTO;

public class IncomeDTO {
    private float total;
    private String month;

    public IncomeDTO() {
    }

    public IncomeDTO(float total, String month) {
        this.total = total;
        this.month = month;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
