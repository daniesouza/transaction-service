package com.microservices.transactions.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservices.transactions.model.Statistics;
import com.microservices.transactions.model.Transactions;
import com.microservices.transactions.service.TransactionService;

import io.micrometer.core.instrument.Statistic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Transaction", description = "Controller de transactions")
@RequestMapping("/transactions")
public class TransactionController {
	
	private TransactionService transactionService;
	

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

	
    @ApiOperation(httpMethod = "POST", value = "Salva as transacoes.")
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "Created",  response = Transactions.class),
    		@ApiResponse(code = 204, message = "No content.")
    })
    @PostMapping("/")
    public ResponseEntity save(@RequestBody @Valid Transactions transaction){

    	transactionService.save(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

}
