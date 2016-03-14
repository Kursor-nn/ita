package com.kozachuk.ita.Persistance.Model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
@Entity
@Table(name="categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", unique = false, nullable = true, length = 50)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "publicId", unique = true, nullable = false, length = 50)
    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getPublicId() {
        return publicId;
    }

    public Category(){}
    public Category(String name){
        this.name = name;
    }
    public Category(String name, String publicId){
        this.name = name;
        this.publicId = publicId;
    }


    private Integer id;
    private String name;
    private String publicId;

}
