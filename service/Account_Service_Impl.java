package com.capgemini.service;
import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningBalanceEXception;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repository.Account_Repo;

public class Account_Service_Impl implements Account_Service {
	
	
	Account_Repo account_repo;
	
	public Account_Service_Impl(Account_Repo account_repo){
		
		super();
		this.account_repo = account_repo;
		
	}
	
	
	public Account create_account(int account_num,int amount) throws InsufficientOpeningBalanceEXception{
		
		if (amount < 500){
			
			throw new InsufficientOpeningBalanceEXception();
		}
			
			Account account= new Account();
			account.setAccount_num(account_num);
			account.setAmount(amount);
		
			if(account_repo.save(account)){
				
					return account;
				}
			
			return null;
		}
	
public int deposit_amount(int account_num,int amount) throws InvalidAccountNumberException{
		
		Account account = account_repo.search_Account(account_num);	
		if(account == null){
			System.out.println("From if statement");
			throw new InvalidAccountNumberException();
		}
		
		account.setAmount(account.getAmount() + amount);
		account_repo.save(account);
		System.out.println("after save");
		return account.getAmount();
	}

public int withdraw_amount(int account_num,int amount) throws InvalidAccountNumberException,InsufficientBalanceException { 
	
	Account account = account_repo.search_Account(account_num);
	
	if(account == null){
		throw new InvalidAccountNumberException();
		}
	
	if(account.getAmount() < amount){
		
			throw new InsufficientBalanceException();
		}
	account.setAmount(account.getAmount() - amount);
	account_repo.save(account);
	return account.getAmount();
	
	}
}
