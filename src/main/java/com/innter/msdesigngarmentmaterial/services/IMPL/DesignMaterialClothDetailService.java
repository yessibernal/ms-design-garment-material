package com.innter.msdesigngarmentmaterial.services.IMPL;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothDetailRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.DesignMaterialClothDetailResponse;
import com.innter.msdesigngarmentmaterial.entities.DesignMaterialClothDetailEntity;
import com.innter.msdesigngarmentmaterial.exceptions.BadRequestTextil;
import com.innter.msdesigngarmentmaterial.mappers.DesignMaterialClothDetailMapper;
import com.innter.msdesigngarmentmaterial.repositories.DesignMaterialClothDetailRepository;
import com.innter.msdesigngarmentmaterial.services.IDesignMaterialClothDetailService;
import com.innter.msdesigngarmentmaterial.exceptions.NotFoundTextil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignMaterialClothDetailService implements IDesignMaterialClothDetailService {

    @Autowired
    private DesignMaterialClothDetailRepository designMaterialClothDetailRepository;

    @Autowired
    private DesignMaterialClothDetailMapper designMaterialClothDetailMapper;


    @Override
    public DesignMaterialClothDetailResponse saveDesignMaterialClothDetail(DesignMaterialClothDetailRequest designMaterialClothDetailRequest) {
        try {
            DesignMaterialClothDetailEntity designMaterialClothDetail = designMaterialClothDetailMapper.designMaterialClothDetailRequestToDesignMaterialClothDetail(designMaterialClothDetailRequest);
            designMaterialClothDetailRepository.save(designMaterialClothDetail);
            return designMaterialClothDetailMapper.designMaterialClothDetailToDesignMaterialClothDetailResponse(designMaterialClothDetail);
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El detalle del diseño de materiales no se pudo crear.");
        }
    }

    @Override
    public List<DesignMaterialClothDetailResponse> getDesignMaterialClothDetail(Pageable pageable) {
        try {
            List<DesignMaterialClothDetailEntity> designMaterialClothDetails = designMaterialClothDetailRepository.findDesignMaterialCloth(pageable);
            List<DesignMaterialClothDetailResponse> designMaterialClothDetailResponses = new ArrayList<>();
            designMaterialClothDetails.stream().forEach(designMaterialClothDetail -> {
                designMaterialClothDetailResponses.add(designMaterialClothDetailMapper.designMaterialClothDetailToDesignMaterialClothDetailResponse(designMaterialClothDetail));
            });
            return designMaterialClothDetailResponses;
        } catch (Exception e) {
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los detalles del diseño de materiales no se pudieron encontrar.");
        }
    }

    @Override
    public DesignMaterialClothDetailResponse editedDesignMaterialClothDetail(DesignMaterialClothDetailRequest designMaterialClothDetailRequest, Long id) {
        DesignMaterialClothDetailEntity designMaterialClothDetail = findDesignMaterialClothDetailById(designMaterialClothDetailRepository.findById(id));
        if (designMaterialClothDetail.getStatus() == true) {
            designMaterialClothDetail.setColor(designMaterialClothDetailRequest.getColor());
            designMaterialClothDetail.setCodeColor(designMaterialClothDetailRequest.getCodeColor());
            designMaterialClothDetail.setIndication(designMaterialClothDetailRequest.getIndication());
            designMaterialClothDetail.setImage(designMaterialClothDetailRequest.getImage());
            designMaterialClothDetail.setImagePath(designMaterialClothDetailRequest.getImagePath());
            return designMaterialClothDetailMapper.designMaterialClothDetailToDesignMaterialClothDetailResponse(designMaterialClothDetail);
        }

        throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El detalle del diseño de materiales no fue encontrado.");
    }

    private DesignMaterialClothDetailEntity findDesignMaterialClothDetailById(Optional<DesignMaterialClothDetailEntity> optionalDesignMaterialClothDetailEntity) {
        return optionalDesignMaterialClothDetailEntity.orElseThrow(() -> new NotFoundTextil("P-404", HttpStatus.NOT_FOUND, "El detalle del diseño de materiales no fue encontrado"));
    }
}
