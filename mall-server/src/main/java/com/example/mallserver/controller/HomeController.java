package com.example.mallserver.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/home")
    public String home() {
        logger.info("HomeController's home() method has been accessed.");
        return "home";
    }

    /**
     * Serves the contact page with an optional success message.
     *
     * @param model the model to pass attributes to the view.
     * @param success the optional success message parameter.
     * @return the name of the contact view.
     */
    @GetMapping("/contact")
    public String contact(Model model, @RequestParam(value = "success", required = false) String success) {
        if (success != null) {
            model.addAttribute("successMessage", "Your message has been sent successfully!");
        }
        return "contact";
    }

    /**
     * Redirects an authenticated user to their profile.
     *
     * @param redirectAttributes attributes for redirection.
     * @param user the session user.
     * @return a redirection string to the user profile.
     */
    @GetMapping("/my-profile")
    public String myProfile(RedirectAttributes redirectAttributes, @ModelAttribute("user") String user) {
        if (user != null && !user.isEmpty()) {
            return "redirect:/user/" + user;
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "You must be logged in to view your profile.");
            return "redirect:/login";
        }
    }

    /**
     * Searches for products or content on the site.
     *
     * @param query the search query.
     * @param model the model to pass attributes to the view.
     * @return the name of the search view.
     */
    @GetMapping("/search")
    public String search(@RequestParam("q") String query, Model model) {
        // Logic to perform search using a service
        // The results are added to the model
        model.addAttribute("searchResults", searchService.search(query));
        return "search";
    }
}
