package com.king.Bigshow.Interfaces;

import com.king.Bigshow.model.AccountBean;

public interface AccountInterface {
	public void insert(AccountBean account);
    public void update(AccountBean account);
    public void delete(AccountBean account);
    public AccountBean findByAccountNo(String User_Name);
}
