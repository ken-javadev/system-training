package com.training.controller;

import com.training.config.variable.AppConstant;
import com.training.exception.CustomNotfoundException;
import com.training.model.KaryawanEntity;
import com.training.model.RekeningEntity;
import com.training.model.response.GeneralResponse;
import com.training.repository.KaryawanJpaRepository;
import com.training.repository.RekeningJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class RekeningRestController {

    @Autowired
    private RekeningJpaRepository rekeningService;
    @Autowired
    private KaryawanJpaRepository karyawanService;

    @RequestMapping(value = "/rekening",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> findAll(@RequestParam(name = "page") int page,
                                     @RequestParam(name = "size") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<RekeningEntity> allData = rekeningService.getAllData(paging);
        if(allData.isEmpty())
            throw new CustomNotfoundException("data not found");
        return new ResponseEntity<>(new GeneralResponse<>(200, allData, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/rekening/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        RekeningEntity byId = rekeningService.getById(id);
        if(byId==null)
            throw new CustomNotfoundException("data not found");
        return new ResponseEntity<>(new GeneralResponse<>(200, byId, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/rekening",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody RekeningEntity rekening) {
        KaryawanEntity byId = karyawanService.getById(rekening.getKaryawan().getId());
        if(byId==null)
            throw new CustomNotfoundException("karyawan not found");

        rekening.setKaryawan(byId);
        rekening.setCreatedDate(new Date());
        rekening.setUpdatedDate(new Date());
        RekeningEntity save = rekeningService.save(rekening);
        return new ResponseEntity<>(new GeneralResponse<>(200, save, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/rekening",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody RekeningEntity rekening){
        if (rekening.getId() == null)
            throw new CustomNotfoundException("id rekening not found");

        KaryawanEntity byId = karyawanService.getById(rekening.getKaryawan().getId());
        if(byId==null)
            throw new CustomNotfoundException("karywan not found");

        RekeningEntity RekeningEntity = rekeningService.getById(rekening.getId());
        rekening.setKaryawan(byId);
        rekening.setCreatedDate(RekeningEntity.getCreatedDate());
        rekening.setUpdatedDate(new Date());
        RekeningEntity save = rekeningService.save(rekening);
        return new ResponseEntity<>(new GeneralResponse<>(200, save, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/rekening/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        rekeningService.deleteById(id);
        return new ResponseEntity<>(new GeneralResponse<>(200, null, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

}
