package ru.kata.academy.kovtunenko.third.block.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "title")
    private String title;

    public User() {

    }

    public User(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!id.equals(user.id)) {
            return false;
        }

        if (!name.equals(user.name)) {
            return false;
        }

        return title.equals(user.title);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("User: Id = %s; Name = %s; Title = %s", id, name, title);
    }

    public static User getEmptyUser() {
        return new EmptyUser();
    }

    private static class EmptyUser extends User {

    }
}
