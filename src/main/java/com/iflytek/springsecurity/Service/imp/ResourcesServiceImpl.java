package com.iflytek.springsecurity.Service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.iflytek.springsecurity.Service.ResourcesService;
import com.iflytek.springsecurity.entity.Resources;
import com.iflytek.springsecurity.mapper.ResourcesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cool
 * @version V1.0
 * @className ResourcesServiceImpl
 * @description Code Is Poetry.
 * @createDate 2019年05月22日
 */
@Slf4j
@Service
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    ResourcesMapper resourcesMapper;


    @Override
    public List<Resources> queryAll() {
        QueryWrapper<Resources> queryWrapper = Wrappers.query();
        return resourcesMapper.selectList(queryWrapper);
    }
}
