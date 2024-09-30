package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
public class BidListController {
    //private static Logger logger = LoggerFactory.getLogger(BidListController.class);
    // TODO: Inject Bid service
    @Autowired
    BidListService bidListService;
    @RequestMapping("/bidList/list")
    public String home(Model model, HttpServletRequest request)
    {
        // TODO: call service find all bids to show to the view
        model.addAttribute("remoteUser", request.getRemoteUser());
        model.addAttribute("bidLists", bidListService.getAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (!result.hasErrors()) {
            bidListService.saveBidList(bid);
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        Optional<BidList> bidList = bidListService.getBidListById(id);
        if (bidList.isPresent()) {
            BidList bidListFound = bidList.get();
            model.addAttribute("bidList", bidListFound);
            return "bidList/update";
        } else {
            log.error("BidList with id {} not found", id);
            return "redirect:/error";
        }
       // return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {

        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (!result.hasErrors()) {
            bidListService.saveBidList(bidList);
            return "redirect:/bidList/list";
        }
        return "/bidList/update/{"+id+"}";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        bidListService.deleteBidListById(id);
        return "redirect:/bidList/list";
    }
}
