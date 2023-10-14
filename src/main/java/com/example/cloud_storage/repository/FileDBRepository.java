package com.example.cloud_storage.repository;

import com.example.cloud_storage.model.FileDB;
import com.example.cloud_storage.model.FileDBId;
import com.example.cloud_storage.model.FileResponse;
import com.example.cloud_storage.model.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, FileDBId> {
    //@Transactional
    @Query(nativeQuery = true,
            value = "select f.* from _files f join _user u on f.user_id = u.id where u.id = :idUser limit :limit"
    )
    List<FileDB> getUserFilesById(long idUser, int limit);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "insert into _files (name, type, data, user_id) values (:filename, :type, :data, :userId);"
    )
    void saveFileInDB(String filename, String type, byte[] data, long userId);

    FileDB getFileDBByNameAndUser(String filename, User user);
}


//    public String uploadFileToServer(String filename, MultipartFile file) {
//        //TODO добавить запись в базу данных
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(Objects.requireNonNull(file.getOriginalFilename()))); //+ "-uploaded"
//                stream.write(bytes);
//                stream.close();
//                return "Вы удачно загрузили " + filename + " в " + filename + "-uploaded !";
//            } catch (Exception e) {
//                return "Вам не удалось загрузить " + filename + " => " + e.getMessage();
//            }
//        } else {
//            return "Вам не удалось загрузить " + filename + " потому что файл пустой.";
//        }
//    }
