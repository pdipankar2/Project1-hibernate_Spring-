package com.jtc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtc.dao.StudentDao;
import com.jtc.entity.Student;

@Service
public class OTPService {
	
	 @Autowired
	    private StudentDao studentDao;

	    private final Random random = new Random();
	    private final Map<String, String> otpMap = new HashMap<>(); // Temporary storage for OTPs

	    public String sendOtp(String email) {
	        // Check if the student exists
	        Student student = studentDao.getStudentByEmail(email);
	        if (student != null) {
	            String otp = String.format("%06d", random.nextInt(1000000)); // Generate a 6-digit OTP
	            String subject = "Your OTP Code";
	            String body = "Your OTP code is: <strong>" + otp + "</strong>";

	            EmailUtil.sendEmail(email, subject, body); // Send the email
	            otpMap.put(email, otp); // Store the OTP
	            return "OTP sent to your email.";
	        } else {
	            return "Email not found.";
	        }
	    }

	    public String verifyOtp(String email, String otp) {
	        String generatedOtp = otpMap.get(email);
	        if (generatedOtp != null && generatedOtp.equals(otp)) {
	            otpMap.remove(email); // Clear OTP after verification
	            return "Login successful!";
	        } else {
	            return "Invalid OTP. Please try again.";
	        }
	    }

}
