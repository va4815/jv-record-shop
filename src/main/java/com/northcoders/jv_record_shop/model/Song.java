package com.northcoders.jv_record_shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.northcoders.jv_record_shop.dto.request.CreateSongRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private Duration songLength;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "songs")
    @JsonIgnore
    private Set<Album> albums = new HashSet<>();

    public Song(CreateSongRequestDTO requestDTO) {
        if (requestDTO != null) {
            this.title = requestDTO.getTitle();
            this.writer = requestDTO.getWriter();
            this.songLength = requestDTO.getSongLength();
        }
    }
}
