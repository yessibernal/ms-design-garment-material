package com.innter.msdesigngarmentmaterial.services;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothRequest;
import com.innter.msdesigngarmentmaterial.dtos.request.DesignRequestStatus;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesignMaterialClothService {

    DesignMaterialClothResponse saveDesignMaterialCloth (DesignMaterialClothRequest designMaterialClothRequest);

}
