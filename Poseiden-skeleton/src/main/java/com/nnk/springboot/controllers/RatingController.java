package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.service.RatingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.util.Optional;

@Slf4j
@Controller
public class RatingController {
    // TODO: Inject Rating service
    @Autowired
    RatingService ratingService;


    @RequestMapping("/rating/list")
    public String home(Model model, HttpServletRequest request)
    {
        // TODO: find all Rating, add to model
        model.addAttribute("remoteUser", request.getRemoteUser());
        model.addAttribute("ratings", ratingService.getAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        if (!result.hasErrors()) {
            ratingService.saveRating(rating);
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form


        Optional<Rating> rating = ratingService.getRatingById(id);
        if (rating.isPresent()) {
            Rating ratingFound = rating.get();
            model.addAttribute("rating", ratingFound);
            return "rating/update";
        } else {
            log.error("Rating with id {} not found", id);
            return "redirect:/error";
        }
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        if (!result.hasErrors()) {
            ratingService.saveRating(rating);
            return "redirect:/rating/list";
        }
        return "/rating/update{"+id+"}";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        ratingService.deleteRatingById(id);
        return "redirect:/rating/list";
    }
}
