package com.innter.msdesigngarmentmaterial.mappers;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothResponse;
import com.innter.msdesigngarmentmaterial.entities.*;
import com.innter.msdesigngarmentmaterial.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DesignMaterialClothMapper implements IDesignMaterialClothMapper{
    @Autowired
    private DesignCompositionGroupRepository designCompositionGroupRepository;

    @Autowired
    private DesignPrintRepository designPrintRepository;

    @Autowired
    private DesignMaterialClothDetailRepository designMaterialClothDetailRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public DesignMaterialClothResponse designMaterialClothToDesignMaterialClothResponse(DesignMaterialClothEntity designMaterialCloth) {
        DesignMaterialClothResponse designMaterialClothResponse = new DesignMaterialClothResponse();
        designMaterialClothResponse.setId(designMaterialCloth.getId());
        designMaterialClothResponse.setName(designMaterialCloth.getName());
        designMaterialClothResponse.setDesignCompositionGroupId(designMaterialCloth.getCompositionGroup().getId());
        designMaterialClothResponse.setWidth(designMaterialCloth.getWidth());
        designMaterialClothResponse.setMeterPrice(designMaterialCloth.getMeterPrice());
        designMaterialClothResponse.setDesignPrintId(designMaterialCloth.getPrint().getId());
        designMaterialClothResponse.setDesignMaterialClothDetailId(designMaterialCloth.getClothDetail().getId());
        designMaterialClothResponse.setProviderId(designMaterialCloth.getProvider().getId());
        return designMaterialClothResponse;
    }

    @Override
    public DesignMaterialClothEntity designMaterialClothRequestToDesignMaterialCloth(DesignMaterialClothRequest designMaterialClothRequest) {
        DesignCompositionGroupEntity compositionGroupEntity = designCompositionGroupRepository.findDesignCompositionGroup(designMaterialClothRequest.getDesignMaterialClothDetailId());
        DesignPrintEntity printEntity = designPrintRepository.findDesignPrint(designMaterialClothRequest.getDesignPrintId());
        DesignMaterialClothDetailEntity designMaterialClothDetail = designMaterialClothDetailRepository.findDesignMaterialClothDetail(designMaterialClothRequest.getDesignMaterialClothDetailId());
        ProviderEntity providerEntity = providerRepository.findProviderStatusById(designMaterialClothRequest.getProviderId());
        DesignMaterialClothEntity designMaterialCloth = new DesignMaterialClothEntity();
        designMaterialCloth.setName(designMaterialClothRequest.getName());
        designMaterialCloth.setCompositionGroup(compositionGroupEntity);
        designMaterialCloth.setWidth(designMaterialClothRequest.getWidth());
        designMaterialCloth.setMeterPrice(designMaterialClothRequest.getMeterPrice());
        designMaterialCloth.setPrint(printEntity);
        designMaterialCloth.setClothDetail(designMaterialClothDetail);
        designMaterialCloth.setProvider(providerEntity);
        return designMaterialCloth;
    }
}
