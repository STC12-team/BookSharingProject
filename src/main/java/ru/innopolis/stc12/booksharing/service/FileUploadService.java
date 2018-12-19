package ru.innopolis.stc12.booksharing.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

@Service
public class FileUploadService {
    private static final Logger logger = Logger.getLogger(FileUploadService.class);
    private static final String PROP_CLOUD_NAME = "CLOUD_NAME";
    private static final String PROP_API_KEY = "API_KEY";
    private static final String PROP_API_SECRET = "API_SECRET";

    /**
     * Загружает изображение в Cloudinary и возвращает ссылку на него
     *
     * @param file Файл который нужно загрузить в Cloudinary
     * @return ссылка на загруженное в Cloudinary изображение
     */
    public String uploadMultipartFile(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            File uploadFile = new File(file.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(uploadFile)) {
                fos.write(file.getBytes());
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                Properties props = new Properties();
                props.load(classLoader.getResourceAsStream("config.properties"));
                String myCloudName = props.getProperty(PROP_CLOUD_NAME);
                String myApiKey = props.getProperty(PROP_API_KEY);
                String myApiSecret = props.getProperty(PROP_API_SECRET);

                Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                        PROP_CLOUD_NAME, myCloudName,
                        PROP_API_KEY, myApiKey,
                        PROP_API_SECRET, myApiSecret));
                Map uploadResult = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());
                return (String) uploadResult.get("url");
            } catch (Exception e) {
                logger.error(e);
                return e.getMessage();
            }
        } else {
            logger.error("file is empty");
            return "";
        }
    }
}