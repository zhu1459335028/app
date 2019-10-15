package com.secusoft.ssw.model.viewobject.Request;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel("从其他平台同步过来的气体集")
public class GasVo {
    private Data data;
    private String key;
    private String signkey;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String device_id;
    private String time;

    public String getTime() {
        return time;
    }

    private String pm25; //pm25
    private String pm10; //pm10
    private String noise;//噪音
    private String temperature;// 温度
    private String humidity;//湿度
    private String winddirection;// 风向
    private String windspeed;//风速
    private String tsp;//颗粒物
    private String pressure;//大气压力


    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getTsp() {
        return tsp;
    }

    public void setTsp(String tsp) {
        this.tsp = tsp;
    }


    public static class Data {
        @JsonProperty("CO")
        private String CO;
        @JsonProperty("CO2")
        private String CO2;
        @JsonProperty("H2")
        private String H2;
        @JsonProperty("H2S")
        private String H2S;
        @JsonProperty("NH3")
        private String NH3;
        @JsonProperty("NO2")
        private String NO2;
        @JsonProperty("O2")
        private String O2;
        @JsonProperty("O3")
        private String O3;
        @JsonProperty("PM2.5")
        private String PM25;
        @JsonProperty("PM10")
        private String PM10;
        @JsonProperty("SO2")
        private String SO2;
        @JsonProperty("TVOC")
        private String TVOC;
        @JsonProperty("atmosphericpressure")
        private String atmosphericpressure;
        @JsonProperty("device_id")
        private String device_id;
        @JsonProperty("humidity")
        private String humidity;
        @JsonProperty("illuminance")
        private String illuminance;
        @JsonProperty("methane")
        private String methane;
        @JsonProperty("noise")
        private String noise;
        @JsonProperty("temperature")
        private String temperature;
        @JsonProperty("winddirection")
        private String winddirection;
        @JsonProperty("windpower")
        private String windpower;
        @JsonProperty("windspeed")
        private String windspeed;

        @JSONField(name = "CO")
        public String getCO() {
            return CO;
        }

        public void setCO(String CO) {
            this.CO = CO;
        }

        @JSONField(name = "CO2")
        public String getCO2() {
            return CO2;
        }

        public void setCO2(String CO2) {
            this.CO2 = CO2;
        }

        @JSONField(name = "H2")
        public String getH2() {
            return H2;
        }

        public void setH2(String h2) {
            H2 = h2;
        }

        @JSONField(name = "H2S")
        public String getH2S() {
            return H2S;
        }

        public void setH2S(String h2S) {
            H2S = h2S;
        }

        @JSONField(name = "NH3")
        public String getNH3() {
            return NH3;
        }

        public void setNH3(String NH3) {
            this.NH3 = NH3;
        }

        @JSONField(name = "NO2")
        public String getNO2() {
            return NO2;
        }

        public void setNO2(String NO2) {
            this.NO2 = NO2;
        }

        @JSONField(name = "O2")
        public String getO2() {
            return O2;
        }

        public void setO2(String o2) {
            O2 = o2;
        }

        @JSONField(name = "O3")
        public String getO3() {
            return O3;
        }

        public void setO3(String o3) {
            O3 = o3;
        }

        @JSONField(name = "PM2.5")
        public String getPM25() {
            return PM25;
        }

        public void setPM25(String PM25) {
            this.PM25 = PM25;
        }

        @JSONField(name = "PM10")
        public String getPM10() {
            return PM10;
        }

        public void setPM10(String PM10) {
            this.PM10 = PM10;
        }

        @JSONField(name = "SO2")
        public String getSO2() {
            return SO2;
        }

        public void setSO2(String SO2) {
            this.SO2 = SO2;
        }

        @JSONField(name = "TVOC")
        public String getTVOC() {
            return TVOC;
        }

        public void setTVOC(String TVOC) {
            this.TVOC = TVOC;
        }

        public String getAtmosphericpressure() {
            return atmosphericpressure;
        }

        public void setAtmosphericpressure(String atmosphericpressure) {
            this.atmosphericpressure = atmosphericpressure;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getIlluminance() {
            return illuminance;
        }

        public void setIlluminance(String illuminance) {
            this.illuminance = illuminance;
        }

        public String getMethane() {
            return methane;
        }

        public void setMethane(String methane) {
            this.methane = methane;
        }

        public String getNoise() {
            return noise;
        }

        public void setNoise(String noise) {
            this.noise = noise;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWinddirection() {
            return winddirection;
        }

        public void setWinddirection(String winddirection) {
            this.winddirection = winddirection;
        }

        public String getWindpower() {
            return windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSignkey() {
        return signkey;
    }

    public void setSignkey(String signkey) {
        this.signkey = signkey;
    }

}
