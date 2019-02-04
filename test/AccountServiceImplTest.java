package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningBalanceEXception;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repository.Account_Repo;
import com.capgemini.service.Account_Service;
import com.capgemini.service.Account_Service_Impl;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {

	@Mock
	Account_Repo account_repo;
		
	Account_Service account_service;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
			account_service = new Account_Service_Impl(account_repo);
	
	}
	
	// create amount test cases

	 @Test(expected = com.capgemini.exceptions.InsufficientOpeningBalanceEXception.class)
	public void whentheamountisleassthan500() throws InsufficientOpeningBalanceEXception {
	
		account_service.create_account(101, 100);
	} 


	@Test
	public void whenthevalidinfoispassed() throws InsufficientOpeningBalanceEXception{
		
		Account account = new Account();
		account.setAccount_num(101);
		account.setAmount(5000);
		when(account_repo.save(account)).thenReturn(true);
		assertEquals(account,account_service.create_account(101, 5000));
	}
	
	// deposit amount test cases
	
	@Test(expected = com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whentheaccountnoisnotvalidfordepositamountthenthrowexception() throws InvalidAccountNumberException{
		
		account_service.deposit_amount(101, 3000);		
		
	}
	
	@Test
	public void whentheamountissuccessfullydeposited() throws InvalidAccountNumberException {
		

		Account account = new Account();
		account.setAccount_num(101);
		account.setAmount(5000);
		when(account_repo.search_Account(101)).thenReturn(account);
		assertEquals(account.getAmount() + 1000,account_service.deposit_amount(101, 1000));
	}

	// withdraw amount test cases

	@Test(expected = com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whentheaccountnoisnotvalidforwithdrawamountthenthrowexception() throws InvalidAccountNumberException,InsufficientBalanceException{
		
		account_service.withdraw_amount(101, 3000);		
		
	}
	
	@Test(expected = com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whentheamountisnotsufficientsystemshouldthrowexception() throws InvalidAccountNumberException,InsufficientBalanceException{
		
		Account account = new Account();
		account.setAccount_num(101);
		account.setAmount(5000);
		when(account_repo.search_Account(101)).thenReturn(account);
		account_service.withdraw_amount(101,6000);	
		
	}
	
	@Test
	public void whentheamountissuccessfullywithdrawn() throws InvalidAccountNumberException,InsufficientBalanceException {
		
		Account account = new Account();
		account.setAccount_num(101);
		account.setAmount(5000);
		when(account_repo.search_Account(101)).thenReturn(account);
		assertEquals(account.getAmount() - 1000,account_service.withdraw_amount(101, 1000));
		
	}	
}
	
	