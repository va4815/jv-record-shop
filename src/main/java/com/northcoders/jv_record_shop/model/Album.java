package com.northcoders.jv_record_shop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "album")
@Data
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Instant releasedDate;

    @Enumerated(EnumType.ORDINAL)
    @Column
    Genre genre;

    @Column
    private Instant createdAt;

    @Column
    private Instant modifiedAt;

}
