package io.transwarp.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.transwarp.util.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/controller/")
@Api(tags = "baseInfoCompany", description = "数据接口")
public class controller {

    private String url = "http://***:28142/transwarp/bifrost/api";

    //获取swagger json
    @RequestMapping(value = "getSwagger", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSwagger(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " +
                "WG-App-Version, WG-Device-Id, WG-Network-Type, WG-Vendor, WG-OS-Type, WG-OS-Version, WG-Device-Model, WG-CPU, WG-Sid, WG-App-Id, WG-Token");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json;charset=UTF-8");
        //String path = "/opt/apache-tomcat-8.5.61/webapps/swagger.json";
        String path = "D:\\工作文件\\天津港代码\\UDTF\\SpringMVC_Swagger\\src\\main\\resources\\swagger.json";
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readLine = bufferedReader.readLine();
        String jsonObject = "";
        while (readLine != null) {
            jsonObject += readLine;
            readLine = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();
        return JSONObject.parseObject(jsonObject);
    }

    //GPS数据接口
    @ApiOperation(value = "单车非跨天24小时数据")
    @RequestMapping(value = "singleTheDay24", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start_time", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "end_time", value = "截止时间", dataType = "String"),
            @ApiImplicitParam(name = "vehicle_no", value = "车牌号", dataType = "String"),
            @ApiImplicitParam(name = "tableName", value = "表名", dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject singleTheDay24(String start_time, String end_time, String vehicle_no, String tableName,
                                     String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("start_time", start_time);
        params.put("end_time", end_time);
        params.put("vehicle_no", vehicle_no);
        params.put("tableName", tableName);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }


    @ApiOperation(value = "单车非跨天24小时数据")
    @RequestMapping(value = "singleMoreDay24", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start_time", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "end_time", value = "截止时间", dataType = "String"),
            @ApiImplicitParam(name = "vehicle_no", value = "车牌号", dataType = "String"),
            @ApiImplicitParam(name = "tableName1", value = "表名1", dataType = "string"),
            @ApiImplicitParam(name = "tableName2", value = "表名2", dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject singleMoreDay24(String start_time, String end_time, String vehicle_no, String tableName1,
                                      String tableName2,String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("start_time", start_time);
        params.put("end_time", end_time);
        params.put("vehicle_no", vehicle_no);
        params.put("tableName1", tableName1);
        params.put("tableName2", tableName2);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "单车非跨天24小时数据")
    @RequestMapping(value = "roadTruck10000", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject roadTruck10000(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "多车跨天24小时数据")
    @RequestMapping(value = "moreMoreDay24", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start_time", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "end_time", value = "截止时间", dataType = "String"),
            @ApiImplicitParam(name = "vehicle_nos", value = "多个车牌号，以英文逗号隔开", dataType = "String"),
            @ApiImplicitParam(name = "tableName1", value = "表名1", dataType = "string"),
            @ApiImplicitParam(name = "tableName2", value = "表名2", dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject moreMoreDay24(String start_time, String end_time, String vehicle_nos, String tableName1,
                                      String tableName2,String apiKey, String accessToken) throws Exception {
        String[] vehicle_no = vehicle_nos.split(",");
        if(vehicle_no.length != 20) {
            return JSONObject.parseObject("{\"code\": \"500\",\"message\": \"输出车牌号的格式有误\"}");
        }
        JSONObject params = new JSONObject();
        for(int i = 0; i < vehicle_no.length; i++) {
            params.put("vehicle_no"+(i + 1), vehicle_no[i]);
        }
        params.put("start_time", start_time);
        params.put("end_time", end_time);
        params.put("tableName1", tableName1);
        params.put("tableName2", tableName2);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "多车非跨天24小时数据")
    @RequestMapping(value = "moreTheDay24", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start_time", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "end_time", value = "截止时间", dataType = "String"),
            @ApiImplicitParam(name = "vehicle_nos", value = "多个车牌号，以英文逗号隔开", dataType = "String"),
            @ApiImplicitParam(name = "tableName", value = "表名", dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject moreTheDay24(String start_time, String end_time, String vehicle_nos, String tableName,
                                   String apiKey, String accessToken) throws Exception {
        String[] vehicle_no = vehicle_nos.split(",");
        if(vehicle_no.length != 20) {
            return JSONObject.parseObject("{\"code\": \"500\",\"message\": \"输出车牌号的格式有误\"}");
        }
        JSONObject params = new JSONObject();
        for(int i = 0; i < vehicle_no.length; i++) {
            params.put("vehicle_no"+(i + 1), vehicle_no[i]);
        }
        params.put("start_time", start_time);
        params.put("end_time", end_time);
        params.put("tableName", tableName);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "两客一危GPS实时最新1000条数据")
    @RequestMapping(value = "passengerDanger1000", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject passengerDanger1000(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "多车非跨天24小时数据")
    @RequestMapping(value = "moreNewLocation", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vehicle_nos", value = "多个车牌号，以英文逗号隔开", dataType = "String"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject moreNewLocation(String vehicle_nos, String apiKey, String accessToken) throws Exception {
        String[] vehicle_no = vehicle_nos.split(",");
        if(vehicle_no.length != 20) {
            return JSONObject.parseObject("{\"code\": \"500\",\"message\": \"输出车牌号的格式有误\"}");
        }
        JSONObject params = new JSONObject();
        for(int i = 0; i < vehicle_no.length; i++) {
            params.put("vehicle_no"+(i + 1), vehicle_no[i]);
        }
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "12吨普货-20S前1分半数据")
    @RequestMapping(value = "twelveTonOneMin", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject twelveTonOneMin(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "12吨普货-20S前1分半数据统计")
    @RequestMapping(value = "twelveTonOneMinCount", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject twelveTonOneMinCount(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    //设备类数据接口
    @ApiOperation(value = "岸桥设备信号-每5S返回数据")
    @RequestMapping(value = "anqiaoDeviceSignal5", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timeStamp1", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "timeStamp2", value = "截止时间", dataType = "String"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject anqiaoDeviceSignal5(String timeStamp1, String timeStamp2,
                                          String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("timeStamp1", timeStamp1);
        params.put("timeStamp2", timeStamp2);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "岸桥-设备实时-近10分钟最大风速")
    @RequestMapping(value = "anqiaoDeviceRealTimeMaxWs10", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject anqiaoDeviceRealTimeMaxWs10(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "岸桥设备实时-每5S返回数据")
    @RequestMapping(value = "anqiaoDeviceRealTime5", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timeStamp1", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "timeStamp2", value = "截止时间", dataType = "String"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject anqiaoDeviceRealTime5(String timeStamp1, String timeStamp2,
                                          String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("timeStamp1", timeStamp1);
        params.put("timeStamp2", timeStamp2);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "岸桥-设备实时-近2分钟最大风速")
    @RequestMapping(value = "anqiaoDeviceRealTimeMaxWs2", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject anqiaoDeviceRealTimeMaxWs2(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "岸桥-设备实时-当天最大风速")
    @RequestMapping(value = "anqiaoDeviceRealTimeMaxWsDay", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject anqiaoDeviceRealTimeMaxWsDay(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "岸桥-设备实时-设备实时最大风速")
    @RequestMapping(value = "anqiaoDeviceRealTimeWsReal", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject anqiaoDeviceRealTimeWsReal(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "岸桥设备运行-每1小时返回数据")
    @RequestMapping(value = "anqiaoDeviceMotionOneHour", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timeStamp1", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "timeStamp2", value = "截止时间", dataType = "String"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject anqiaoDeviceMotionOneHour(String timeStamp1, String timeStamp2,
                                            String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("timeStamp1", timeStamp1);
        params.put("timeStamp2", timeStamp2);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "当前生产设备数量及状态(岸桥)")
    @RequestMapping(value = "anqiaoCurrentDeviceNumAndStatus", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject anqiaoCurrentDeviceNumAndStatus(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "当前生产设备数量及状态(无人集卡)")
    @RequestMapping(value = "zhongqiCurrentDeviceNumAndStatus", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject zhongqiCurrentDeviceNumAndStatus(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "设备生产任务计划(无人集卡)")
    @RequestMapping(value = "zhongqiDeviceProducePlan", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject zhongqiDeviceProducePlan(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    //大气类数据接口
    @ApiOperation(value = "全国预警-全部数据")
    @RequestMapping(value = "getCountryWarnData", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject getCountryWarnData(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "天津港公司24小时预报")
    @RequestMapping(value = "companyForecast", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "公司ID", dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject companyForecast(String companyId,
                                     String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("companyId", companyId);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "8天预报")
    @RequestMapping(value = "eightDayForecast", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", value = "公司纬度", dataType = "string"),
            @ApiImplicitParam(name = "lng", value = "公司经度", dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject eightDayForecast(String lat, String lng,
                                      String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("lat", lat);
        params.put("lng", lng);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "天文潮位信息")
    @RequestMapping(value = "astronomyTide", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", dataType = "string"),
            @ApiImplicitParam(name = "endDate", value = "截止时间，格式：yyyy-MM-dd HH:mm:ss", dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject astronomyTide(String startDate, String endDate,
                                       String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "滨海新区预警数据")
    @RequestMapping(value = "getRealTimeWarn", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject getRealTimeWarn(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "站点气象信息")
    @RequestMapping(value = "awsData", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stationNum", value = "站点编号", dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject awsData(String stationNum, String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("stationNum", stationNum);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "全国预警-指定预警编码")
    @RequestMapping(value = "getCodeCountryWarnData", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "预警编号", dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject getCodeCountryWarnData(String code, String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("code", code);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }


    //市交委
    @ApiOperation(value = "市交委-旅客船员统计量")
    @RequestMapping(value = "getTravelPersonCount", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject getTravelPersonCount(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "市交委-邮轮客轮明细")
    @RequestMapping(value = "getOceanLinerCount", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject getOceanLinerCount(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "市交委-来港作业船舶测试")
    @RequestMapping(value = "getTJPWorkTest", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject getTJPWorkTest(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "集装箱船舶配载（含参）-外理")
    @RequestMapping(value = "getContainerBoastTake", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evoyage", value = "evoyage", dataType = "String"),
            @ApiImplicitParam(name = "ename", value = "ename", dataType = "String"),
            @ApiImplicitParam(name = "ivoyage", value = "ivoyage", dataType = "String"),
            @ApiImplicitParam(name = "cname1", value = "cname1", dataType = "String"),
            @ApiImplicitParam(name = "imo", value = "imo", dataType = "String"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject getContainerBoastTake(String evoyage, String ename,String ivoyage, String cname1, String imo,
                                            String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("evoyage", evoyage);
        params.put("ename", ename);
        params.put("ivoyage", ivoyage);
        params.put("cname1", cname1);
        params.put("imo", imo);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "ecevent（集装箱北区最近一小时）含参数")
    @RequestMapping(value = "getEceventContainerOneHour", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "time", dataType = "String", defaultValue = "2020-12-14 13:00:49"),
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject getEceventContainerOneHour(String time, String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        params.put("time", time);
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    @ApiOperation(value = "在场箱数据（集装箱北区）")
    @RequestMapping(value = "getBoxData", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiKey", value = "API Key", dataType = "String"),
            @ApiImplicitParam(name = "accessToken", value = "Access Token", dataType = "String")
    })
    @ResponseBody
    public JSONObject getBoxData(String apiKey, String accessToken) throws Exception {
        JSONObject params = new JSONObject();
        JSONObject result = HttpUtil.post(url, accessToken, apiKey, params.toJSONString());
        //解析结果数据
        JSONObject data = result.getJSONObject("data");
        JSONObject value = data.getJSONObject("value");
        return result;
    }

    //获取swagger页面
    @RequestMapping(value = "docs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDocs() {
        return new ModelAndView("/index");
    }

    //获取swagger页面
    @RequestMapping(value = "manager", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getManager() {
        return new ModelAndView("/Manager");
    }

    public static void main(String[] args) throws Exception {
//        new controller().singleTheDay24("1608534025000", "1608534265724", "0冀FR239",
//                "20201221", "450947606496153600", "449465485499830272");
    }
}
