package com.innter.msdesigngarmentmaterial.services.IMPL;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothResponse;
import com.innter.msdesigngarmentmaterial.entities.*;
import com.innter.msdesigngarmentmaterial.exceptions.BadRequestTextil;
import com.innter.msdesigngarmentmaterial.repositories.*;
import com.innter.msdesigngarmentmaterial.services.IDesignMaterialClothService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class DesignMaterialClothService implements IDesignMaterialClothService {
    @Autowired
    private DesignMaterialClothRepository designMaterialClothRepository;
    @Autowired
    private DesignCompositionRepository designCompositionRepository;
    @Autowired
    private DesignMaterialClothCompositionRepository designMaterialClothCompositionRepository;
    @Autowired
    private DesignCompositionGroupRepository designCompositionGroupRepository;
    @Autowired
    private DesignPrintRepository designPrintRepository;
    @Autowired
    private DesignMaterialClothDetailRepository designMaterialClothDetailRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private DesignGarmentGroupRepository designGarmentGroupRepository;

    @Autowired
    private DesingMaterialClothGarmentGroupRepository desingMaterialClothGarmentGroupRepository;

    @Transactional
    @Override
    public DesignMaterialClothResponse saveDesignMaterialCloth(DesignMaterialClothRequest designMaterialClothRequest) {

        DesignCompositionGroupEntity compositionGroupEntity = designCompositionGroupRepository.findDesignCompositionGroup(designMaterialClothRequest.getDesignCompositionGroupId());
        DesignPrintEntity printEntity = designPrintRepository.findDesignPrint(designMaterialClothRequest.getDesignPrintId());
        DesignMaterialClothDetailEntity designMaterialClothDetail = designMaterialClothDetailRepository.findDesignMaterialClothDetail(designMaterialClothRequest.getDesignMaterialClothDetailId());
        ProviderEntity providerEntity = providerRepository.findProviderStatusById(designMaterialClothRequest.getProviderId());

        try {
            DesignMaterialClothEntity designMaterialCloth = new DesignMaterialClothEntity();
            designMaterialCloth.setName(designMaterialClothRequest.getName());
            designMaterialCloth.setCompositionGroup(compositionGroupEntity);
            designMaterialCloth.setWidth(designMaterialClothRequest.getWidth());
            designMaterialCloth.setMeterPrice(designMaterialClothRequest.getMeterPrice());
            designMaterialCloth.setPrint(printEntity);
            designMaterialCloth.setClothDetail(designMaterialClothDetail);
            designMaterialCloth.setProvider(providerEntity);
            designMaterialClothRepository.save(designMaterialCloth);

            designMaterialClothRequest.getDesignComposition().forEach(composition -> {
                designCompositionRepository.findById(composition.getId_composition()).ifPresent(designComposition -> {
                    DesingMaterialClothCompositionEntity desingMaterialClothComposition = new DesingMaterialClothCompositionEntity();
                    desingMaterialClothComposition.setDesignComposition(designComposition);
                    desingMaterialClothComposition.setDesignMaterialClothComposition(designMaterialCloth);
                    desingMaterialClothComposition.setPercent(composition.getPercent());
                    designMaterialClothCompositionRepository.save(desingMaterialClothComposition);
                });

            });

            designMaterialClothRequest.getDesignGarmentGroup().forEach(garmentGroup -> {
                designGarmentGroupRepository.findById(garmentGroup.getId_garments_group()).ifPresent(designGarmentGroup -> {
                    DesingMaterialClothGarmentGroupEntity desingMaterialClothGarmentGroup = new DesingMaterialClothGarmentGroupEntity();
                    desingMaterialClothGarmentGroup.setDesignGarmentGroup(designGarmentGroup);
                    desingMaterialClothGarmentGroup.setDesignMaterialClothGarment(designMaterialCloth);
                    desingMaterialClothGarmentGroupRepository.save(desingMaterialClothGarmentGroup);
                } );
            });

            DesignMaterialClothResponse designMaterialClothResponse = new DesignMaterialClothResponse();
            designMaterialClothResponse.setId(designMaterialCloth.getId());
            designMaterialClothResponse.setDesignComposition(designMaterialClothRequest.getDesignComposition());
            designMaterialClothResponse.setName(designMaterialClothRequest.getName());
            designMaterialClothResponse.setDesignCompositionGroupId(compositionGroupEntity.getId());
            designMaterialClothResponse.setDesignGarmentGroup(designMaterialClothRequest.getDesignGarmentGroup());
            designMaterialClothResponse.setWidth(designMaterialClothRequest.getWidth());
            designMaterialClothResponse.setMeterPrice(designMaterialClothRequest.getMeterPrice());
            designMaterialClothResponse.setDesignPrintId(printEntity.getId());
            designMaterialClothResponse.setDesignMaterialClothDetailId(designMaterialClothDetail.getId());
            designMaterialClothResponse.setProviderId(providerEntity.getId());
            return designMaterialClothResponse;
        } catch (Exception e) {
            log.error("error: {}", e);
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Material de las telas no se pudo crear.");
        }
    }

}
