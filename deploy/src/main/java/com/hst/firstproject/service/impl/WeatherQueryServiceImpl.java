package com.hst.firstproject.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hst.firstproject.dto.WeatherDto;
import com.hst.firstproject.service.WeatherQueryService;
import com.hst.firstproject.vo.QueryWeatherVo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherQueryServiceImpl implements WeatherQueryService {

    @Override
    public WeatherDto queryWeather(QueryWeatherVo queryWeatherVo) {
        String url = "https://www.mxnzp.com/api/weather/forecast/";
        String city = queryWeatherVo.getCityName();
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append(city);
        sb.append("?");
        sb.append("app_id=syuolmlgqwnmtixk");
        sb.append("&");
        sb.append("app_secret=dEpJRGZacUhqelJxcVlUWFpoWHkvdz09");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cityName",city);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");


        HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(jsonObject), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(sb.toString(), HttpMethod.POST, null, String.class);
        System.out.println(exchange);
        String data = exchange.getBody();
        System.out.println(data);
        WeatherDto weatherDto = JSON.parseObject(data, WeatherDto.class);
        System.out.println(weatherDto);
        return weatherDto;
    }
}
