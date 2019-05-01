package com.microservices.transactions.controller;

import com.microservices.transactions.model.Statistics;
import com.microservices.transactions.service.TransactionService;
import io.micrometer.core.instrument.Statistic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Statistics", description = "Controller de estatisticas")
@RequestMapping("/statistics")
public class StatisticController {

	private TransactionService transactionService;

    @Autowired
    public StatisticController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    
	@ApiOperation(httpMethod = "GET", value = "Metodo que faz os calcalos das ultimas transacoes nos 60 segundos")
    @ApiResponses(value = {
        @ApiResponse(
            code = 200,  message = "Success",  response = Statistic.class
        )
    })
	@GetMapping("/")
 	public ResponseEntity<Statistics> getStatistics(){
		
		Statistics statistics = transactionService.calculateStatistics();
	
		return ResponseEntity.status(HttpStatus.OK).body(statistics);
	
	}

}
