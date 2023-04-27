package com.training.controller;

import com.training.config.variable.AppConstant;
import com.training.exception.CustomNotfoundException;
import com.training.model.DetailKaryawanEntity;
import com.training.model.KaryawanEntity;
import com.training.model.response.GeneralResponse;
import com.training.repository.DetailKaryawanJpaRepository;
import com.training.repository.KaryawanJpaRepository;
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
public class KaryawanRestController {

    @Autowired
    private KaryawanJpaRepository karyawanService;
    @Autowired
    private DetailKaryawanJpaRepository detailKaryawanJpaRepository;

    @RequestMapping(value = "/karyawan",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> findAll(@RequestParam(name = "page") int page,
                                     @RequestParam(name = "size") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<KaryawanEntity> allData = karyawanService.getAllData(paging);
        if(allData.isEmpty())
            throw new CustomNotfoundException("data not found");
        return new ResponseEntity<>(new GeneralResponse<>(200, allData, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/karyawan/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        KaryawanEntity byId = karyawanService.getById(id);
        if(byId==null)
            throw new CustomNotfoundException("data not found");
        return new ResponseEntity<>(new GeneralResponse<>(200, byId, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/karyawan",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody KaryawanEntity karyawan) {
        karyawan.setCreatedDate(new Date());
        karyawan.setUpdatedDate(new Date());
        karyawan.getDetailKaryawanEntity().setKaryawan(karyawan);
        karyawan.getDetailKaryawanEntity().setCreatedDate(new Date());
        karyawan.getDetailKaryawanEntity().setUpdatedDate(new Date());
        KaryawanEntity save = karyawanService.save(karyawan);
        return new ResponseEntity<>(new GeneralResponse<>(200, save, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/karyawan",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody KaryawanEntity karyawan){
        if (karyawan.getId() == null)
            throw new CustomNotfoundException("id karyawan not found");
        if (karyawan.getDetailKaryawanEntity().getId() == null)
            throw new CustomNotfoundException("id karyawan detail not found");

        KaryawanEntity karyawanEntity = karyawanService.getById(karyawan.getId());
        DetailKaryawanEntity detailKaryawanEntity = detailKaryawanJpaRepository.getById(karyawan.getDetailKaryawanEntity().getId());

        karyawan.setCreatedDate(karyawanEntity.getCreatedDate());
        karyawan.setUpdatedDate(new Date());
        karyawan.getDetailKaryawanEntity().setKaryawan(karyawan);
        karyawan.getDetailKaryawanEntity().setCreatedDate(detailKaryawanEntity.getCreatedDate());
        karyawan.getDetailKaryawanEntity().setUpdatedDate(new Date());
        KaryawanEntity save = karyawanService.save(karyawan);
        return new ResponseEntity<>(new GeneralResponse<>(200, save, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/karyawan/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        karyawanService.deleteById(id);
        return new ResponseEntity<>(new GeneralResponse<>(200, null, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

}
