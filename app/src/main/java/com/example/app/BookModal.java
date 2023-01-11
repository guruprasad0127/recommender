package com.example.app;

import java.io.Serializable;

public class BookModal implements Serializable {
    private String book_name, book_author, image_url, lang_code;
    private int publish_year, total_rating;
    private float rating;

    public BookModal(String book_name, String book_author, String image_url, String lang_code, int publish_year, int total_rating, float rating) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.image_url = image_url;
        this.lang_code = lang_code;
        this.publish_year = publish_year;
        this.total_rating = total_rating;
        this.rating = rating;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLang_code() {
        return lang_code;
    }

    public void setLang_code(String lang_code) {
        this.lang_code = lang_code;
    }

    public int getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(int publish_year) {
        this.publish_year = publish_year;
    }

    public int getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(int total_rating) {
        this.total_rating = total_rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
