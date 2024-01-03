package com.innter.msdesigngarmentmaterial.mappers;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothResponse;
import com.innter.msdesigngarmentmaterial.entities.DesignMaterialClothEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDesignMaterialClothMapper {

    DesignMaterialClothResponse designMaterialClothToDesignMaterialClothResponse (DesignMaterialClothEntity designMaterialCloth);

    DesignMaterialClothEntity designMaterialClothRequestToDesignMaterialCloth (DesignMaterialClothRequest designMaterialClothRequest);
}
