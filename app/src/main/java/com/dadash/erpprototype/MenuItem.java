package com.dadash.erpprototype;

import java.util.List;

public class MenuItem {
    private String mealType;
    private String timing;
    private String qrCode;
    private List<String> items;

    // No-argument constructor required for Firestore deserialization
    public MenuItem() {
    }

    public MenuItem(String mealType, String timing, String qrCode, List<String> items) {
        this.mealType = mealType;
        this.timing = timing;
        this.qrCode = qrCode;
        this.items = items;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
