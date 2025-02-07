package com.jtc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jtc.dao.StudentDao;
import com.jtc.entity.Student;
import com.jtc.util.OTPService;

@Controller
public class HomeController {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private OTPService otpService;

	@RequestMapping(path = "home")
	public String home() {

		return "home";

	}

	@RequestMapping(path = "register")
	public String register() {

		return "register";

	}

	@RequestMapping(path = "registerStudent", method = RequestMethod.POST)
	public String Addstudent(@ModelAttribute Student student) {
		System.out.println(student);

		int saveStudent = studentDao.saveStudent(student);

		return "register";

	}

	@GetMapping("login")
	public String login() {
		return "login"; // Return login view
	}

	@PostMapping("sendOtp")
	public String sendOtp(@RequestParam String email, Model model) {
		String message = otpService.sendOtp(email);
		model.addAttribute("message", message);
		if (message.contains("OTP sent")) {
			model.addAttribute("email", email); // Pass email for OTP verification
			return "verifyOtp"; // Return view to verify OTP
		} else {
			model.addAttribute("error", message);
			return "login"; // Return to login on error
		}
	}

	@PostMapping("verifyOtp")
	public String verifyOtp(@RequestParam String email, @RequestParam String otp, Model model, HttpSession session) {
	    System.out.println("Verifying OTP for email: " + email + " with OTP: " + otp);

		
		String message = otpService.verifyOtp(email, otp);
		if (message.equals("Login successful!")) {
			Student student = studentDao.getStudentByEmail(email);
			session.setAttribute("student", student); // Store student in session
			return "dashboard"; // Return dashboard view
		} else {
			model.addAttribute("error", message);
			return "verifyOtp"; // Return to verify OTP on error
		}
	}

	@GetMapping("dashboard")
	public String dashboard(Model model, HttpSession session) {
		Student student = (Student) session.getAttribute("student");
		if (student != null) {
			model.addAttribute("student", student); // Make sure student is added to the model
			return "dashboard"; // Show dashboard
		} else {
			return "redirect:login"; // Redirect to login if not logged in
		}
	}

	@PostMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate(); // Invalidate the session
		return "redirect:login"; // Redirect to login
	}
}
