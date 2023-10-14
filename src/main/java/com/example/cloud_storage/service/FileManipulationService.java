package com.example.cloud_storage.service;

import com.example.cloud_storage.model.FileDB;
import com.example.cloud_storage.model.FileResponse;
import com.example.cloud_storage.model.User;
import com.example.cloud_storage.repository.FileDBRepository;
import com.example.cloud_storage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileManipulationService {

    private final FileDBRepository fileDBRepository;
    private final UserRepository userRepository;

    public void loadFile(String email, String fileName, MultipartFile file) throws IOException {

        User user = userRepository.findByEmail(email).get();

        String filename = file.getOriginalFilename();

        //String[] contentType = Objects.requireNonNull(file.getContentType()).split("/");
        String type = file.getContentType();

        byte[] data = file.getBytes();
        long userId = user.getId();

        //FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), user);
        fileDBRepository.saveFileInDB(filename, type, data, userId);
        //fileDBRepository.saveAndFlush(fileDB);
    }

//    public FileDB getFile(String id) {
//        return fileDBRepository.findById(id).get();
//    }

//    public Stream<FileDB> getAllFiles() {
//        return fileDBRepository.findAll().stream();
//    }

    public List<FileResponse> getFilesFromServer(String email, int limit) {

        long id_user = userRepository.findByEmail(email).get().getId();

        List<FileDB> fileDBList = fileDBRepository.getUserFilesById(id_user, limit);
        List<FileResponse> fileResponseList = new ArrayList<>();
        fileDBList.forEach(fileDB -> {
            fileResponseList.add(new FileResponse(fileDB.getName(), fileDB.getData().length));
        });

        /*
         fileDBList.stream().forEach(fileDB -> {
            fileResponseList.add(new FileResponse(fileDB.getName(), fileDB.getData().length));
        });
         */

        return fileResponseList;

                //fileDBList.get(1).getName()
//                .map(fileDB ->
//                        (
//                            new FileResponse(fileDB.getName(), fileDB.getData().length)
//                        )
//        ).collect(Collectors.toList());

//        System.out.println("--->>>" + fileDBRepository.findAll().stream()
//                .map(
//                        fileDB -> new FileResponse(fileDB.getName().substring(10), 766)
//                )
//                .toList());
//        return fileDBRepository
//                .findAll()
//                .stream()
//                .map(
//                        fileDB -> new FileResponse(fileDB.getName().substring(10), 766)
//                )
//                .collect(Collectors.toList());

    }

    public byte[] getFile(String email, String filename) throws IOException {
        User user = userRepository.findByEmail(email).get();
        //long userId = user.getId();
        FileDB fileDB = fileDBRepository.getFileDBByNameAndUser(filename, user);
        return fileDB.getBytes();
    }
}
