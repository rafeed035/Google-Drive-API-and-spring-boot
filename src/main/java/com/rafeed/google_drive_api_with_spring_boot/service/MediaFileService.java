package com.rafeed.google_drive_api_with_spring_boot.service;

import com.rafeed.google_drive_api_with_spring_boot.custom_exceptions.exceptions.EntityNotFoundException;
import com.rafeed.google_drive_api_with_spring_boot.entity.MediaFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface MediaFileService {
    String uploadMediaFile(String mediaTitle,
                           MultipartFile multipartFile) throws GeneralSecurityException, IOException, EntityNotFoundException;

    List<MediaFile> getAllMediaFiles();

    String getMediaFileUrlById(long id) throws EntityNotFoundException;

    String deleteMediaFile(long id) throws EntityNotFoundException, GeneralSecurityException, IOException;
}
