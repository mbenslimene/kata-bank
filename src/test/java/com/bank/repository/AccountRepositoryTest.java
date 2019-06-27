package com.bank.repository;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Map;

import com.bank.exception.RepositoryException;
import com.bank.model.Account;

public class AccountRepositoryTest {

	Map<String, Account> mockAccountList = mock(Map.class);
	private AccountRepository accountRepository = new AccountRepositoryImp(mockAccountList);

	@Test
	public void testCreate() {

		String acountId = accountRepository.create("firstname", "lastname");
		assertNotNull(acountId);

	}

	@Test(expected = RepositoryException.class)
	public void testSaveThrowRepositoryException() {
		Account account = new Account("firstname", "lastname");
		account.setId(null);
		accountRepository.save(account);
	}

	public void testGetById() {

		String accountId = "20";

		Account expectedAccount = new Account("firstname", "lastname");
		expectedAccount.setId(accountId);

		when(mockAccountList.get(accountId)).thenReturn(expectedAccount);

		Account account = accountRepository.getById(accountId);

		assertEquals(account, expectedAccount);

	}

}
