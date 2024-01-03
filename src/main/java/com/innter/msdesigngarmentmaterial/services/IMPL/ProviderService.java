package com.innter.msdesigngarmentmaterial.services.IMPL;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignRequestStatus;
import com.innter.msdesigngarmentmaterial.dtos.request.ProviderRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.ProviderResponse;
import com.innter.msdesigngarmentmaterial.entities.ProviderEntity;
import com.innter.msdesigngarmentmaterial.exceptions.BadRequestTextil;
import com.innter.msdesigngarmentmaterial.exceptions.NotFoundTextil;
import com.innter.msdesigngarmentmaterial.mappers.ProviderMapper;
import com.innter.msdesigngarmentmaterial.repositories.ProviderRepository;
import com.innter.msdesigngarmentmaterial.services.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderService implements IProviderService {
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public ProviderResponse saveProvider(ProviderRequest newProviderRequest) {
        try {
            ProviderEntity providerEntity = providerMapper.providerRequestToProvider(newProviderRequest);
            providerRepository.save(providerEntity);
            return providerMapper.providerToProviderResponse(providerEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El proveedor no se pudo crear.");
        }
    }

    @Override
    public List<ProviderResponse> getProviders(Pageable pageable) {

        try {
            List<ProviderEntity> providerEntities =providerRepository.findProviders(pageable);
            List<ProviderResponse>providerResponses = new ArrayList<>();
            providerEntities.stream().forEach(providerResponse ->{
                providerResponses.add(providerMapper.providerToProviderResponse(providerResponse));
            });
            return providerResponses;
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "Los proveedores no se pudieron encontrar.");
        }
    }

    @Override
    public ProviderResponse editedProvider(ProviderRequest newProviderRequest, Long id) {
        ProviderEntity providerEntity = findProviderById(providerRepository.findById(id));

        if(providerEntity.getStatus() == true){
            providerEntity.setName(newProviderRequest.getName());
            providerRepository.save(providerEntity);
            return providerMapper.providerToProviderResponse(providerEntity);
        }
        throw new BadRequestTextil("P-404", HttpStatus.NOT_FOUND, "El proveedor no tiene un estado activo");
    }

    @Override
    public ProviderResponse editedProviderByStatus(DesignRequestStatus designRequestStatus, Long id) {
        try {
            ProviderEntity providerEntity = providerRepository.findProviderStatusById(id);
            providerEntity.setStatus(designRequestStatus.getStatus());
            providerRepository.save(providerEntity);
            return providerMapper.providerToProviderResponse(providerEntity);
        }catch (Exception e){
            throw new BadRequestTextil("P-400", HttpStatus.BAD_REQUEST, "El proveedor no fue encontrado.");
        }
    }

    private ProviderEntity findProviderById (Optional<ProviderEntity> optionalProvider){
        return optionalProvider.orElseThrow(()-> new NotFoundTextil( "P-404", HttpStatus.NOT_FOUND,"El proveedor no fue encontrado."));
    }
}
