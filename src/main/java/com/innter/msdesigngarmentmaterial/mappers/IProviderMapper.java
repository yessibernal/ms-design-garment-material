package com.innter.msdesigngarmentmaterial.mappers;

import com.innter.msdesigngarmentmaterial.dtos.request.ProviderRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.ProviderResponse;
import com.innter.msdesigngarmentmaterial.entities.ProviderEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IProviderMapper {

    ProviderResponse providerToProviderResponse (ProviderEntity providerEntity);

    ProviderEntity providerRequestToProvider (ProviderRequest providerRequest);
}
