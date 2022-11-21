package com.hst.firstproject.utils;

import com.alibaba.fastjson.JSONObject;
import com.hst.util.apitools.RestTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import javax.ws.rs.client.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.core.Response;

@Slf4j
public class HttpHelper {

	private static final int OK = 200;

	/**
	 * GET请求
	 *
	 * @param apiUrl
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String apiUrl, String paramStr)  {
		RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
		setUtf8(restTemplate);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl,String.class,paramStr);
		return responseEntity.getBody();
	}

	private static void setUtf8(RestTemplate restTemplate){
		if (null == restTemplate || ObjectUtils.isEmpty(restTemplate.getMessageConverters())) {
			return;
		}
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		for (int i = 0; i < messageConverters.size(); i++) {
			HttpMessageConverter<?> httpMessageConverter = messageConverters.get(i);
			if (httpMessageConverter.getClass().equals(StringHttpMessageConverter.class)) {
				messageConverters.set(i, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			}
		}

	}

	/**
	 * GET请求
	 *
	 * @param apiUrl
	 * @param apiUsername
	 * @param apiPassword
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String apiUrl, String apiUsername, String apiPassword, String paramStr) throws Exception {
		RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
		setUtf8(restTemplate);
		if(StringUtils.isNotBlank(apiUsername) && StringUtils.isNotBlank(apiPassword)){
			restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(apiUsername,apiPassword));
		}
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl,String.class,paramStr);
		return responseEntity.getBody();
	}
	/**
	 * POST请求
	 *
	 * @param apiUrl
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static String sendPost(String apiUrl, String paramStr) {
		return sendPostWithHeader(apiUrl,paramStr,null);
	}

	/**
	 * POST请求
	 *
	 * @param apiUrl
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static String sendPostAndToken(String apiUrl, String paramStr, String token) {
		return sendPostWithHeaderAndToken(apiUrl,paramStr,null,token);
	}


	/**
	 * POST请求,需要设置header的时候调用
	 *
	 * @param apiUrl
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static String sendPostWithHeaderAndToken(String apiUrl, String paramStr, Map<String,Object> headerMap,String token) {
		HttpHeaders headers = new HttpHeaders();
		if(headers == null || headers.isEmpty()){
			Iterator<Map.Entry<String,Object>> it = headerMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String,Object> entry =  it.next();
				headers.add(entry.getKey(), entry.getValue().toString());
			}
		}else{
			headers.add("content-type","application/json;charset=UTF-8");
		}
		headers.add("Authorization",token);
		RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
		setUtf8(restTemplate);
		HttpEntity<String> httpEntity = new HttpEntity<String>(paramStr,headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST,httpEntity,String.class);
		return responseEntity.getBody();
	}

	/**
	 * POST请求,需要设置header的时候调用
	 *
	 * @param apiUrl
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static String sendPostWithHeader(String apiUrl, String paramStr, Map<String,Object> headerMap) {
		HttpHeaders headers = new HttpHeaders();
		if(headers == null || headers.isEmpty()){
			Iterator<Map.Entry<String,Object>> it = headerMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String,Object> entry =  it.next();
				headers.add(entry.getKey(), entry.getValue().toString());
			}
		}else{
			headers.add("content-type","application/json;charset=UTF-8");
		}
		RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
		setUtf8(restTemplate);
		HttpEntity<String> httpEntity = new HttpEntity<String>(paramStr,headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST,httpEntity,String.class);
		return responseEntity.getBody();
	}

	/**
	 * POST请求
	 *
	 * @param apiUrl
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static JSONObject sendPostWithToken(String apiUrl, String paramStr, String token) {
		return sendPostWithToken(apiUrl,paramStr,null,token);
	}


	/**
	 * POST请求,需要设置header的时候调用
	 *
	 * @param apiUrl
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static JSONObject sendPostWithToken(String apiUrl, String paramStr, Map<String,Object> headerMap, String token) {
		HttpHeaders headers = new HttpHeaders();
		if(headers == null || headers.isEmpty()){
			Iterator<Map.Entry<String,Object>> it = headerMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String,Object> entry =  it.next();
				headers.add(entry.getKey(), entry.getValue().toString());
			}
		}else{
			headers.add("content-type","application/json;charset=UTF-8");
		}
		headers.add("token",token);
		RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
		setUtf8(restTemplate);
		HttpEntity<String> httpEntity = new HttpEntity<String>(paramStr,headers);
		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST,httpEntity, JSONObject.class);
		return responseEntity.getBody();
	}
	/**
	 * POST请求,需要设置header的时候调用
	 *
	 * @param apiUrl
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static JSONObject sendPostGetObject(String apiUrl, String paramStr, Map<String,Object> headerMap) {
		HttpHeaders headers = new HttpHeaders();
		if(headers == null || headers.isEmpty()){
			Iterator<Map.Entry<String,Object>> it = headerMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String,Object> entry =  it.next();
				headers.add(entry.getKey(), entry.getValue().toString());
			}
		}else{
			headers.add("content-type","application/json;charset=UTF-8");
		}
		RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
		setUtf8(restTemplate);
		HttpEntity<String> httpEntity = new HttpEntity<String>(paramStr,headers);
		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST,httpEntity, JSONObject.class);
		return responseEntity.getBody();
	}


	/**
	 * POST请求,ERP中台 工号认证
	 *
	 * @param apiUrl
	 * @param paramStr
	 * @return
	 * @throws Exception
	 */
	public static String sendPostWithCode(String apiUrl, String paramStr, Map<String,Object> headerMap, String code) {
		HttpHeaders headers = new HttpHeaders();
		if(headers == null || headers.isEmpty()){
			Iterator<Map.Entry<String,Object>> it = headerMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String,Object> entry =  it.next();
				headers.add(entry.getKey(), entry.getValue().toString());
			}
		}else{
			headers.add("content-type","application/json;charset=UTF-8");
		}
		headers.add("x-pagoda-user-id",code);
		RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
		setUtf8(restTemplate);
		HttpEntity<String> httpEntity = new HttpEntity<String>(paramStr,headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST,httpEntity,String.class);
		return responseEntity.getBody();
	}

}
