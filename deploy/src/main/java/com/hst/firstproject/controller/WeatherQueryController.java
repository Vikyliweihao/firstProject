package com.hst.firstproject.controller;

import com.hst.firstproject.dto.WeatherDto;
import com.hst.firstproject.entity.Store;
import com.hst.firstproject.service.WeatherQueryService;
import com.hst.firstproject.vo.QueryWeatherVo;
import com.hst.firstproject.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherQueryController {

    @Autowired
    private WeatherQueryService weatherQueryService;

    @PostMapping("/query")
    public WeatherDto queryWeather(@RequestBody QueryWeatherVo queryWeatherVo){
        WeatherDto weather = weatherQueryService.queryWeather(queryWeatherVo);
        return weather;
    }
}
