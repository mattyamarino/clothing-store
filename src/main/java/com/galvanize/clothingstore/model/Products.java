package com.galvanize.clothingstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    private List<JacketEntity> jackets;
    private List<ShirtEntity> shirts;
    private List<ShoeEntity> shoes;
}
