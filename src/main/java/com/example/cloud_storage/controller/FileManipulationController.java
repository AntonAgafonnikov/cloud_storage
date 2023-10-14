package com.example.cloud_storage.controller;

import com.example.cloud_storage.model.AuthenticationRequest;
import com.example.cloud_storage.model.AuthenticationResponse;
import com.example.cloud_storage.model.FileDB;
import com.example.cloud_storage.model.FileResponse;
import com.example.cloud_storage.service.FileManipulationService;
import com.example.cloud_storage.service.JwtProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class FileManipulationController {

    private final FileManipulationService fileManipulationService;
    private final JwtProcessingService jwtProcessingService;

    @PostMapping("/file")
    public void uploadFileToServer (
            @RequestHeader("auth-token") String authToken,
            @RequestParam("filename") String filename,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String email = jwtProcessingService.extractUsername(authToken);
        fileManipulationService.loadFile(email, filename, file);
    }

    @GetMapping("/file")
    public byte[] getFile (
            @RequestHeader("auth-token") String authToken,
            @RequestParam("filename") String filename
    ) throws IOException {
        String email = jwtProcessingService.extractUsername(authToken);
        return fileManipulationService.getFile(email, filename);

//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//                .body(fileDB.getData());
    }

    @GetMapping("/list")
    public @ResponseBody List<FileResponse> getFilesFromServer (
            @RequestHeader("auth-token") String authToken,
            @RequestParam("limit") int limit
    ) throws IOException {
        String userName = jwtProcessingService.extractUsername(authToken);
        return fileManipulationService.getFilesFromServer(userName, limit);

        //if (limit == 0) limit = 5;
        //return fileManipulationService.getFilesFromServer(limit);
    }

//    @DeleteMapping("/file")
//    public String deleteFileFromServer(@RequestHeader("auth-token") String authToken,
//                                       @RequestParam("filename") String fileName){
//        return "Success deleted";
//    }
}
