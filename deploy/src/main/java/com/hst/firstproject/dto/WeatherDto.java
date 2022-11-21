package com.hst.firstproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeatherDto {

    private String code;
    private String msg;
    private Item data;

    @Data
    public static class Item{
        /**
         * 城市
         */
        private String address;
        private String cityCode;
        private String reportTime;
        private List<Forecasts> forecasts;
    }

    @Data
    public static class Forecasts{
        private String date;
        private String dayOfWeek;
        private String dayWeather;
        private String nightWeather;
        private String dayTemp;
        private String nightTemp;
        private String dayWindDirection;
        private String nightWindDirection;
        private String dayWindPower;
        private String nightWindPower;
    }

}
