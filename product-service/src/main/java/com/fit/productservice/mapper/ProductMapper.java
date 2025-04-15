package com.fit.productservice.mapper;

import com.fit.productservice.dto.request.RequestAddProductDTO;
import com.fit.productservice.entity.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")
    Product toProduct(RequestAddProductDTO requestAddProductDTO);

    @Mapping(source = "category.id", target = "categoryId")
    RequestAddProductDTO toRequestAddProductDTO(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "categoryId", target = "category.id")
    Product partialUpdate(RequestAddProductDTO requestAddProductDTO, @MappingTarget Product product);
}