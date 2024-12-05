package com.northcoders.jv_record_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "album")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "id")
    private Set<Artists> artists;

    @Column
    private Instant createdAt;

    @Column
    private Instant modifiedAt;

}
