package com.microservices.transactions.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.transactions.excepetion.TransactionException;
import com.microservices.transactions.model.Statistics;
import com.microservices.transactions.model.Transactions;
import com.microservices.transactions.repository.TransactionRepository;

@Service
public class TransactionService {

	private TransactionRepository transactionRepository;
	private static final int UM_MINUTO = 60000;

	@Autowired
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	public Statistics calculateStatistics() {
		Statistics statc = new Statistics();

		List<Transactions> trans = transactionRepository.getTransactions().stream()
				.filter(t -> System.currentTimeMillis() - t.getTimestamp() <= UM_MINUTO)
				.collect(Collectors.toList());

		if (!trans.isEmpty()) {

			DoubleStream doubleStream = trans.stream().mapToDouble(Transactions::getAmount);

			statc.setAvg(BigDecimal.valueOf(doubleStream.average().getAsDouble())
					.setScale(2, BigDecimal.ROUND_HALF_EVEN));

			statc.setSum(BigDecimal.valueOf(doubleStream.sum())
					.setScale(2, BigDecimal.ROUND_HALF_EVEN));

			statc.setMax(BigDecimal.valueOf(doubleStream.max().getAsDouble())
					.setScale(2, BigDecimal.ROUND_HALF_EVEN));

			statc.setMin(BigDecimal.valueOf(doubleStream.min().getAsDouble())
					.setScale(2, BigDecimal.ROUND_HALF_EVEN));

			statc.setCount(doubleStream.count());
		}

		return transactionRepository.getStatitics(statc);
	}

	public Transactions save(Transactions transaction) {

		if (System.currentTimeMillis() - transaction.getTimestamp() > UM_MINUTO) {
			throw new TransactionException("TimeStamp Invalido");
		}

		return transactionRepository.save(transaction);
	}

}
