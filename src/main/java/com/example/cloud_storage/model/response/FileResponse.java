package com.example.cloud_storage.model.response;

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
