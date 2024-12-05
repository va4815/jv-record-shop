package com.northcoders.jv_record_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String releasedDate;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Genre genre;

//    @OneToMany(mappedBy = "id")
//    private Set<Artists> artists;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "album_artists",
            joinColumns = { @JoinColumn(name = "album_id") },
            inverseJoinColumns = { @JoinColumn(name = "artist_id") })
    private Set<Artists> artists = new HashSet<>();

    @Column
    private Instant createdAt;

    @Column
    private Instant modifiedAt;

}
