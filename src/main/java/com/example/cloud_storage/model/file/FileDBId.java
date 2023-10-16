package com.example.cloud_storage.model.file;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class FileDBId implements Serializable {

    private String name;
    private Long user;

    public FileDBId() {
    }

    public FileDBId(String name, Long user_id) {
        this.name = name;
        this.user = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDBId fileDBId = (FileDBId) o;
        return Objects.equals(name, fileDBId.name) && Objects.equals(user, fileDBId.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, user);
    }
}
