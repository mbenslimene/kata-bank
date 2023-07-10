package com.bank.service;

import com.bank.exception.ServiceException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


import java.util.List;


public class AccountServiceTest {

	private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final AccountService accountService = new AccountServiceImp(accountRepository);
    
    
    @Test
    public void testCreateAccount() {
    	String firstName = "firstname";
    	String lastName =  "lastname";
    	String expectedAccountId = "20";
    	when(accountRepository.create(firstName, lastName)).thenReturn("20");
    	
    	String accountId = accountService.createAccount(firstName, lastName);
    	Assert.assertEquals(accountId,expectedAccountId);
    	
    }
    
    @Test
    public void testGetAccount() {
    	String accountId ="20";
    	Account expectedAccount = new Account("firstname", "lastname");
    	expectedAccount.setId(accountId);
    	when(accountRepository.getById(accountId)).thenReturn(expectedAccount);
    	
    	Account account = accountService.getAccount(accountId);
    	
    	Assert.assertEquals(account,expectedAccount);
    	
    }

    @Test
    public void testDeposit() {
    	String accountId ="20-111-3333";
    	int initialBlance = 5;
    	int depositAmount = 10;
    	int expectedBalanceAfterDeposit = depositAmount + initialBlance;
    	
    	Account expectedAccount = new Account("firstname", "lastname");
    	expectedAccount.setId(accountId);
    	expectedAccount.setBalance(initialBlance);
    	
    	int numberTransactionBeforeDeposit = expectedAccount.getTransactionList().size();
    	int numberTransactionAfterDeposit = numberTransactionBeforeDeposit +1;
    	
        
        when(accountRepository.getById(accountId)).thenReturn(expectedAccount);
        
        accountService.deposit(accountId, depositAmount);

        //check balance after deposit
        Assert.assertEquals(expectedAccount.getBalance(), expectedBalanceAfterDeposit, 0);
        
        
        //check deposit transaction
        Assert.assertEquals(expectedAccount.getTransactionList().size(), numberTransactionAfterDeposit, 0);
        
        Transaction depositTransaction = expectedAccount.getTransactionList().get(numberTransactionAfterDeposit - 1);
        
        Assert.assertEquals(depositTransaction.getType(), Transaction.TransactionType.DEPOSIT);
        Assert.assertEquals(depositTransaction.getAmount(), depositAmount, 0);
        Assert.assertEquals(depositTransaction.getInitialBalance(), initialBlance, 0);
        
        
       
        verify(accountRepository).save(any());
    }
    
    @Test
    public void testWithdrawal() {
    	String accountId ="20-111-3333";
    	int initialBlance = 10;
    	int withdrawaAmount = 5;
    	int expectedBalanceAfterWithdraw = initialBlance - withdrawaAmount;
    	
    	Account expectedAccount = new Account("firstname", "lastname");
    	expectedAccount.setId(accountId);
    	expectedAccount.setBalance(initialBlance);
    	
    	int numberTransactionBeforeWithdrawal = expectedAccount.getTransactionList().size();
    	int numberTransactionAfterWithdrawal = numberTransactionBeforeWithdrawal +1;
    	
        
        when(accountRepository.getById(accountId)).thenReturn(expectedAccount);
        
        accountService.withdraw(accountId, withdrawaAmount);

        //check balance after withdraw
        Assert.assertEquals(expectedAccount.getBalance(), expectedBalanceAfterWithdraw, 0);
        
        
        //check withdraw transaction
        Assert.assertEquals(expectedAccount.getTransactionList().size(), numberTransactionAfterWithdrawal, 0);
        
        Transaction depositTransaction = expectedAccount.getTransactionList().get(numberTransactionAfterWithdrawal - 1);
        
        Assert.assertEquals(depositTransaction.getType(), Transaction.TransactionType.WITHDRAWAL);
        Assert.assertEquals(depositTransaction.getAmount(), withdrawaAmount, 0);
        Assert.assertEquals(depositTransaction.getInitialBalance(), initialBlance, 0);
        
        
       
        verify(accountRepository).save(any());

    }


    /**
     * should throw exception because account balance is set to zero for new account and no deposit is done
     */
    @Test(expected = ServiceException.class)
    public void testUnauthorisedWithdrawal() {
        
        
        String accountId ="20-111-3333";
    	int initialBlance = 3;
    	int withdrawaAmount = 5;
    	
    	Account expectedAccount = new Account("firstname", "lastname");
    	expectedAccount.setId(accountId);
    	expectedAccount.setBalance(initialBlance);
    	
    	
        
        when(accountRepository.getById(accountId)).thenReturn(expectedAccount);
        
        accountService.withdraw(accountId, withdrawaAmount);

    }
    
    
    @Test
    public void testGetTransactions() {
    	String accountId ="20-111-3333";
    	Transaction.TransactionType expectedTransactionType = Transaction.TransactionType.DEPOSIT;
    	float initialBlance = 0;
    	float transactionAmount = 5;
    	Account expectedAccount = new Account("firstname", "lastname");
    	expectedAccount.setId(accountId);
    	expectedAccount.setBalance(initialBlance);
    	
    	expectedAccount.getTransactionList().add(new Transaction(expectedTransactionType, transactionAmount, initialBlance));
    	
    	when(accountRepository.getById(accountId)).thenReturn(expectedAccount);
    	
    	List<Transaction> transactions = accountService.getTransactions(accountId);
    	
    	assertEquals(transactions.size(), 1);
    	Transaction depositTransaction = transactions.get(0);
    	assertEquals(depositTransaction.getType(), expectedTransactionType);
    	assertEquals(depositTransaction.getAmount(), transactionAmount,0);
    	assertEquals(depositTransaction.getInitialBalance(), initialBlance,0);
    	
    	
    }
    

   
    
   
}
