package com.rafeed.google_drive_api_with_spring_boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "media_file_table"
)
public class MediaFile {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(
            name = "media_title",
            nullable = false
    )
    private String mediaTitle;

    @Column(
            name = "media_url",
            nullable = false
    )
    private String mediaUrl;

    @Column(
            name = "media_file_id"
    )
    private String mediaFileId;
}
