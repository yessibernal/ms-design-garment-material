package com.innter.msdesigngarmentmaterial.mappers;

import com.innter.msdesigngarmentmaterial.dtos.DesignCompositionDto;
import com.innter.msdesigngarmentmaterial.dtos.DesignGarmentGroupDto;
import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothResponse;
import com.innter.msdesigngarmentmaterial.entities.*;
import com.innter.msdesigngarmentmaterial.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private DesignCompositionRepository designCompositionRepository;

    @Autowired
    private DesignGarmentGroupRepository designGarmentGroupRepository;

    @Autowired
    private DesignMaterialClothCompositionRepository designMaterialClothCompositionRepository;

    @Override
    public DesignMaterialClothResponse designMaterialClothToDesignMaterialClothResponse(DesignMaterialClothEntity designMaterialCloth) {
        List<DesignCompositionDto> designCompositionDtos = new ArrayList<>();
        DesignCompositionDto designCompositionDto = new DesignCompositionDto();
        List<DesignGarmentGroupDto> designGarmentGroupDtos = new ArrayList<>();
        DesignGarmentGroupDto designGarmentGroupDto = new DesignGarmentGroupDto();
        designMaterialCloth.getDesignMaterialComposition().forEach(desingComposition -> {
            designCompositionRepository.findById(desingComposition.getDesignMaterialClothComposition().getId());
                designCompositionDto.setId_composition(desingComposition.getDesignComposition().getId());
                designCompositionDto.setPercent(desingComposition.getPercent());
        });
        designCompositionDtos.add(designCompositionDto);

        designMaterialCloth.getDesignMaterialGarment().forEach(desingMaterialClothGarmentGroup -> {
            designGarmentGroupRepository.findById(desingMaterialClothGarmentGroup.getDesignGarmentGroup().getId());
            designGarmentGroupDto.setId_garments_group(desingMaterialClothGarmentGroup.getDesignMaterialClothGarment().getId());
        } );
        designGarmentGroupDtos.add(designGarmentGroupDto);

        DesignMaterialClothResponse designMaterialClothResponse = new DesignMaterialClothResponse();
        designMaterialClothResponse.setId(designMaterialCloth.getId());
        designMaterialClothResponse.setDesignComposition(designCompositionDtos);
        designMaterialClothResponse.setName(designMaterialCloth.getName());
        designMaterialClothResponse.setDesignCompositionGroupId(designMaterialCloth.getCompositionGroup().getId());
        designMaterialClothResponse.setDesignGarmentGroup(designGarmentGroupDtos);
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
        Set<DesingMaterialClothCompositionEntity> desingMaterialClothCompositionEntitySet = new HashSet<>();

        designMaterialClothRequest.getDesignComposition().forEach(composition -> {
            designCompositionRepository.findById(composition.getId_composition()).ifPresent(designComposition -> {
                DesingMaterialClothCompositionEntity desingMaterialClothComposition = new DesingMaterialClothCompositionEntity();
                desingMaterialClothComposition.setDesignComposition(designComposition);
                desingMaterialClothComposition.setDesignMaterialClothComposition(designMaterialCloth);
                desingMaterialClothComposition.setPercent(composition.getPercent());
                designMaterialClothCompositionRepository.save(desingMaterialClothComposition);

                desingMaterialClothCompositionEntitySet.add(desingMaterialClothComposition);
            });

        });

        designMaterialCloth.setDesignMaterialComposition(desingMaterialClothCompositionEntitySet);
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
