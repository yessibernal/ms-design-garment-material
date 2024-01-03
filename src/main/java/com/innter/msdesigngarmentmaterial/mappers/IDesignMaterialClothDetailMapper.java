package com.innter.msdesigngarmentmaterial.mappers;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothDetailRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothDetailResponse;
import com.innter.msdesigngarmentmaterial.entities.DesignMaterialClothDetailEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignMaterialClothDetailMapper {

    DesignMaterialClothDetailResponse designMaterialClothDetailToDesignMaterialClothDetailResponse (DesignMaterialClothDetailEntity designMaterialClothDetail);

    DesignMaterialClothDetailEntity designMaterialClothDetailRequestToDesignMaterialClothDetail (DesignMaterialClothDetailRequest designMaterialClothDetailRequest);
}
