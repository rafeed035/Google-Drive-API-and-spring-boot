package com.rafeed.google_drive_api_with_spring_boot.repository;

import com.rafeed.google_drive_api_with_spring_boot.entity.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaFileRepository extends JpaRepository<MediaFile, Long> {

    MediaFile getMediaFileById(long id);

    List<MediaFile> getAllByOrderByIdDesc();
}
