package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {
    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> getAll() {
        return bidListRepository.findAll();
    }

    public Optional<BidList> getBidListById(Integer Id) {
        return bidListRepository.findById(Id);
    }

    public BidList saveBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public void deleteBidListById(Integer id) {
        bidListRepository.deleteById(id);
    }
}
