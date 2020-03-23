package com.jihu.mall.tiny.service;

import com.github.pagehelper.PageHelper;
import com.jihu.mall.tiny.mbg.mapper.PmsBrandMapper;
import com.jihu.mall.tiny.mbg.model.PmsBrand;
import com.jihu.mall.tiny.mbg.model.PmsBrandExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("pmsBrandService")
public class PmsBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    public List<PmsBrand> findAllBrand() {
        List<PmsBrand> pmsBrands = pmsBrandMapper.selectByExample(new PmsBrandExample());
        return pmsBrands;
    }

    public int add(PmsBrand pmsBrand) {
        int count = pmsBrandMapper.insert(pmsBrand);
        return count;
    }

    public int update(PmsBrand pmsBrand) {
        PmsBrand temp = pmsBrandMapper.selectByPrimaryKey(pmsBrand.getId());
        if(Optional.ofNullable(temp).isPresent()){
            BeanUtils.copyProperties(pmsBrand,temp);
            return  pmsBrandMapper.updateByPrimaryKey(temp);
        }
        return 0;
    }

    public int delete(long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    public List<PmsBrand> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    public PmsBrand findById(long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }
}
