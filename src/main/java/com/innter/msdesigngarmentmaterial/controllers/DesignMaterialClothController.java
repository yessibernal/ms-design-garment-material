package com.innter.msdesigngarmentmaterial.controllers;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothRequest;
import com.innter.msdesigngarmentmaterial.services.IDesignMaterialClothService;
import com.innter.msdesigngarmentmaterial.utils.ResponseUtils;
import com.innter.msdesigngarmentmaterial.utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/designMaterialCloth")
public class DesignMaterialClothController {
    @Autowired
    private IDesignMaterialClothService designMaterialClothService;

    @Autowired
    private ResponseUtils responseUtils;

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesignMaterialCloth(@RequestBody DesignMaterialClothRequest designMaterialClothRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(designMaterialClothService.saveDesignMaterialCloth(designMaterialClothRequest),

                "El Material de las telas se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }
}
