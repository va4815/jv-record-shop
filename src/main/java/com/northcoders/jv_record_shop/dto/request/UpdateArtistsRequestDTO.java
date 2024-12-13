package com.northcoders.jv_record_shop.dto.request;

import com.northcoders.jv_record_shop.model.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateArtistsRequestDTO {
    private Long id;
    private String name;
    private Gender gender;
}
