package com.authentication_web.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authentication_web.Entities.User;
import com.authentication_web.Service.UserDataService;

@Controller
public class UserController {
	

    @Autowired
    private UserDataService userDataService;
	
	@GetMapping("/user_page")
	public String userPage(Model model) {
        model.addAttribute("userDetails", userDataService.getAllUsers());

		return "user_page";
	}
	

    @PostMapping("/block")
    @ResponseBody
    public ResponseEntity<String> blockUser(@RequestBody List<Long> userIds) {
        for (Long userId : userIds) {
            User user = userDataService.getUserByID(userId);
            if (user != null) {
                user.setActive(false);
                userDataService.saveUser(user);
            }
        }
        return ResponseEntity.ok("Users blocked successfully");
    }

    @PostMapping("/unblock")
    @ResponseBody
    public ResponseEntity<String> unblockUser(@RequestBody List<Long> userIds) {
        for (Long userId : userIds) {
            User user = userDataService.getUserByID(userId);
            if (user != null) {
                user.setActive(true);
                userDataService.saveUser(user);
            }
        }
        return ResponseEntity.ok("Users unblocked successfully");
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@RequestBody List<Long> userIds) {
        for (Long userId : userIds) {
            userDataService.deleteUser(userId);
        }
        return ResponseEntity.ok("Users deleted successfully");
    }
}
