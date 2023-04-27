package com.training.controller;

import com.training.config.variable.AppConstant;
import com.training.exception.CustomNotfoundException;
import com.training.model.TrainingEntity;
import com.training.model.response.GeneralResponse;
import com.training.repository.KaryawanJpaRepository;
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
public class TrainingRestController {

    @Autowired
    private TrainingJpaRepository trainingService;

    @RequestMapping(value = "/training",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> findAll(@RequestParam(name = "page") int page,
                                     @RequestParam(name = "size") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<TrainingEntity> allData = trainingService.getAllData(paging);
        if(allData.isEmpty())
            throw new CustomNotfoundException("data not found");
        return new ResponseEntity<>(new GeneralResponse<>(200, allData, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/training/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        TrainingEntity byId = trainingService.getById(id);
        if(byId==null)
            throw new CustomNotfoundException("data not found");
        return new ResponseEntity<>(new GeneralResponse<>(200, byId, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/training",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TrainingEntity training) {
        training.setCreatedDate(new Date());
        training.setUpdatedDate(new Date());
        TrainingEntity save = trainingService.save(training);
        return new ResponseEntity<>(new GeneralResponse<>(200, save, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/training",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody TrainingEntity training){
        if (training.getId() == null)
            throw new CustomNotfoundException("id training not found");
        TrainingEntity TrainingEntity = trainingService.getById(training.getId());
        training.setCreatedDate(TrainingEntity.getCreatedDate());
        training.setUpdatedDate(new Date());
        TrainingEntity save = trainingService.save(training);
        return new ResponseEntity<>(new GeneralResponse<>(200, save, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

    @RequestMapping(value = "/training/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        trainingService.deleteById(id);
        return new ResponseEntity<>(new GeneralResponse<>(200, null, AppConstant.MESSAGE_OK), HttpStatus.OK);
    }

}
