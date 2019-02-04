package com.capgemini.service;
import com.capgemini.beans.*;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningBalanceEXception;
import com.capgemini.exceptions.InvalidAccountNumberException;

public interface Account_Service {

	
	Account create_account(int account_num,int amount) throws InsufficientOpeningBalanceEXception;
	int deposit_amount(int account_num,int amount) throws InvalidAccountNumberException;
	int withdraw_amount(int account_num,int amount) throws InvalidAccountNumberException,InsufficientBalanceException;

	
}
