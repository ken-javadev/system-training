package com.training.controller;

import com.training.config.variable.AppConstant;
import com.training.exception.CustomNotfoundException;
import com.training.model.KaryawanEntity;
import com.training.model.KaryawanTrainingEntity;
import com.training.model.TrainingEntity;
import com.training.model.response.GeneralResponse;
import com.training.repository.KaryawanJpaRepository;
import com.training.repository.KaryawanTrainingJpaRepository;
import com.training.repository.TrainingJpaRepository;
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
public class KaryawanTrainingRestController {

    @Autowired
    private KaryawanJpaRepository karyawanService;
    @Autowired
    private TrainingJpaRepository trainingService;
    @Autowired
    private KaryawanTrainingJpaRepository karyawanTrainingService;

    @RequestMapping(value = "/karyawan-training",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> findAll(@RequestParam(name = "page") int page,
                                     @RequestParam(name = "size") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<KaryawanTrainingEntity> allData = karyawanTrainingService.getAllData(paging);
        if(allData.isEmpty())
            throw new CustomNotfoundException("data not found");
        return new ResponseEntity<>(new GeneralResponse<>(200, allData, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/karyawan-training/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        KaryawanTrainingEntity byId = karyawanTrainingService.getById(id);
        if(byId==null)
            throw new CustomNotfoundException("data not found");
        return new ResponseEntity<>(new GeneralResponse<>(200, byId, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/karyawan-training",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody KaryawanTrainingEntity karyawanTraining) {
        KaryawanEntity byId = karyawanService.getById(karyawanTraining.getKaryawan().getId());
        if(byId==null)
            throw new CustomNotfoundException("karyawan not found");

        TrainingEntity byId1 = trainingService.getById(karyawanTraining.getTraining().getId());
        if(byId1==null)
            throw new CustomNotfoundException("training not found");

        karyawanTraining.setKaryawan(byId);
        karyawanTraining.setTraining(byId1);
        karyawanTraining.setCreatedDate(new Date());
        karyawanTraining.setUpdatedDate(new Date());
        KaryawanTrainingEntity save = karyawanTrainingService.save(karyawanTraining);
        return new ResponseEntity<>(new GeneralResponse<>(200, save, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/karyawan-training",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody KaryawanTrainingEntity karyawanTraining){
        if (karyawanTraining.getId() == null)
            throw new CustomNotfoundException("id karyawan training not found");

        KaryawanEntity byId = karyawanService.getById(karyawanTraining.getKaryawan().getId());
        if(byId==null)
            throw new CustomNotfoundException("karyawan not found");

        TrainingEntity byId1 = trainingService.getById(karyawanTraining.getTraining().getId());
        if(byId1==null)
            throw new CustomNotfoundException("training not found");

        KaryawanTrainingEntity KaryawanTrainingEntity = karyawanTrainingService.getById(karyawanTraining.getId());
        karyawanTraining.setCreatedDate(KaryawanTrainingEntity.getCreatedDate());
        karyawanTraining.setUpdatedDate(new Date());
        KaryawanTrainingEntity save = karyawanTrainingService.save(karyawanTraining);
        return new ResponseEntity<>(new GeneralResponse<>(200, save, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/karyawan-training/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        karyawanTrainingService.deleteById(id);
        return new ResponseEntity<>(new GeneralResponse<>(200, null, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

}
