package com.ChitChat.FileSharing;

import lombok.Data;

@Data
public class File {
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
}
