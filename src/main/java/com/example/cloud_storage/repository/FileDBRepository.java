package com.example.cloud_storage.repository;

import com.example.cloud_storage.model.file.FileDB;
import com.example.cloud_storage.model.file.FileDBId;
import com.example.cloud_storage.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, FileDBId> {

    @Query(nativeQuery = true,
            value = "select f.* from _files f join _user u on f.user_id = u.id where u.id = :idUser limit :limit"
    )
    List<FileDB> getUserFilesById(long idUser, int limit);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "insert into _files (name, type, data, user_id) values (:filename, :type, :data, :userId)"
    )
    void saveFileInDB(String filename, String type, byte[] data, long userId);

    FileDB getFileDBByNameAndUser(String filename, User user);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "delete from _files where name = :filename and user_id = :userId"
    )
    void deleteFile(String filename, long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "update _files set name = :newFileName WHERE name = :oldFileName and user_id = :userId"
    )
    void editFileName(String oldFileName, String newFileName, long userId);
}
