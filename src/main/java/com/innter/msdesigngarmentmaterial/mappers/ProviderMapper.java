package com.innter.msdesigngarmentmaterial.mappers;

import com.innter.msdesigngarmentmaterial.dtos.request.ProviderRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.ProviderResponse;
import com.innter.msdesigngarmentmaterial.entities.ProviderEntity;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper implements IProviderMapper{
    @Override
    public ProviderResponse providerToProviderResponse(ProviderEntity providerEntity) {
        ProviderResponse providerResponse = new ProviderResponse();
        providerResponse.setId(providerEntity.getId());
        providerResponse.setName(providerEntity.getName());
        providerResponse.setProviderKey(providerEntity.getProviderKey());
        return providerResponse;
    }

    @Override
    public ProviderEntity providerRequestToProvider(ProviderRequest providerRequest) {
        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setName(providerRequest.getName());
        providerEntity.setProviderKey(providerRequest.getProviderKey());
        return providerEntity;
    }
}
