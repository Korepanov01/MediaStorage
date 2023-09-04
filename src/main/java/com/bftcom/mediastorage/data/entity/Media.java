package com.bftcom.mediastorage.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "media", schema = "public",
        indexes = {
                @Index(name = "idx_media_name", columnList = "name"),
                @Index(name = "idx_media_media_type_id", columnList = "media_type_id"),
                @Index(name = "idx_media_category_id", columnList = "category_id")
        })
public class Media extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotBlank
    @Size(max = 200)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 10_000)
    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "media_type_id", nullable = false)
    private MediaType mediaType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "media_tag",
            schema = "public",
            joinColumns = @JoinColumn(name = "media_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false),
            indexes = {
                    @Index(name = "uidx_media_tag_media_id_tag_id", columnList = "media_id,tag_id", unique = true)
            }
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "media_file",
            schema = "public",
            joinColumns = @JoinColumn(name = "media_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "file_id", nullable = false),
            indexes = {
                    @Index(name = "idx_media_file_media_id", columnList = "media_id"),
                    @Index(name = "uidx_media_file_media_id_file_id", columnList = "media_id,file_id", unique = true)
            }
    )
    private Set<File> files = new HashSet<>();

    public void addFile(File file) {
        files.add(file);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public Media(String name, String description, Category category, MediaType mediaType, User user) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.mediaType = mediaType;
        this.user = user;
    }
}
