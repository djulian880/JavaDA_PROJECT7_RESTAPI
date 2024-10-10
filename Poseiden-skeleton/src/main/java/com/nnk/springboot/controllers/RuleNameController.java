package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import com.nnk.springboot.service.TradeService;
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
public class RuleNameController {
    // TODO: Inject RuleName service
    @Autowired
    RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model, HttpServletRequest request)
    {
        // TODO: find all RuleName, add to model
        model.addAttribute("remoteUser", request.getRemoteUser());
        model.addAttribute("ruleNames", ruleNameService.getAll());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        if (!result.hasErrors()) {
            ruleNameService.saveRuleName(ruleName);
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form


        Optional<RuleName> ruleName = ruleNameService.getRuleNameById(id);
        if (ruleName.isPresent()) {
            RuleName ruleNameFound = ruleName.get();
            model.addAttribute("ruleName", ruleNameFound);
            return "ruleName/update";
        } else {
            log.error("CurvePoint with id {} not found", id);
            return "redirect:/error";
        }
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        if (!result.hasErrors()) {
            ruleNameService.saveRuleName(ruleName);
            return "redirect:/ruleName/list";
        }
        return "/ruleName/update{"+id+"}";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        ruleNameService.deleteRuleNameById(id);
        return "redirect:/ruleName/list";
    }
}
