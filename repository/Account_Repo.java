package com.capgemini.repository;
import com.capgemini.beans.*;

public interface Account_Repo {
	
	
	boolean save(Account account);
	Account search_Account(int account_num);
	

}
