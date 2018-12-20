package ru.innopolis.stc12.booksharing.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Service
public class FileUploadService {
    private static final Logger logger = Logger.getLogger(FileUploadService.class);
    private static final String PROP_CLOUD_NAME = "cloud_name";
    private static final String PROP_API_KEY = "api_key";
    private static final String PROP_API_SECRET = "api_secret";

    /**
     * Загружает изображение в Cloudinary и возвращает ссылку на него
     *
     * @param file Файл который нужно загрузить в Cloudinary
     * @return ссылка на загруженное в Cloudinary изображение
     */
    public String uploadMultipartFile(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            File uploadFile = getFile(file.getOriginalFilename());
            try (FileOutputStream fos = getFileOutputStream(uploadFile)) {
                fos.write(file.getBytes());
                Properties props = getProperties();
                String myCloudName = props.getProperty(PROP_CLOUD_NAME);
                String myApiKey = props.getProperty(PROP_API_KEY);
                String myApiSecret = props.getProperty(PROP_API_SECRET);

                Map map = ObjectUtils.asMap(
                        PROP_CLOUD_NAME, myCloudName,
                        PROP_API_KEY, myApiKey,
                        PROP_API_SECRET, myApiSecret);
                Cloudinary cloudinary = getCloudinary(map);
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

    File getFile(String fileName) {
        return new File(fileName);
    }

    FileOutputStream getFileOutputStream(File file) throws FileNotFoundException {
        return new FileOutputStream(file);
    }

    Cloudinary getCloudinary(Map map) {
        return new Cloudinary(map);
    }

    Properties getProperties() throws IOException {
        Properties props = new Properties();
        Map<String, String> env = System.getenv();
        if (env.get(PROP_CLOUD_NAME).isEmpty() || env.get(PROP_API_KEY).isEmpty() || env.get(PROP_API_SECRET).isEmpty()) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            props.load(classLoader.getResourceAsStream("config.properties"));
            return props;
        } else {
            props.setProperty(PROP_CLOUD_NAME, env.get(PROP_CLOUD_NAME));
            props.setProperty(PROP_API_KEY, env.get(PROP_API_KEY));
            props.setProperty(PROP_API_SECRET, env.get(PROP_API_SECRET));
            return props;
        }
    }


}