package com.rafeed.google_drive_api_with_spring_boot.service_implementation;

import com.rafeed.google_drive_api_with_spring_boot.custom_exceptions.exceptions.EntityNotFoundException;
import com.rafeed.google_drive_api_with_spring_boot.entity.MediaFile;
import com.rafeed.google_drive_api_with_spring_boot.repository.MediaFileRepository;
import com.rafeed.google_drive_api_with_spring_boot.service.MediaFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class MediaFileServiceImplementation implements MediaFileService {

    private MediaFileRepository mediaFileRepository;
    private GoogleDriveService googleDriveService;

    public MediaFileServiceImplementation(MediaFileRepository mediaFileRepository,
                                          GoogleDriveService googleDriveService) {
        this.mediaFileRepository = mediaFileRepository;
        this.googleDriveService = googleDriveService;
    }

    //upload media to google drive and save media file data
    @Override
    public String uploadMediaFile(String mediaTitle,
                                  MultipartFile multipartFile) throws GeneralSecurityException, IOException, EntityNotFoundException {
        //use Google Drive service to upload the file and then retrieve the file url
        String fileId = googleDriveService.uploadFile(multipartFile);
        String fileUrl = googleDriveService.getFileUrl(fileId);

        // Save the fileUrl to the database along with other relevant information of the media file
        MediaFile mediaFile = new MediaFile();
        mediaFile.setMediaTitle(mediaTitle);
        mediaFile.setMediaFileId(fileId);
        mediaFile.setMediaUrl(fileUrl);
        mediaFileRepository.save(mediaFile);
        return "File Uploaded";
    }

    //get all media files
    @Override
    public List<MediaFile> getAllMediaFiles() {
        return mediaFileRepository.getAllByOrderByIdDesc();
    }

    //get media url by id
    @Override
    public String getMediaFileUrlById(long id) throws EntityNotFoundException {
        MediaFile mediaFile = mediaFileRepository.getMediaFileById(id);
        if (mediaFile == null) {
            throw new EntityNotFoundException("Media File with id: " + id + " Not Found");
        } else {
            return mediaFile.getMediaUrl();
        }
    }

    //delete
    @Override
    public String deleteMediaFile(long id) throws EntityNotFoundException, GeneralSecurityException, IOException {
        MediaFile mediaFile = mediaFileRepository.getMediaFileById(id);
        if (mediaFile == null) {
            throw new EntityNotFoundException("Media File with id: " + id + " Not Found");
        } else {
            String fileId = mediaFile.getMediaFileId();
            googleDriveService.deleteFile(fileId);
            mediaFileRepository.delete(mediaFile);
            return "Deleted Successfully!";
        }
    }
}
