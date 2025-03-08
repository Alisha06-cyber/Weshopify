package com.weshopify.platform.outbound;

import java.net.http.HttpHeaders;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weshopify.platform.beans.RoleBean;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleMgmtClient {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;
//
//	@Autowired
//	private ApplicationsUtil porpsUtil;

	@Value("${iamserver.base-url}")
	private String iam_server_api_base_url;// https://localhost:9443/scim2

	@Value("${iamserver.role-api}")
	private String role_api_context;// Roles

	@Value("${iamserver.user-name}")
	private String iam_user_name;

	@Value("${iamserver.password}")
	private String iam_password;

	// @Value("${iamserver.role-api-schema}")
	// private String roleApiSchema;

	public List<RoleBean> findAllRoles() {
		String role_api_url = iam_server_api_base_url + role_api_context;
		// https://localhost:9443/scim2/Roles
		log.info("constructed role api url is :\t" + role_api_url);
		String adminCreds = iam_user_name + ":" + iam_password;
		log.info("admin creds arre \t" + adminCreds);

		String encodedAdminCreds = Base64.getEncoder().encodeToString(adminCreds.getBytes());
		log.info("admin creds encoded are:\t" + encodedAdminCreds);
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Authorization", "Basic " +encodedAdminCreds);
 
		HttpEntity<String> requestBody = new HttpEntity<>(headers);
		ResponseEntity<Object> apiResponse = restTemplate.exchange(role_api_url, HttpMethod.GET, requestBody,
				Object.class);

		log.info("response code of the api is :\t" + apiResponse.getStatusCode().value());
		if (HttpStatus.OK.value() == apiResponse.getStatusCode().value()) {
			Object  responseBody = apiResponse.getBody().toString();
			try {
				String response=objectMapper.writeValueAsString(responseBody);
				log.info("the response Body is:\t" +responseBody);

			} catch (JsonProcessingException e) {
				log.error(e.getMessage());
			}
			
		}
		return null;
	}
}
