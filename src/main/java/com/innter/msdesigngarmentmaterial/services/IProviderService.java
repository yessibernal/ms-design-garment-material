package com.innter.msdesigngarmentmaterial.services;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignRequestStatus;
import com.innter.msdesigngarmentmaterial.dtos.request.ProviderRequest;
import com.innter.msdesigngarmentmaterial.dtos.response.ProviderResponse;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IProviderService {
    ProviderResponse saveProvider (ProviderRequest newProviderRequest);

    List<ProviderResponse> getProviders (Pageable pageable);

    ProviderResponse editedProvider (ProviderRequest newProviderRequest, Long id);

    ProviderResponse editedProviderByStatus (DesignRequestStatus designRequestStatus, Long id);
}
