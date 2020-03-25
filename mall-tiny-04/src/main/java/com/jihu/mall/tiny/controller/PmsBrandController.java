package com.jihu.mall.tiny.controller;

import com.jihu.mall.tiny.common.api.CommonPage;
import com.jihu.mall.tiny.common.api.CommonResult;
import com.jihu.mall.tiny.common.api.ResultCode;
import com.jihu.mall.tiny.mbg.model.PmsBrand;
import com.jihu.mall.tiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理controller
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @Autowired
    private PmsBrandService pmsBrandService;

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("获取所有品牌列表")
    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> getBrandList(){
        List<PmsBrand> brands =pmsBrandService.findAllBrand();
        if(CollectionUtils.isEmpty(brands)){
            return new CommonResult<List<PmsBrand>>(ResultCode.SUCCESS,null);
        }
        return new CommonResult<List<PmsBrand>>(ResultCode.SUCCESS,brands);
    }

    @ApiOperation("添加品牌")
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

    @ApiOperation("更新指定id品牌信息")
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

    @ApiOperation("删除指定id的品牌")
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


    @ApiOperation("分页查询品牌列表")
    @GetMapping("/list")
    public CommonResult list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize ){

        List<PmsBrand> pmsBrands = pmsBrandService.findByPage(pageNum,pageSize);
        return new CommonResult(ResultCode.SUCCESS,CommonPage.restPage(pmsBrands));
    }

    @ApiOperation("获取指定id的品牌详情")
    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") long id){
        PmsBrand pmsBrand = pmsBrandService.findById(id);
        return new CommonResult(ResultCode.SUCCESS,pmsBrand);
    }
}
