package com.kozachuk.ita.Persistance.Model;

/**
 * Created by alexanderkozachuk on 11.03.16.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "comment", unique = false, nullable = true, length = 20)
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "msisdn", unique = true, nullable = false, length = 20)
    public Long getMsisdn() {
        return msisdn;
    }
    public void setMsisdn(Long msisdn) {
        this.msisdn = msisdn;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "content_to_user", joinColumns = {
                                   @JoinColumn(name = "user_id",    nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "content_id", nullable = false, updatable = false) })
    public Set<Note> getNotes() {
        return this.notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("name", getComment())
                .append("msisdn", getMsisdn())
                .toString();
    }

    public User() {}

    public User(Long msisdn, String comment) {
        this.comment = comment;
        this.msisdn = msisdn;
    }

    public User(Long msisdn, String comment, Set<Note> notes) {
        this.comment = comment;
        this.msisdn = msisdn;
        this.notes = notes;
    }

    private Integer id;
    private String comment;
    private Long msisdn;
    private Set<Note> notes = new HashSet<Note>(0);
}
