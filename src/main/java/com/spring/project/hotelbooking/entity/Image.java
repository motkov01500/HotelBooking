package com.spring.project.hotelbooking.entity;

import javax.persistence.*;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "image")
public class Image {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "image_binary", nullable = false)
    @Lob
    private Blob imageBinary;

    //region getters
    public int getId() {
        return id;
    }

    public Blob getImageBinary() {
        return imageBinary;
    }
    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setImageBinary(Blob imageBinary) {
        this.imageBinary = imageBinary;
    }
    //endregion
}
