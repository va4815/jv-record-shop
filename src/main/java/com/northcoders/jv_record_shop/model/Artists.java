package com.northcoders.jv_record_shop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artists")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Artists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

}
