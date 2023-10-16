package com.example.cloud_storage.controller;

import com.example.cloud_storage.exception.ErrorBadRequest;
import com.example.cloud_storage.exception.ErrorUnauthorizedException;
import com.example.cloud_storage.model.response.FileResponse;
import com.example.cloud_storage.model.request.PutRequest;
import com.example.cloud_storage.service.FileManipulationService;
import com.example.cloud_storage.service.JwtProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final UserDetailsService userDetailsService;

    @PostMapping("/file")
    public void uploadFileToServer(@RequestHeader("auth-token") String authToken,
                                   @RequestParam("filename") String filename,
                                   @RequestParam("file") MultipartFile file) throws IOException
    {
        String login = extractLogin(authToken);
        if (isTokenValid(authToken, login)) {
            fileManipulationService.loadFile(login, filename, file);
        }
    }

    @DeleteMapping("/file")
    public void deleteFileFromServer(@RequestHeader("auth-token") String authToken,
                                     @RequestParam("filename") String fileName)
    {
        String login = extractLogin(authToken);
        if (isTokenValid(authToken, login)) {
            fileManipulationService.deleteFile(login, fileName);
        }
    }

    @GetMapping("/file")
    public byte[] getFile(@RequestHeader("auth-token") String authToken,
                          @RequestParam("filename") String filename) throws IOException
    {
        String login = extractLogin(authToken);
        if (isTokenValid(authToken, login)) {
            return fileManipulationService.getFile(login, filename);
        }
        throw new ErrorBadRequest("Error input data");
    }

    @PutMapping("/file")
    public void editFileName(@RequestHeader("auth-token") String authToken,
                             @RequestParam("filename") String oldFileName,
                             @RequestBody PutRequest newFileName)
    {
        String login = extractLogin(authToken);
        if (isTokenValid(authToken, login)) {
            fileManipulationService.editFileName(login, oldFileName, newFileName.getFilename());
        }
    }

    @GetMapping("/list")
    public @ResponseBody List<FileResponse> getFilesFromServer(@RequestHeader("auth-token") String authToken,
                                                               @RequestParam("limit") int limit)
    {
        String login = extractLogin(authToken);
        if (isTokenValid(authToken, login)) {
            return fileManipulationService.getFilesFromServer(login, limit);
        }
        throw new ErrorBadRequest("Error input data");
    }

    private Boolean isTokenValid(String authToken, String login) {
        if (!jwtProcessingService.isTokenValid(authToken, userDetailsService.loadUserByUsername(login))) {
            throw new ErrorUnauthorizedException("Unauthorized error");
        }
        return true;
    }

    private String extractLogin(String authToken) {
        return jwtProcessingService.extractUsername(authToken);
    }
}
