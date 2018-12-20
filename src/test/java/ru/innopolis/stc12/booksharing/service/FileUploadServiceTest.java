package ru.innopolis.stc12.booksharing.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileUploadServiceTest {

    @InjectMocks
    FileUploadService fileUploadService;

    @Mock
    MultipartFile multipartFile;

    @Mock
    Cloudinary cloudinary;

    @Mock
    Uploader uploader;

    @Mock
    Properties properties;

    @Mock
    FileOutputStream fileOutputStream;


    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        fileUploadService = spy(new FileUploadService());
        doReturn(fileOutputStream).when(fileUploadService).getFileOutputStream(any(File.class));
        doReturn(cloudinary).when(fileUploadService).getCloudinary(any());
        doReturn(properties).when(fileUploadService).getProperties();

    }

    @Test
    void uploadMultipartFile() throws IOException {
        Map expectedMap = new HashMap();
        String expectedUrl = "Expected URL";
        expectedMap.put("url", expectedUrl);

        when(multipartFile.getOriginalFilename()).thenReturn("expected path of file");
        when(multipartFile.getBytes()).thenReturn(new byte[1]);
        when(cloudinary.uploader()).thenReturn(uploader);
        when(uploader.upload(any(), any())).thenReturn(expectedMap);
        assertEquals(expectedUrl, fileUploadService.uploadMultipartFile(multipartFile));
    }

    @Test
    void uploadMultipartFileGetException() throws IOException {
        Map expectedMap = new HashMap();
        String expectedUrl = "Expected URL";
        expectedMap.put("url", expectedUrl);

        when(multipartFile.getOriginalFilename()).thenReturn("expected path of file");
        when(multipartFile.getBytes()).thenReturn(new byte[1]);
        when(cloudinary.uploader()).thenReturn(uploader);
        when(uploader.upload(any(), any())).thenThrow(new IOException("Expected io exception"));
        assertEquals("Expected io exception", fileUploadService.uploadMultipartFile(multipartFile));
    }

    @Test
    void emptyFileUpload() throws IOException {
        multipartFile = null;
        assertEquals("", fileUploadService.uploadMultipartFile(multipartFile));
    }
}
