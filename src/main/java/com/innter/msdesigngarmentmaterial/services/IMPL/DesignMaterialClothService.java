package com.innter.msdesigngarmentmaterial.services.IMPL;

import com.innter.msdesigngarmentmaterial.dtos.DesignCompositionDto;
import com.innter.msdesigngarmentmaterial.dtos.DesignGarmentGroupDto;
import com.innter.msdesigngarmentmaterial.dtos.DesignMaterialClothEditedDto;
import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothRequest;
import com.innter.msdesigngarmentmaterial.dtos.request.DesignRequestStatus;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothResponse;
import com.innter.msdesigngarmentmaterial.entities.*;
import com.innter.msdesigngarmentmaterial.exceptions.BadRequestTextil;
import com.innter.msdesigngarmentmaterial.exceptions.NotFoundTextil;
import com.innter.msdesigngarmentmaterial.mappers.DesignMaterialClothMapper;
import com.innter.msdesigngarmentmaterial.repositories.*;
import com.innter.msdesigngarmentmaterial.services.IDesignMaterialClothService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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
    @Autowired
    private DesignMaterialClothMapper designMaterialClothMapper;

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
                });
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

    @Override
    public List<DesignMaterialClothResponse> getDesignMaterialCloth(Pageable pageable) {
        try {
            List<DesignMaterialClothEntity> designMaterialClothEntities = designMaterialClothRepository.findDesignMaterialClothEntity(pageable);
            List<DesignMaterialClothResponse> designMaterialClothResponses = new ArrayList<>();
            designMaterialClothEntities.stream().forEach(designMaterialCloth -> {
                designMaterialClothResponses.add(designMaterialClothMapper.designMaterialClothToDesignMaterialClothResponse(designMaterialCloth));
            });
            return designMaterialClothResponses;
        } catch (Exception e) {
            log.error("error: {}", e);
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Material de las telas no se pudieron encontrar.");
        }
    }

    @Override
    public DesignMaterialClothResponse editedDesignMaterialClothByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            DesignMaterialClothEntity designMaterialCloth = designMaterialClothRepository.findDesignMaterialClothStatusById(id);
            designMaterialCloth.setStatus(designRequestStatus.getStatus());
            designMaterialClothRepository.save(designMaterialCloth);
            return designMaterialClothMapper.designMaterialClothToDesignMaterialClothResponse(designMaterialCloth);
        } catch (Exception e) {
            log.error("error: {}", e);
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El Material de las telas no fue encontrado.");
        }
    }
    
    @Transactional
    @Override
    public DesignMaterialClothResponse editedDesignMaterialCloth(DesignMaterialClothEditedDto designMaterialClothEditedDto, Long id) {
        DesignCompositionGroupEntity compositionGroupEntity = designCompositionGroupRepository.findDesignCompositionGroup(designMaterialClothEditedDto.getDesignCompositionGroupId());
        DesignPrintEntity printEntity = designPrintRepository.findDesignPrint(designMaterialClothEditedDto.getDesignPrintId());
        DesignMaterialClothDetailEntity designMaterialClothDetail = designMaterialClothDetailRepository.findDesignMaterialClothDetail(designMaterialClothEditedDto.getDesignMaterialClothDetailId());
        ProviderEntity providerEntity = providerRepository.findProviderStatusById(designMaterialClothEditedDto.getProviderId());
        DesignMaterialClothEntity designCloth = findDesignMaterialClothById(designMaterialClothRepository.findById(id));

        List<Long> listCompositionRequest = new ArrayList<>();
        List<Long> listCompositionUpdate = new ArrayList<>();
        List<Long> listCompositionDelete = new ArrayList<>();
        List<Long> listCompositionAdd = new ArrayList<>();
        List<Long> listRequestGarmentGroup = new ArrayList<>();
        List<Long> listUpdateGarmentGroup = new ArrayList<>();
        List<Long> listDeleteGarmentGroup = new ArrayList<>();
        List<Long> listAddGarmentGroup = new ArrayList<>();
        try {
            designCloth.setId(id);
            designCloth.setName(designCloth.getName());
            designCloth.setCompositionGroup(compositionGroupEntity);
            designCloth.setWidth(designMaterialClothEditedDto.getWidth());
            designCloth.setMeterPrice(designMaterialClothEditedDto.getMeterPrice());
            designCloth.setPrint(printEntity);
            designCloth.setClothDetail(designMaterialClothDetail);
            designCloth.setProvider(providerEntity);
            designMaterialClothRepository.save(designCloth);
            designMaterialClothEditedDto.getDesignComposition().forEach(composition -> listCompositionRequest.add(composition.getId_composition()));
            designMaterialClothEditedDto.getDesignGarmentGroup().forEach(designGarmentGroup -> listRequestGarmentGroup.add(designGarmentGroup.getId_garments_group()));

            List<Long> listCompositionExistNew = getListCompositionAdd(id);
            listCompositionRequest.forEach(listComposition -> {
                if (!listCompositionExistNew.contains(listComposition)) {
                    listCompositionAdd.add(listComposition);
                }
            });

            designMaterialClothEditedDto.getDesignComposition().forEach(designComposition -> listCompositionAdd.forEach(listSave -> {
                if (designComposition.getId_composition().equals(listSave)) {
                    designCompositionRepository.findById(listSave).ifPresent(newListComposition -> {
                        DesingMaterialClothCompositionEntity desingMaterialClothComposition = new DesingMaterialClothCompositionEntity();
                        desingMaterialClothComposition.setDesignComposition(newListComposition);
                        desingMaterialClothComposition.setDesignMaterialClothComposition(designCloth);
                        desingMaterialClothComposition.setPercent(designComposition.getPercent());
                        designMaterialClothCompositionRepository.save(desingMaterialClothComposition);
                    });
                }
            }));

            listCompositionRequest.forEach(listComposition -> {
                if (listCompositionExistNew.contains(listComposition)) {
                    listCompositionUpdate.add(listComposition);
                }
            });

            List<DesingMaterialClothCompositionEntity> desingMaterialClothCompositionUpdateList = designMaterialClothCompositionRepository.findDesingMaterialClothComposition(id);
            desingMaterialClothCompositionUpdateList.forEach(designClothUpdate -> designMaterialClothEditedDto.getDesignComposition().forEach(composition -> {
                listCompositionRequest.add(composition.getId_composition());
                DesingMaterialClothCompositionEntity desingMaterialClothComposition = new DesingMaterialClothCompositionEntity();
                desingMaterialClothComposition.setId(designClothUpdate.getId());
                desingMaterialClothComposition.setDesignMaterialClothComposition(designClothUpdate.getDesignMaterialClothComposition());
                desingMaterialClothComposition.setDesignComposition(designClothUpdate.getDesignComposition());

                if (composition.getId_composition() == designClothUpdate.getDesignComposition().getId()) {
                    desingMaterialClothComposition.setPercent(composition.getPercent());
                    designMaterialClothCompositionRepository.save(desingMaterialClothComposition);
                }
            }));

            listCompositionExistNew.forEach(listComposition -> {
                if (!listCompositionUpdate.contains(listComposition)) {
                    listCompositionDelete.add(listComposition);
                }
            });

            List<DesingMaterialClothCompositionEntity> desingMaterialClothCompositionList = designMaterialClothCompositionRepository.findDesingMaterialClothComposition(id);
            desingMaterialClothCompositionList.forEach(compositionGroup -> listCompositionDelete.forEach(listDeleteComposition -> {
                if (compositionGroup.getDesignComposition().getId() == listDeleteComposition) {
                    designMaterialClothCompositionRepository.delete(compositionGroup);
                }
            }));

            List<Long> listExistGarmentGroupNew = getListGarmentGroup(id);
            listRequestGarmentGroup.forEach(listRequest -> {
                if (!listExistGarmentGroupNew.contains(listRequest)) {
                    listAddGarmentGroup.add(listRequest);
                }
            });

            designMaterialClothEditedDto.getDesignGarmentGroup().forEach(designGarmentGroup -> listAddGarmentGroup.forEach(listSaveGarmentGroup -> {
                if (designGarmentGroup.getId_garments_group().equals(listSaveGarmentGroup)) {
                    designGarmentGroupRepository.findById(listSaveGarmentGroup).ifPresent(newListGarmentGroup -> {
                        DesingMaterialClothGarmentGroupEntity clothGarmentGroup = new DesingMaterialClothGarmentGroupEntity();
                        clothGarmentGroup.setDesignGarmentGroup(newListGarmentGroup);
                        clothGarmentGroup.setDesignMaterialClothGarment(designCloth);
                        desingMaterialClothGarmentGroupRepository.save(clothGarmentGroup);
                    });
                }
            }));

            listRequestGarmentGroup.forEach(listRequest -> {
                if (listExistGarmentGroupNew.contains(listRequest)) {
                    listUpdateGarmentGroup.add(listRequest);
                }
            });

            listExistGarmentGroupNew.forEach(listExist -> {
                if (!listUpdateGarmentGroup.contains(listExist)) {
                    listDeleteGarmentGroup.add(listExist);
                }
            });

            List<DesingMaterialClothGarmentGroupEntity> desingMaterialClothGarmentGroupList = desingMaterialClothGarmentGroupRepository.findDesignMaterialClothGarment(id);
            desingMaterialClothGarmentGroupList.forEach(clothGarmentGroup -> listDeleteGarmentGroup.forEach(listDeleteGarment -> {
                if (clothGarmentGroup.getDesignGarmentGroup().getId() == listDeleteGarment) {
                    desingMaterialClothGarmentGroupRepository.delete(clothGarmentGroup);
                }
            }));

            DesignMaterialClothResponse designMaterialClothResponse = getDesignMaterialClothResponse(designMaterialClothEditedDto, designCloth);
            return designMaterialClothResponse;
        } catch (Exception e) {
            log.error("error: {}", e);
            throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "La instrucción del cuidado no tiene un estado activo");
        }
    }

    private DesignMaterialClothEntity findDesignMaterialClothById(Optional<DesignMaterialClothEntity> optionalDesignMaterialCloth) {
        return optionalDesignMaterialCloth.orElseThrow(() -> new NotFoundTextil("P-404", HttpStatus.NOT_FOUND, "El Material de las telas no fue encontrada."));
    }

    private static DesignMaterialClothResponse getDesignMaterialClothResponse(DesignMaterialClothEditedDto designMaterialClothEditedDto, DesignMaterialClothEntity designCloth) {
        DesignMaterialClothResponse designMaterialClothResponse = new DesignMaterialClothResponse();
        designMaterialClothResponse.setId(designCloth.getId());
        designMaterialClothResponse.setName(designCloth.getName());
        designMaterialClothResponse.setDesignCompositionGroupId(designCloth.getCompositionGroup().getId());
        designMaterialClothResponse.setWidth(designCloth.getWidth());
        designMaterialClothResponse.setMeterPrice(designCloth.getMeterPrice());
        designMaterialClothResponse.setDesignPrintId(designCloth.getPrint().getId());
        designMaterialClothResponse.setDesignMaterialClothDetailId(designCloth.getClothDetail().getId());
        designMaterialClothResponse.setProviderId(designCloth.getProvider().getId());
        designMaterialClothResponse.setDesignComposition(designMaterialClothEditedDto.getDesignComposition());
        designMaterialClothResponse.setDesignGarmentGroup(designMaterialClothEditedDto.getDesignGarmentGroup());
        return designMaterialClothResponse;
    }

    private List<Long> getListCompositionAdd(Long id) {
        List<DesignCompositionDto> designCompositionDtos = new ArrayList<>();
        List<Long> listCompositionExist = new ArrayList<>();
        List<DesingMaterialClothCompositionEntity> desingMaterialClothCompositionEntities = designMaterialClothCompositionRepository.findDesingMaterialClothComposition(id);
        desingMaterialClothCompositionEntities.forEach(compositionGroup -> {
            DesignCompositionDto designCompositionDto = new DesignCompositionDto();
            designCompositionDto.setId_composition(compositionGroup.getDesignComposition().getId());
            designCompositionDto.setPercent(compositionGroup.getPercent());
            designCompositionDtos.add(designCompositionDto);
            listCompositionExist.add(designCompositionDto.getId_composition());
        });
        return listCompositionExist;
    }

    private List<Long> getListGarmentGroup(long id) {
        List<DesignGarmentGroupDto> designGarmentGroupDtos = new ArrayList<>();
        List<Long> listExistGarmentGroupNew = new ArrayList<>();
        List<DesingMaterialClothGarmentGroupEntity> desingMaterialClothGarmentGroup = desingMaterialClothGarmentGroupRepository.findDesignMaterialClothGarment(id);
        desingMaterialClothGarmentGroup.forEach(garmentGroup -> {
            DesignGarmentGroupDto designGarmentGroupDto = new DesignGarmentGroupDto();
            designGarmentGroupDto.setId_garments_group(garmentGroup.getDesignGarmentGroup().getId());
            designGarmentGroupDtos.add(designGarmentGroupDto);
            listExistGarmentGroupNew.add(designGarmentGroupDto.getId_garments_group());
        });
        return listExistGarmentGroupNew;
    }
}
