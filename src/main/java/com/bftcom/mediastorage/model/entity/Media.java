package com.bftcom.mediastorage.model.entity;

import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "media")
public class Media extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User author;

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

    public Media(Long id, User author, Category category, String name, String description, MediaType mediaType) {
        super(id);
        this.author = author;
        this.category = category;
        this.name = name;
        this.description = description;
        this.mediaType = mediaType;
    }
}
