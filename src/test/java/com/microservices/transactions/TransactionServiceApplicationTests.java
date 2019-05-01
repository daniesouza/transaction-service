package com.microservices.transactions;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.microservices.transactions.model.Transactions;
import com.microservices.transactions.service.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class )
@SpringBootTest(classes = TransactionServiceApplication.class)
public class TransactionServiceApplicationTests {

	/** The web application context. */
	@Autowired
	private WebApplicationContext webApplicationContext;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

    @Mock
    private TransactionService transactionService;

    @Before
    public void setUpMock() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(transactionService.save(any(Transactions.class))).thenReturn(new Transactions());
    }

    
	@Test
	public void okTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/statistics")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void notfoundTest() throws Exception {
		mockMvc.perform(get("/statistics/1")).andExpect(status().isNotFound());
	}
 	
    @Test
    public void salvarTransacaoTest() {
        Assert.assertNotNull(transactionService.save(new Transactions()));
    }
    

}
