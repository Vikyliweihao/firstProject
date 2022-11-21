package com.hst.firstproject.service;

import com.hst.firstproject.dto.WeatherDto;
import com.hst.firstproject.vo.QueryWeatherVo;

public interface WeatherQueryService {

    WeatherDto queryWeather(QueryWeatherVo queryWeatherVo);
}
