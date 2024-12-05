package com.northcoders.jv_record_shop.dto.response;

import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AlbumResponseDTO {
    private Long id;
    private String name;
    private String releasedDate;
    private Genre genre;

    public AlbumResponseDTO(Album album) {
        if (album != null) {
            this.id = album.getId();
            this.name = album.getName();
            this.releasedDate = album.getReleasedDate();
            this.genre = album.getGenre();
        }
    }
}
