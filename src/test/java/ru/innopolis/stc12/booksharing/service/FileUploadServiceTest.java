package ru.innopolis.stc12.booksharing.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FileUploadServiceTest {

    @Mock
    MultipartFile multipartFile;

    @Mock
    Cloudinary cloudinary;

    @Mock
    Uploader uploader;

    @InjectMocks
    FileUploadService fileUploadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void uploadMultipartFile() {
        Map expectedMap = new HashMap();
        String expectedUrl = "Expected URL";
        expectedMap.put("url", expectedUrl);


        when(multipartFile.getOriginalFilename()).thenReturn("expected path of file");
        try {
            when(multipartFile.getBytes()).thenReturn(new byte[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        when(cloudinary.uploader()).thenReturn(uploader);
        try {
            when(uploader.upload(any(), any())).thenReturn(expectedMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expectedUrl, fileUploadService.uploadMultipartFile(multipartFile));
    }
}
