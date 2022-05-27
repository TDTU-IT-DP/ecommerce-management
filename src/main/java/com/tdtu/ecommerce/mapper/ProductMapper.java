package com.tdtu.ecommerce.mapper;

import com.tdtu.ecommerce.dto.RacketDto;
import com.tdtu.ecommerce.dto.ShoeDto;
import com.tdtu.ecommerce.entity.Racket;
import com.tdtu.ecommerce.entity.Shoe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Racket toEntity(RacketDto racketDto);

    RacketDto toDto(Racket racket);

    Shoe toEntity(ShoeDto shoeDto);

    ShoeDto toDto(Shoe shoe);
}
