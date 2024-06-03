package com.example.resource;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.AdminUpdateDetailsDto;
import com.example.dto.CommonApiResponse;
import com.example.dto.OtpRequest;
import com.example.dto.RequestUserDto;
import com.example.dto.UpdatePasswordDto;
import com.example.dto.UserLoginRequest;
import com.example.dto.UserLoginResponse;
import com.example.dto.UserResponseDto;
import com.example.entity.User;
import com.example.security.JwtUtils;
import com.example.service.OtpService;
import com.example.service.UserService;

@Service
public class UserResource {

    private static final Logger logger = Logger.getLogger(UserResource.class.getName());

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private OtpService otpService;
//registe HR
    public ResponseEntity<CommonApiResponse> registerUser(RequestUserDto request) {
        CommonApiResponse response = new CommonApiResponse();

        if (request == null) {
            response.setMessage("Missing input");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User userEntity = RequestUserDto.toUserEntity(request);
        userEntity.setRole("HR");
        userEntity.setStatus("Active");
        
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedata = service.savedata(userEntity);
        if (savedata == null) {
            response.setMessage("Failed to save data");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.setMessage("Successfully registered");
        response.setStatus(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//login api
    public ResponseEntity<UserLoginResponse> login(UserLoginRequest loginRequest) {
        UserLoginResponse response = new UserLoginResponse();

        if (loginRequest == null) {
            response.setMessage("Missing input");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String jwtToken;
        User user;

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(loginRequest.getRole()));

        try {
            if (loginRequest.getOtp() != null && !loginRequest.getOtp().isEmpty()) {
                if (!otpService.validateOtp(loginRequest.getEmailId(), loginRequest.getOtp())) {
                    response.setMessage("Invalid OTP.");
                    response.setStatus(false);
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmailId(), loginRequest.getPassword(), authorities));
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Authentication failed", ex);
            response.setMessage("Invalid email or password.");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        jwtToken = jwtUtils.generateToken(loginRequest.getEmailId());
        user = service.getbyemail(loginRequest.getEmailId());

        if (jwtToken != null) {
            response.setUser(user);
            response.setMessage("Logged in successfully");
            response.setStatus(true);
            response.setJwtToken(jwtToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Login failed");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
//otp send
    public ResponseEntity<CommonApiResponse> sendOtp(OtpRequest otpRequest) {
        CommonApiResponse response = new CommonApiResponse();

        if (otpRequest == null || otpRequest.getEmail() == null) {
            response.setMessage("Missing email");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
User getbyemail = service.getbyemail(otpRequest.getEmail());
if(getbyemail!=null) {
        try {
            otpService.generateAndSendOtp(otpRequest.getEmail());
            response.setMessage("OTP sent successfully");
            response.setStatus(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to send OTP", e);
            response.setMessage("Failed to send OTP");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }}else
        	 response.setMessage("Give proper email");
response.setStatus(false);
return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<UserResponseDto> fetchAllUsers() {
        UserResponseDto api = new UserResponseDto();

        List<User> findAll = service.findall();
        if (findAll.isEmpty()) {
            api.setMessage("No data found");
            api.setStatus(false);
            return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
        }

        api.setUser(findAll);
        api.setMessage("Data found");
        api.setStatus(true);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }
    
	
    //give special permission
    public ResponseEntity<CommonApiResponse> grantRole(String email) {
        CommonApiResponse response = new CommonApiResponse();
        boolean success = service.grantRole(email);
        if (success) {
            response.setMessage("Role granted successfully");
            response.setStatus(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("User not found or role assignment failed");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
//remove special permission
    public ResponseEntity<CommonApiResponse> revokeRole(String email) {
        CommonApiResponse response = new CommonApiResponse();
        boolean success = service.revokeRole(email);
        if (success) {
            response.setMessage("Role revoked successfully");
            response.setStatus(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("User not found or role revocation failed");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    /////
    //register employee
    public ResponseEntity<CommonApiResponse> registeremployeeUser(RequestUserDto request) {
        CommonApiResponse response = new CommonApiResponse();

        if (request == null) {
            response.setMessage("Missing input");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User userEntity = RequestUserDto.toUserEntity(request);
        userEntity.setRole("Employee");
        userEntity.setStatus("Active");
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedata = service.savedata(userEntity);
        if (savedata == null) {
            response.setMessage("Failed to save data");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.setMessage("Successfully registered");
        response.setStatus(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    //update employee
    
    public ResponseEntity<CommonApiResponse>updateemployee(String name,String mobileno,String email){
    	
    	  CommonApiResponse response = new CommonApiResponse();
    	  User getbyemail = service.getbyemail(email);
    	  
    	  if(getbyemail==null) {
    		  response.setMessage("data not found");
              response.setStatus(false);
              return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	  }
    	  
    	  getbyemail.setName(name);
    	  getbyemail.setMobileno(mobileno);
    	  User savedata = service.savedata(getbyemail);
    	  
    	  if (savedata == null) {
              response.setMessage("Failed to update data");
              response.setStatus(false);
              return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
          }

          response.setMessage("Successfully updated");
          response.setStatus(true);
          return new ResponseEntity<>(response, HttpStatus.OK);
    	
    	
    }
    
    //findby role
  public ResponseEntity<UserResponseDto>findbyrole(String role){
	  UserResponseDto response = new UserResponseDto();
	  
	  List<User> byRole = service.findByRole(role);
	  
	  if(byRole.isEmpty()) {
		  response.setMessage("Failed to find data");
          response.setStatus(false);
          return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	  }
	  response.setUser(byRole);
	  response.setMessage("data found");
      response.setStatus(true);
      return new ResponseEntity<>(response, HttpStatus.OK);
  }
   
  //delete Employee
  public ResponseEntity<CommonApiResponse>deleteemployee(String email){
	   CommonApiResponse response = new CommonApiResponse();
	   User getbyemail = service.getbyemail(email);
 	  
 	  if(getbyemail==null) {
 		  response.setMessage("data not found");
           response.setStatus(false);
           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
 	  }
 	 getbyemail.setStatus("Deactivate");
 	 
 	  User savedata = service.savedata(getbyemail);
 	  
 	  if (savedata == null) {
           response.setMessage("Failed to delete data");
           response.setStatus(false);
           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
       }

       response.setMessage("Successfully deleted");
       response.setStatus(true);
       return new ResponseEntity<>(response, HttpStatus.OK);
	   
	   
  }
  
  //teams based on position
  
  public ResponseEntity<UserResponseDto>findbyposition(String position){
	  UserResponseDto response = new UserResponseDto();
	  
	  List<User> byPosition = service.findByPosition(position);
	  
	  if(byPosition.isEmpty()) {
		  response.setMessage("no data found");
          response.setStatus(false);
          return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	  }
	  
	  response.setUser(byPosition);
	  response.setMessage("data found");
	  response.setStatus(true);
	  return new ResponseEntity<>(response,HttpStatus.OK);
  }
  
	//admin Update Details
  public ResponseEntity<CommonApiResponse>updateAdminDetails(AdminUpdateDetailsDto dto){
	  
		  CommonApiResponse response = new CommonApiResponse();
		 
		  User byEmailAndRole = service.findByEmailAndRole(dto.getEmail(), dto.getRole());
	  
	  if(byEmailAndRole==null) {
	  response.setMessage("your are not authorized to update details");
	  response.setStatus(false); return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	  } 
	  byEmailAndRole.setMobileno(dto.getMobileno());
	  byEmailAndRole.setName(dto.getName());
	  User update = service.savedata(byEmailAndRole);
	  if(update==null) {
		  response.setMessage("update failed");
          response.setStatus(false);
          return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  response.setMessage("data updated success");
      response.setStatus(true);
      return new ResponseEntity<>(response, HttpStatus.OK);
  }
	 
  //password update
  
  public ResponseEntity<CommonApiResponse>updatePassword(UpdatePasswordDto dto){
	  CommonApiResponse response = new CommonApiResponse();
	  
	  if(dto.getEmail()!=null||dto.getOtp()!=null) {
		  if (!otpService.validateOtp(dto.getEmail(), dto.getOtp())) {
              response.setMessage("Invalid OTP.");
              response.setStatus(false);
              return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
          }
		  User getbyemail = service.getbyemail(dto.getEmail());
		  getbyemail.setPassword(passwordEncoder.encode(dto.getNewpassword()));
		  User update = service.savedata(getbyemail);
		  if(update==null) {
			  response.setMessage("failed to update password");
              response.setStatus(false);
              return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		  response.setMessage("Successfully updated new password");
          response.setStatus(true);
          return new ResponseEntity<>(response, HttpStatus.OK);
		  
	  }else
		  response.setMessage("missing input");
      response.setStatus(false);
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
  
}