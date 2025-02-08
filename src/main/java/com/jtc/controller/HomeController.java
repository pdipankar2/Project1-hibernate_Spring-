package com.jtc.controller;

import java.util.List;

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
	
	
	
	//UPDATE students SET isAdmin = true WHERE email = 'Pdipankar28@gmail.com';


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
    public String Addstudent(@ModelAttribute Student student, Model model) {
        try {
            System.out.println(student);

            // Generate and set student ID
            String studentId = studentDao.generateStudentId();
           
            student.setStudentId(studentId);

            int saveStudent = studentDao.saveStudent(student);

            model.addAttribute("success", "Registration successful. Your Student ID is: " + studentId);
            return "register";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred during registration: " + e.getMessage());
            return "register";
        }
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

	/*
	 * @PostMapping("verifyOtp") public String verifyOtp(@RequestParam String
	 * email, @RequestParam String otp, Model model, HttpSession session) {
	 * System.out.println("Verifying OTP for email: " + email + " with OTP: " +
	 * otp);
	 * 
	 * String message = otpService.verifyOtp(email, otp); if
	 * (message.equals("Login successful!")) { Student student =
	 * studentDao.getStudentByEmail(email); session.setAttribute("student",
	 * student); // Store student in session return "dashboard"; // Return dashboard
	 * view } else { model.addAttribute("error", message); return "verifyOtp"; //
	 * Return to verify OTP on error } }
	 */
	
	@PostMapping("verifyOtp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp, Model model, HttpSession session) {
        System.out.println("Verifying OTP for email: " + email + " with OTP: " + otp);

        String message = otpService.verifyOtp(email, otp);
        if (message.equals("Login successful!")) {
            Student student = studentDao.getStudentByEmail(email);
            session.setAttribute("student", student);
            if (student.isAdmin()) {
                return "redirect:adminPanel";
            } else {
                return "dashboard";
            }
        } else {
            model.addAttribute("error", message);
            return "verifyOtp";
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
	
	//admin 
	
	@GetMapping("adminPanel")
    public String adminPanel(Model model, HttpSession session) {
        Student admin = (Student) session.getAttribute("student");
        if (admin != null && admin.isAdmin()) {
            List<Student> students = studentDao.getAllStudents();
            model.addAttribute("students", students);
            return "adminPanel";
        } else {
            return "redirect:login";
        }
    }
	
	
	@PostMapping("updateStudent")
    public String updateStudent(@ModelAttribute Student student, HttpSession session) {
        Student admin = (Student) session.getAttribute("student");
        if (admin != null && admin.isAdmin()) {
            studentDao.updateStudent(student);
            return "redirect:adminPanel";
        } else {
            return "redirect:login";
        }
    }
	
	@PostMapping("deleteStudent")
    public String deleteStudent(@RequestParam int id, HttpSession session) {
        Student admin = (Student) session.getAttribute("student");
        if (admin != null && admin.isAdmin()) {
            studentDao.deleteStudent(id);
            return "redirect:adminPanel";
        } else {
            return "redirect:login";
        }
    }
	
	
	
	@PostMapping("/setAdminAccess")
    public String setAdminAccess(@RequestParam String email, @RequestParam boolean isAdmin, HttpSession session, Model model) {
        Student currentUser = (Student) session.getAttribute("student");
        if (currentUser != null && currentUser.isAdmin()) {
            studentDao.setAdminAccess(email, isAdmin);
            model.addAttribute("message", "Admin access updated successfully");
        } else {
            model.addAttribute("error", "You don't have permission to perform this action");
        }
        return "redirect:adminPanel";
    }
	
	
}
