package com.hst.firstproject.service;


import com.hst.firstproject.entity.Store;
import com.hst.firstproject.vo.AddStoreMessageVo;

public interface StoreService {

    Store queryStore(String storeCode);

    void insertStoreMessage(AddStoreMessageVo addStoreMessageVo);

}
