package com.ztech.controller;

import com.ztech.dto.RewardInfo;
import com.ztech.entity.CustomerTransaction;
import com.ztech.repository.CustomerTransactionRepository;
import com.ztech.service.RewardPointsService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
public class RewardPointsController {

    @Autowired
    private RewardPointsService rewardPointsService;

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    @PostConstruct
    public void populateCustomerTransactions() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.of(2025,2,13);

        List<CustomerTransaction> customerTransactionLits =
                    List.of(new CustomerTransaction(null,"Ravi",120L,
                                    simpleDateFormat.parse(LocalDate.of(2025,2,13).toString())),
                            new CustomerTransaction(null,"Suresh",50L,
                                    simpleDateFormat.parse(LocalDate.of(2025,2,13).toString())),
                            new CustomerTransaction(null,"Ravi",100L,
                                    simpleDateFormat.parse(LocalDate.of(2025,1,13).toString())));

        customerTransactionRepository.saveAll(customerTransactionLits);
    }

    @GetMapping("/rewardPoints/{customerName}")
    public List<RewardInfo> getEmployee(@PathVariable String customerName)
    {
        return rewardPointsService.getCustomerRewardpoints(customerName);
    }

}
