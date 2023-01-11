package com.example.app;

public class CategoryModal {
    private String categoryText;
    private String categoryImageUrl;

    public CategoryModal(String categoryText, String categoryImageUrl) {
        this.categoryText = categoryText;
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(String categoryText) {
        this.categoryText = categoryText;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }
}
