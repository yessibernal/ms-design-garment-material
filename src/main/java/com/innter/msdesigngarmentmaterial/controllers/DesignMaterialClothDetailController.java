package com.innter.msdesigngarmentmaterial.controllers;

import com.innter.msdesigngarmentmaterial.dtos.request.DesignMaterialClothDetailRequest;
import com.innter.msdesigngarmentmaterial.services.IDesignMaterialClothDetailService;
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
@RequestMapping(value = "/api/designMaterialClothDetail")
public class DesignMaterialClothDetailController {
    @Autowired
    private IDesignMaterialClothDetailService designMaterialClothDetailService;

    @Autowired
    private ResponseUtils responseUtils;

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDesignMaterialClothDetail(@RequestBody DesignMaterialClothDetailRequest designMaterialClothDetailRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(designMaterialClothDetailService.saveDesignMaterialClothDetail(designMaterialClothDetailRequest),
                "El detalle del diseño de materiales se creo de manera exitosa.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole ('DESIGN_READ','ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDesignMaterialClothDetail(@RequestParam(required = false) Integer pageIndex,
                                                          @RequestParam(required = false) Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designMaterialClothDetailService.getDesignMaterialClothDetail(pageable),
                "Los detalles  del diseño de materiales se encontraron correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole ('ADMIN','DESIGN_WRITE')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDesignMaterialClothDetail( @RequestBody DesignMaterialClothDetailRequest designMaterialClothDetailRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(designMaterialClothDetailService.editedDesignMaterialClothDetail(designMaterialClothDetailRequest,id ),
                "El detalle del diseño de materiales con el id:" + id + " se actualizo correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}
