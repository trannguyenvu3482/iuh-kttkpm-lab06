package com.fit.productservice.mapper;

import com.fit.productservice.dto.request.RequestAddCategoryDTO;
import com.fit.productservice.entity.Category;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toCategory(RequestAddCategoryDTO requestAddCategoryDTO);

    RequestAddCategoryDTO toRequestAddCategoryDTO(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(RequestAddCategoryDTO requestAddCategoryDTO, @MappingTarget Category category);
}