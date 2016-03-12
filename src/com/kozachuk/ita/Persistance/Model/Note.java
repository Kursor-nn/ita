package com.kozachuk.ita.Persistance.Model;

/**
 * Created by alexanderkozachuk on 11.03.16.
 */

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "content")
public class Note  implements Serializable {
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

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "notes")
    @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
    public Set<User> getUsers() {
        return this.users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    public void setCategory(Category category) {
        this.categories.add(category);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("name", getName())
                .toString();
    }

    public Note(){}
    public Note(String name){
        this.name = name;
    }
    public Note(String name, Set<User> users){
        this.name = name;
        this.users = users;
    }

    private Integer id;
    private String name;
    Set<User> users = new HashSet<User>();
    Set<Category> categories = new HashSet<Category>();
}