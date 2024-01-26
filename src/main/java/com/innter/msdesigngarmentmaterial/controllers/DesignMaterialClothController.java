package com.innter.msdesigngarmentmaterial.controllers;

import com.innter.msdesigngarmentmaterial.dtos.DesignMaterialClothEditedDto;
import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothRequest;
import com.innter.msdesigngarmentmaterial.dtos.request.DesignRequestStatus;
import com.innter.msdesigngarmentmaterial.services.IDesignMaterialClothService;
import com.innter.msdesigngarmentmaterial.utils.ResponseUtils;
import com.innter.msdesigngarmentmaterial.utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAnyRole ('DESIGN_READ','ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesignMaterialCloth(@RequestParam(required = false) Integer pageIndex,
                                                    @RequestParam(required = false) Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designMaterialClothService.getDesignMaterialCloth(pageable),
                "Los Materiales de las telas se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignMaterialClothByStatus(@RequestBody DesignRequestStatus designRequestStatus, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designMaterialClothService.editedDesignMaterialClothByStatus(designRequestStatus, id),
                "El Material de las telas con el id:" + id + " cambio su status correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignMaterialClothDetail(@RequestBody DesignMaterialClothEditedDto designMaterialClothEditedDto, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(
                designMaterialClothService.editedDesignMaterialCloth(designMaterialClothEditedDto,id),
                "El Material de las telas con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}
