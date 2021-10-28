package com.egg.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PictureVO {

    private Integer id;
    private String name;
    private String mime;
    private String path;
    private Boolean discharge;
}
