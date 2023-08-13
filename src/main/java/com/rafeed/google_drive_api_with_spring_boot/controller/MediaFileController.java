package com.rafeed.google_drive_api_with_spring_boot.controller;

import com.rafeed.google_drive_api_with_spring_boot.custom_exceptions.exceptions.EntityNotFoundException;
import com.rafeed.google_drive_api_with_spring_boot.entity.MediaFile;
import com.rafeed.google_drive_api_with_spring_boot.service.MediaFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/media")
public class MediaFileController {
    private MediaFileService mediaFileService;

    public MediaFileController(MediaFileService mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    @PostMapping("/upload")
    public String uploadMediaFile(@RequestParam String mediaTitle,
                                  @RequestBody MultipartFile multipartFile) throws GeneralSecurityException, IOException, EntityNotFoundException {
        return mediaFileService.uploadMediaFile(mediaTitle, multipartFile);
    }

    @GetMapping("/all")
    public List<MediaFile> getAllMediaFiles() {
        return mediaFileService.getAllMediaFiles();
    }

    @GetMapping("/url")
    public String getMediaFileUrlById(@RequestParam long id) throws EntityNotFoundException {
        return mediaFileService.getMediaFileUrlById(id);
    }

    @DeleteMapping("/delete")
    public String deleteMediaFile(@RequestParam long id) throws EntityNotFoundException, GeneralSecurityException, IOException {
        return mediaFileService.deleteMediaFile(id);
    }
}
