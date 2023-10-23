package com.example.cloud_storage.service;

import com.example.cloud_storage.exception.ErrorBadRequest;
import com.example.cloud_storage.model.file.FileDB;
import com.example.cloud_storage.model.response.FileResponse;
import com.example.cloud_storage.model.User;
import com.example.cloud_storage.repository.FileDBRepository;
import com.example.cloud_storage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileManipulationService {

    private final FileDBRepository fileDBRepository;
    private final UserRepository userRepository;

    public void loadFile(String login, String fileName, MultipartFile file) throws IOException {
        User user = getUserFromRepository(login);
        String type = file.getContentType();
        byte[] data = file.getBytes();
        long userId = user.getId();
        fileDBRepository.saveFileInDB(fileName, type, data, userId);
    }

    public List<FileResponse> getFilesFromServer(String login, int limit) {
        User user = getUserFromRepository(login);
        long id_user = user.getId();
        return fileDBRepository.getUserFilesById(id_user, limit)
                .stream()
                .map(fileDB -> new FileResponse(fileDB.getName(), fileDB.getData().length))
                        .collect(Collectors.toList());
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

    public User getUserFromRepository(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ErrorBadRequest("Error input data"));
    }
}
