package com.innter.msdesigngarmentmaterial.mappers;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothDetailRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothDetailResponse;
import com.innter.msdesigngarmentmaterial.entities.DesignMaterialClothDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignMaterialClothDetailMapper implements IDesignMaterialClothDetailMapper{
    @Override
    public DesignMaterialClothDetailResponse designMaterialClothDetailToDesignMaterialClothDetailResponse(DesignMaterialClothDetailEntity designMaterialClothDetail) {
        DesignMaterialClothDetailResponse designMaterialClothDetailResponse = new DesignMaterialClothDetailResponse();
        designMaterialClothDetailResponse.setId(designMaterialClothDetail.getId());
        designMaterialClothDetailResponse.setColor(designMaterialClothDetail.getColor());
        designMaterialClothDetailResponse.setCodeColor(designMaterialClothDetail.getCodeColor());
        designMaterialClothDetailResponse.setIndication(designMaterialClothDetail.getIndication());
        designMaterialClothDetailResponse.setImage(designMaterialClothDetail.getImage());
        designMaterialClothDetailResponse.setImagePath(designMaterialClothDetail.getImagePath());
        return designMaterialClothDetailResponse;
    }

    @Override
    public DesignMaterialClothDetailEntity designMaterialClothDetailRequestToDesignMaterialClothDetail(DesignMaterialClothDetailRequest designMaterialClothDetailRequest) {
        DesignMaterialClothDetailEntity designMaterialClothDetail = new DesignMaterialClothDetailEntity();
        designMaterialClothDetail.setColor(designMaterialClothDetailRequest.getColor());
        designMaterialClothDetail.setCodeColor(designMaterialClothDetailRequest.getCodeColor());
        designMaterialClothDetail.setIndication(designMaterialClothDetailRequest.getIndication());
        designMaterialClothDetail.setImage(designMaterialClothDetailRequest.getImage());
        designMaterialClothDetail.setImagePath(designMaterialClothDetailRequest.getImagePath());
        return designMaterialClothDetail;
    }
}
