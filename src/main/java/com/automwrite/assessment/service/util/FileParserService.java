package com.automwrite.assessment.service.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FileParserService {

    /**
     * Parses the content of a .txt file uploaded as a MultipartFile.
     *
     * @param file The uploaded .txt file
     * @return The content of the file as a String
     * @throws IOException If an error occurs while reading the file
     */
    public static String parseTxtFile(MultipartFile file) throws IOException {
        return new String(file.getBytes(), StandardCharsets.UTF_8);
    }
}
