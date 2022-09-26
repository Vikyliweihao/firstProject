package com.hst.firstproject.controller;

import com.hst.firstproject.entity.Store;
import com.hst.firstproject.service.StoreService;
import com.hst.firstproject.vo.AddStoreMessageVo;
import com.hst.firstproject.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storeMessage")
public class StoreMessageController {


    @Autowired
    private StoreService storeMessageService;

    @PostMapping("/queryStoreCode")
    public Store queryStoreCode(@RequestBody StoreVo storeVo){
        System.out.println(storeVo);
        Store store = storeMessageService.queryStore(storeVo.getStoreCode());
        System.out.println(store.getLocation() + ":" +store.getStoreCode());
        return store;
    }


    @PostMapping("/addStoreMessage")
    public void addStoreMessage(@RequestBody AddStoreMessageVo addStoreMessageVo){

        storeMessageService.insertStoreMessage(addStoreMessageVo);
        System.out.println("新增一条门店信息");
    }


}
