package com.innter.msdesigngarmentmaterial.services;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothDetailRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothDetailResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesignMaterialClothDetailService {

    DesignMaterialClothDetailResponse saveDesignMaterialClothDetail (DesignMaterialClothDetailRequest designMaterialClothDetailRequest);

    List<DesignMaterialClothDetailResponse> getDesignMaterialClothDetail (Pageable pageable);

    DesignMaterialClothDetailResponse editedDesignMaterialClothDetail (DesignMaterialClothDetailRequest designMaterialClothDetailRequest , Long id);
}
