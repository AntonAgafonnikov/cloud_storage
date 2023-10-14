package com.example.cloud_storage.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileResponse {
    private String filename;
    private int size;
}
