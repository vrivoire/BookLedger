package com.vrivoire.bookledger.model;

import java.util.Map;

public class BookJson {

    private String author;
    private String title;
    private String genre;
    private String pages;
    private String publication;
    private String rating;
    private String isRead;
    private boolean validated;
    private Map<String, String> errors;

    public BookJson() {
    }

    public BookJson(String author, String title, String genre, String pages, String publication, String rating, String isRead, boolean validated, Map<String, String> errors) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.pages = pages;
        this.publication = publication;
        this.rating = rating;
        this.isRead = isRead;
        this.validated = validated;
        this.errors = errors;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public void setErrorMessages(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BookJson [");
        builder.append("author=").append(author);
        builder.append(", errors=").append(errors);
        builder.append(", genre=").append(genre);
        builder.append(", isRead=").append(isRead);
        builder.append(", pages=").append(pages);
        builder.append(", publication=").append(publication);
        builder.append(", rating=").append(rating);
        builder.append(", title=").append(title);
        builder.append(", validated=").append(validated);
        builder.append("]");
        return builder.toString();
    }

}
