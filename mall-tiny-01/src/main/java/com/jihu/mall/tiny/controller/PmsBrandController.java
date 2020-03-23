package com.jihu.mall.tiny.controller;

import com.jihu.mall.tiny.common.api.CommonPage;
import com.jihu.mall.tiny.common.api.CommonResult;
import com.jihu.mall.tiny.common.api.ResultCode;
import com.jihu.mall.tiny.mbg.model.PmsBrand;
import com.jihu.mall.tiny.service.PmsBrandService;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理controller
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @Autowired
    private PmsBrandService pmsBrandService;

    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> getBrandList(){
        List<PmsBrand> brands =pmsBrandService.findAllBrand();
        if(CollectionUtils.isEmpty(brands)){
            return new CommonResult<List<PmsBrand>>(ResultCode.SUCCESS,null);
        }
        return new CommonResult<List<PmsBrand>>(ResultCode.SUCCESS,brands);
    }

    @PostMapping("/add")
    public CommonResult add(@RequestBody PmsBrand pmsBrand){
        int count = pmsBrandService.add(pmsBrand);
        if(count >= 1){
            LOGGER.debug("add Brand success:{}", pmsBrand);
            return  new CommonResult(ResultCode.SUCCESS);
        }
        LOGGER.debug("add Brand failed:{}", pmsBrand);
        return new CommonResult(ResultCode.FAILED);
    }

    @PutMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") long id,@RequestBody PmsBrand pmsBrand){
        pmsBrand.setId(id);
        int count = pmsBrandService.update(pmsBrand);
        if(count >= 1){
            LOGGER.debug("update Brand success:{}", pmsBrand);
            return new CommonResult(ResultCode.SUCCESS);
        }
        LOGGER.debug("update Brand failed:{}", pmsBrand);
        return new CommonResult(ResultCode.FAILED);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") long id){
        int count = pmsBrandService.delete(id);
        if(count >= 1){
            LOGGER.debug("delete Brand success:{}", id);
            return new CommonResult(ResultCode.SUCCESS);
        }
        LOGGER.debug("delete Brand failed:{}", id);
        return new CommonResult(ResultCode.FAILED);
    }


    @GetMapping("/list")
    public CommonResult list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize ){

        List<PmsBrand> pmsBrands = pmsBrandService.findByPage(pageNum,pageSize);
        return new CommonResult(ResultCode.SUCCESS,CommonPage.restPage(pmsBrands));
    }

    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") long id){
        PmsBrand pmsBrand = pmsBrandService.findById(id);
        return new CommonResult(ResultCode.SUCCESS,pmsBrand);
    }
}
