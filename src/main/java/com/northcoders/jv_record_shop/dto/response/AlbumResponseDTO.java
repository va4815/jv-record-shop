package com.northcoders.jv_record_shop.dto.response;

import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Genre;
import com.northcoders.jv_record_shop.model.Song;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
public class AlbumResponseDTO {
    private Long id;
    private String name;
    private String releasedDate;
    private Genre genre;
    private List<SongResponseDTO> songs = new ArrayList<>();

    public AlbumResponseDTO(Album album) {
        if (album != null) {
            this.id = album.getId();
            this.name = album.getName();
            this.releasedDate = album.getReleasedDate();
            this.genre = album.getGenre();

            List<Song> songSet = album.getSongs();
            if (songSet != null && !songSet.isEmpty()) {
                songSet.forEach(s -> this.songs.add(new SongResponseDTO(s)));
            }
        }
    }
}
