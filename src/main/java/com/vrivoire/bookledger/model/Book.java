package com.vrivoire.bookledger.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = -1736102660793102005L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "The author's mame cannot be empty")
    @Size(min = 3, max = 200, message = "The author's mame must be between 3 and 200 characters")
    @NotBlank(message = "The author's mame cannot be empty")
    @Column(name = "AUTHOR")
    private String author;

    @NotNull(message = "The title cannot be empty")
    @Size(min = 3, max = 200, message = "The title must be between 3 and 200 characters")
    @NotBlank(message = "The title cannot be empty")
    @Column(name = "Title")
    private String title;

    @NotNull(message = "The genre cannot be empty")
    @Column(name = "GENRE")
    private Genre genre;

    @Positive(message = "The number of pages must be positive")
    @Min(value = 1, message = "The number of pages must be  greater than 1")
    @Column(name = "PAGES")
    private int pages;

    @Column(name = "PUBLICATION")
    private int publication;

    @Min(value = 1, message = "The rating should not be less than 1")
    @Max(value = 5, message = "The rating should not be greater than 5")
    @Column(name = "RATING")
    private int rating;

    @Column(name = "IS_READ", columnDefinition = "tinyint(1) default 0")
    private boolean isRead;

    public Book() {
    }

    public Book(String author, String title, Genre genre, int pages, int publication, int rating, boolean isRead) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.pages = pages;
        this.publication = publication;
        this.rating = rating;
        this.isRead = isRead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book [");
        builder.append("id=").append(id);
        builder.append(", author=").append(author);
        builder.append(", title=").append(title);
        builder.append(", genre=").append(genre);
        builder.append(", isRead=").append(isRead);
        builder.append(", pages=").append(pages);
        builder.append(", publication=").append(publication);
        builder.append(", rating=").append(rating);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.author);
        hash = 67 * hash + Objects.hashCode(this.title);
        hash = 67 * hash + Objects.hashCode(this.genre);
        hash = 67 * hash + this.pages;
        hash = 67 * hash + Objects.hashCode(this.publication);
        hash = 67 * hash + this.rating;
        hash = 67 * hash + (this.isRead ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.pages != other.pages) {
            return false;
        }
        if (this.rating != other.rating) {
            return false;
        }
        if (this.isRead != other.isRead) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (this.genre != other.genre) {
            return false;
        }
        if (!Objects.equals(this.publication, other.publication)) {
            return false;
        }
        return true;
    }

}
