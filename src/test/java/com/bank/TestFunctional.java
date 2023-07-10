package com.bank;

import org.junit.Assert;
import org.junit.Test;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.AccountRepositoryImp;
import com.bank.service.AccountService;
import com.bank.service.AccountServiceImp;

public class TestFunctional {
	
	private final Map<String, Account> dummyAccountList = new HashMap<> ();
	private final AccountRepository accountRepository = new AccountRepositoryImp(dummyAccountList);
	private final AccountService accountService = new AccountServiceImp(accountRepository);

	 @Test
	    public void testAccountRepository() {
	    	String firstName = "firstname";
	    	String lastName =  "lastname";
	    	float changedBalance = 3;
	    	
	    	String acountId = accountRepository.create(firstName, lastName);
	    
	    	Account account = accountRepository.getById(acountId);
	    	
	    	Assert.assertEquals(account.getFirstName(),firstName);
	    	Assert.assertEquals(account.getLastName(),lastName);
	    	Assert.assertEquals(account.getBalance(),0,0);
	    	Assert.assertEquals(account.getTransactionList().size(),0,0);
	    	
	    	account.setBalance(changedBalance);
	    	accountRepository.save(account);
	    	
	    	Assert.assertEquals(accountRepository.getById(acountId).getBalance(),changedBalance,0);
	    	
	    }
	 
	 
	 @Test
	    public void testAccountService() {
		 
		 	String accountId = accountService.createAccount("firstname", "lastname");
	        accountService.deposit(accountId, 20);

	        accountService.withdraw(accountId, 5);

	        // check account balance
	        Assert.assertEquals(15, accountService.getAccount(accountId).getBalance(), 0);

	        // check transactions
	        List<Transaction> transactionList = accountService.getTransactions(accountId);
	        Assert.assertEquals(transactionList.get(0).getInitialBalance(), 0, 0);
	        Assert.assertEquals(transactionList.get(0).getAmount(), 20, 0);
	        Assert.assertEquals(transactionList.get(0).getType(), Transaction.TransactionType.DEPOSIT);


	        Assert.assertEquals(transactionList.get(1).getInitialBalance(), 20, 0);
	        Assert.assertEquals(transactionList.get(1).getAmount(), 5, 0);
	        Assert.assertEquals(transactionList.get(1).getType(), Transaction.TransactionType.WITHDRAWAL);
	 }
	 
	
	
}
