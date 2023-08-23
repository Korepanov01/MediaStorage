package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "media", schema = "public")
public class Media implements Identical {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

    @NotBlank
    @Size(max = 200)
    private String name;

    @Size(max = 10_000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "media_type_id")
    @NotNull
    private MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToMany
    @JoinTable(
            name = "media_tag",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "media_file",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private Set<File> files = new HashSet<>();

    public void addFile(File file) {
        files.add(file);
    }

    public void removeFile(File file) {
        files.remove(file);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public Media(Category category, String name, String description, MediaType mediaType, User user, Set<Tag> tags, Set<File> files) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.mediaType = mediaType;
        this.user = user;
        this.tags = tags;
        this.files = files;
    }
}
