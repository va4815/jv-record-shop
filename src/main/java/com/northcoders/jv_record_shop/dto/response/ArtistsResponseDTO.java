package com.northcoders.jv_record_shop.dto.response;

import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ArtistsResponseDTO {
    private Long id;
    private String name;
    private Gender gender;

    public ArtistsResponseDTO(Artists artists) {
        if (artists != null) {
            this.id = artists.getId();
            this.name = artists.getName();
            this.gender = artists.getGender();
        }
    }
}
