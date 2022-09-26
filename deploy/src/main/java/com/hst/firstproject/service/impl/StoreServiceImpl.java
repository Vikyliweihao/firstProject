package com.hst.firstproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hst.firstproject.dao.StoreDao;
import com.hst.firstproject.entity.Store;
import com.hst.firstproject.service.StoreService;
import com.hst.firstproject.vo.AddStoreMessageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao storeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Store queryStore(String storeCode) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_code",storeCode);
        Store store = storeMapper.selectOne(queryWrapper);
        if (!Objects.isNull(store)){
            return store;
        }
        return null;
    }

    @Override
    public void insertStoreMessage(AddStoreMessageVo addStoreMessageVo) {
        Store store = new Store();
        BeanUtils.copyProperties(addStoreMessageVo,store);
        storeMapper.insert(store);
        try {
            redisTemplate.opsForValue().set(addStoreMessageVo.getStoreCode(),addStoreMessageVo.getStoreCode());
            System.out.println("成功存入redis中：" + addStoreMessageVo.getStoreCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
