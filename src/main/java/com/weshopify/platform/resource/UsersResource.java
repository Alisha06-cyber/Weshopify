package com.weshopify.platform.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weshopify.platform.beans.RoleBean;
import com.weshopify.platform.beans.WeshopifyPlatformUserBean;
import com.weshopify.platform.service.RoleMgmtService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Lenovo
 * @since
 * {@summary}:create and manage the user resources
 */
@RestController
@Slf4j
public class UsersResource {
	@Autowired
	private RoleMgmtService roleMgmtService;
 @PostMapping(value="/users")
  public ResponseEntity<WeshopifyPlatformUserBean> craeteUser(@RequestBody WeshopifyPlatformUserBean userBean)
  { 
	  log.info("weshopify Users Data" +userBean.toString());
	  return null;
  }
 @GetMapping(value="/users")
  public ResponseEntity<List<WeshopifyPlatformUserBean>> findAllUsers(){
	  return null;
  }
 @PutMapping(value="users")
 public ResponseEntity<WeshopifyPlatformUserBean> updateUser(@RequestBody WeshopifyPlatformUserBean userBean){
 log.info("weshopify Users Data" +userBean.toString());
 return null;
}
 @GetMapping(value="/users/{userId}")
 public ResponseEntity<WeshopifyPlatformUserBean> findUserById(@PathVariable("userId") String userId){
	 
	 return null;
 }
 @DeleteMapping(value="/users/{userId}")
 public ResponseEntity<WeshopifyPlatformUserBean> deleteUserById(@PathVariable("userId") String userId){
	 
	 return null;
 }
 @GetMapping(value="/users/roles")
 public ResponseEntity<List<RoleBean>> findAllRoles(){
	 List<RoleBean> rolesList=roleMgmtService.getAllRoles();
	ResponseEntity<List<RoleBean>> rolesResponse=null;
	if(null!=rolesList && rolesList.size()>0) {
		rolesResponse=ResponseEntity.ok().body(rolesList);
	}
	else {
		rolesResponse=ResponseEntity.noContent().build();
	}
	return rolesResponse;
	
 }
}

