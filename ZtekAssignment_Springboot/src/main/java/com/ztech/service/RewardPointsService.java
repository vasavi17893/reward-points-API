package com.ztech.service;

import com.ztech.dto.RewardInfo;
import com.ztech.entity.CustomerTransaction;
import com.ztech.repository.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RewardPointsService {

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    public List<RewardInfo> getCustomerRewardpoints(String customerName) {

        List<RewardInfo> rewardInfoLis = new ArrayList<>();

        List<CustomerTransaction> transactionsByCustomerName = customerTransactionRepository.findByCustomerName(customerName);

        HashMap<String,Integer> rewardsInfo = new HashMap<>();
        for(CustomerTransaction transaction:transactionsByCustomerName)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");

            String month = simpleDateFormat.format(transaction.getTransactionDate());

            if(rewardsInfo.containsKey(month))
            {
                rewardsInfo.put(month,rewardsInfo.get(month)+calculateRewardPoints(transaction.getAmount()));
            }
            else {
                rewardsInfo.put(month,calculateRewardPoints(transaction.getAmount()));
            }

        }

        for(String month : rewardsInfo.keySet())
        {
            RewardInfo rewardInfo = new RewardInfo();

            rewardInfo.setMonth(month);
            rewardInfo.setPoints(rewardsInfo.get(month));

            rewardInfoLis.add(rewardInfo);
        }

        return rewardInfoLis;
    }

    public int calculateRewardPoints(Long amount)
    {
        int points = 0;

        if(amount>100)
        {
            points +=(amount-100)*2;
            amount = 100L;
        }

        if(amount>50)
        {
            points += (amount-50);
        }

        return points;
    }


}
