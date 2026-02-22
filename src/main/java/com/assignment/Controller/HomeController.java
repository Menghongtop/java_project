package com.assignment.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class HomeController {


    @GetMapping("/dashboard")
    public String generalDashboard() {
        return "dashboard";  // generic dashboard.html (can redirect or show message)
    }


    @GetMapping("/recruiter/dashboard")
    @PreAuthorize("hasRole('RECRUITER')")
    public String recruiterDashboard(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("userEmail", principal.getName());
            // TODO: Later add real stats
            // model.addAttribute("postedJobsCount", jobPostService.countByPostedBy(principal.getName()));
        }
        return "recruiter/dashboard";
    }


    @GetMapping("/jobseeker/dashboard")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public String jobseekerDashboard(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("userEmail", principal.getName());
            // TODO: Later add real stats
            // model.addAttribute("appliedJobsCount", applyService.countByUser(principal.getName()));
        }
        return "jobseeker/dashboard";
    }

}