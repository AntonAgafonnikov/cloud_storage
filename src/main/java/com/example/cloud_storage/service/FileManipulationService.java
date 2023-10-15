package com.example.cloud_storage.service;

import com.example.cloud_storage.exception.ErrorBadRequest;
import com.example.cloud_storage.model.FileDB;
import com.example.cloud_storage.model.FileResponse;
import com.example.cloud_storage.model.User;
import com.example.cloud_storage.repository.FileDBRepository;
import com.example.cloud_storage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//        throw new ErrorBadRequest("Error input data");
@Service
@RequiredArgsConstructor
public class FileManipulationService {

    private final FileDBRepository fileDBRepository;
    private final UserRepository userRepository;

    public void loadFile(String login, String fileName, MultipartFile file) throws IOException {
        User user = getUserFromRepository(login);
        //String filename = file.getOriginalFilename(); //todo - podstavit' filename?
        String type = file.getContentType();
        byte[] data = file.getBytes();
        long userId = user.getId();

        fileDBRepository.saveFileInDB(fileName, type, data, userId);
        //FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), user);
        //fileDBRepository.saveAndFlush(fileDB); //todo - poprobovati kak nibudi realizovat' cherez vstroennie metodi
    }

    public List<FileResponse> getFilesFromServer(String login, int limit) {
        User user = getUserFromRepository(login);
        long id_user = user.getId();
        return fileDBRepository.getUserFilesById(id_user, limit)
                .stream()
                .map(fileDB -> new FileResponse(fileDB.getName(), fileDB.getData().length))
                        .collect(Collectors.toList());

//        List<FileDB> fileDBList = fileDBRepository.getUserFilesById(id_user, limit);
//        List<FileResponse> fileResponseList = new ArrayList<>();
//        fileDBList.forEach(fileDB -> {
//            fileResponseList.add(new FileResponse(fileDB.getName(), fileDB.getData().length));
//        });
//        return fileResponseList; todo работает ли новая версия?
    }

    public byte[] getFile(String login, String filename) throws IOException {
        User user = getUserFromRepository(login);
        FileDB fileDB = fileDBRepository.getFileDBByNameAndUser(filename, user);
        return fileDB.getBytes();
    }

    public void deleteFile(String login, String filename) {
        User user = getUserFromRepository(login);
        long userId = user.getId();
        fileDBRepository.deleteFile(filename, userId);
    }

    public void editFileName(String login, String oldFileName, String newFileName) {
        User user = getUserFromRepository(login);
        long userId = user.getId();
        fileDBRepository.editFileName(oldFileName, newFileName, userId);
    }

    private User getUserFromRepository(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ErrorBadRequest("Error input data"));
    }
}
