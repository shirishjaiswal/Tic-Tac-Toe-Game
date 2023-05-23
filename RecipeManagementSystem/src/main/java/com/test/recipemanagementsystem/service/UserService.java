package com.test.recipemanagementsystem.service;

import com.test.recipemanagementsystem.dto.SignInRequest;
import com.test.recipemanagementsystem.dto.SignInResponse;
import com.test.recipemanagementsystem.dto.SignUpRequest;
import com.test.recipemanagementsystem.dto.SignUpResponse;
import com.test.recipemanagementsystem.model.AuthenticationToken;
import com.test.recipemanagementsystem.model.User;
import com.test.recipemanagementsystem.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    TokenService tokenService;

    //Encrypting the password with MD5
    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(password.getBytes());
        byte[] digested = md5.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : digested) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    //SignUP
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        //TODO : Check if User already exist in Database
        User user = userRepo.findFirstByEmail(signUpRequest.getEmail());
        //if user exist in database return
        if(user != null) throw new IllegalStateException("User Already Exist Please LogIn");

        //TODO :: If User does not exist
        //Step 1 : Encrypt the Password
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signUpRequest.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        //Step : 2 Saving the Patient in Patient Repo
        User newUser = new User();
        newUser.setName(signUpRequest.getFirstName()+" "+signUpRequest.getLastName());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(encryptedPassword);
        newUser.setRegisteredDate(LocalDate.now());

        userRepo.save(newUser);

        return new SignUpResponse(HttpStatus.CREATED.toString(),
                "User Registered Successfully");
    }

    //SignIn
    public SignInResponse signIn(SignInRequest signInRequest) {
        //TODO : Check if User exist in Database
        User user = userRepo.findFirstByEmail(signInRequest.getEmail());
        //if user exist in database return
        if(user == null) throw new IllegalStateException("User Does Not Exist Please SignUp First");

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signInRequest.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        //Check if Password Matches or Not
        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());
        if(!isPasswordValid) {
            throw new RuntimeException("Invalid Credentials");
        }

        //Create AuthenticationToken and Save it
        AuthenticationToken token = new AuthenticationToken(user);
        tokenService.save(token);

        return new SignInResponse("Authentication Successfully !!!", token.getToken());
    }

    //SignOut
    public ResponseEntity<String> signOut(String email) {
        User isUserValid = userRepo.findFirstByEmail(email);
        if(isUserValid == null)
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);

        if(!isLoggedIn(isUserValid.getId()))
            return new ResponseEntity<>("Please Log In First", HttpStatus.FORBIDDEN);

        Integer noOfRowsAffected = tokenService.removeTokenByPatientId(isUserValid.getId());
        if(noOfRowsAffected != 1) {
            return new ResponseEntity<>("Something went Wrong", HttpStatus.FORBIDDEN);
        }
        else  return new ResponseEntity<>("Logged Out", HttpStatus.OK);
    }

    public boolean isLoggedIn(Integer userId ){
        return tokenService.isPresent(userId);
    }

}
