package com.microservices.transactions.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.microservices.transactions.model.Statistics;
import com.microservices.transactions.model.Transactions;

@Repository
public class TransactionRepository {
	
	private static List<Transactions> transactions = Collections.synchronizedList(new ArrayList<>());

	
    public Transactions save(Transactions transaction){
        this.transactions.add(transaction);
        return transaction;
    }

    public Statistics getStatitics(Statistics statistic){
		return statistic;
   
    }
    
    public List<Transactions> getTransactions() {
    	return transactions;
    }

}
