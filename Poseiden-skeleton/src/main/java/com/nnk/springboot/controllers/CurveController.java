package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {
    // TODO: Inject Curve Point service
    @Autowired
    CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model, HttpServletRequest request)
    {
        // TODO: find all Curve Point, add to model
        model.addAttribute("remoteUser", request.getRemoteUser());
        model.addAttribute("curvePoints", curvePointService.getAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if (!result.hasErrors()) {
            curvePointService.saveCurvePoint(curvePoint);
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        Optional<CurvePoint> curvePoint = curvePointService.getCurvePointById(id);
        if (curvePoint.isPresent()) {
            CurvePoint curvePointFound = curvePoint.get();
            model.addAttribute("curvePoint", curvePointFound);
            return "curvePoint/update";
        } else {
            log.error("CurvePoint with id {} not found", id);
            return "redirect:/error";
        }
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if (!result.hasErrors()) {
            curvePointService.saveCurvePoint(curvePoint);
            return "redirect:/curvePoint/list";
        }
        return "/curvePoint/update{"+id+"}";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        curvePointService.deleteCurvePointById(id);
        return "redirect:/curvePoint/list";
    }
}
