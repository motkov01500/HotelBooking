package com.spring.project.hotelbooking.entity;

import javax.persistence.*;

@Entity
@Table(name = "guest_review")
public class GuestReview {

    @Id
    @Column(name="id", nullable = false)
    private int id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "rating")
    private int rating;

    //region getters
    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    //endregion
}
