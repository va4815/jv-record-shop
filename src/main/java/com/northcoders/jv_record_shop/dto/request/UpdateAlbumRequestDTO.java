package com.northcoders.jv_record_shop.dto.request;

import com.northcoders.jv_record_shop.model.Genre;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAlbumRequestDTO {
    private Long id;
    private String name;
    private String releasedDate;
    private Genre genre;
}
