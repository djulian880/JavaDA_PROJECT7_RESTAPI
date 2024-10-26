package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {
    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> getAll() {
        return ruleNameRepository.findAll();
    }

    public Optional<RuleName> getRuleNameById(Integer Id) {
        return ruleNameRepository.findById(Id);
    }

    public RuleName saveRuleName(RuleName rulename) {
        return ruleNameRepository.save(rulename);
    }

    public void deleteRuleNameById(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
