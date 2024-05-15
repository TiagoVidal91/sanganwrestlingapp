package com.bearAndPupperCo.sangenWrestlingApp.APIUtils;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.SingleWrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Services.WrestlerServiceImpl;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class Base64Utils {

    private static final Logger logger = LoggerFactory.getLogger(WrestlerServiceImpl.class);

    private final ValidationUtils validationUtils;
    @Value("${sanganwrestling.app.imagePath}")
    private String folderPath;

    public Base64Utils(ValidationUtils validationUtils) {
        this.validationUtils = validationUtils;
    }

    public void encodeImage(SingleWrestlerDTO wrestlerDTO) throws IOException {
        try {

            validationUtils.validateFileType(wrestlerDTO.getWrestlerPicExtension());

            byte[] imageBytes = Base64.decodeBase64(wrestlerDTO.getWrestlerPic());

            Path dirPath = Path.of(folderPath);
            Files.createDirectories(dirPath);

            String filePath = folderPath + wrestlerDTO.getWrestlerName().replaceAll("\\s+", "").toLowerCase()
                    + wrestlerDTO.getWrestlerPicExtension();

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(imageBytes);
            }

            wrestlerDTO.setWrestlerPicPath(filePath);

        } catch (Exception e) {
            logger.error("Failed to encode image: " + e);
            throw e;
        }
    }
}
