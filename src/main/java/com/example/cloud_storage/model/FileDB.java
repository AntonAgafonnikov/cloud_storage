package com.example.cloud_storage.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Entity
@Data
@Table(name = "_files")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FileDBId.class)
public class FileDB implements MultipartFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    private String type;

    private byte[] data;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    public User user;

    @Override
    public String getOriginalFilename() {
        return name;
    }

    @Override
    public String getContentType() {
        return type;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return data.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return data;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(data);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        try(FileOutputStream fos = new FileOutputStream(dest)) {
            fos.write(data);
        }
    }
}