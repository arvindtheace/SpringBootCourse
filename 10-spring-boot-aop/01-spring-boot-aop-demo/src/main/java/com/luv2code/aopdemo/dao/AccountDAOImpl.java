package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements  AccountDAO {

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": Doing my db work. Adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " in doWork");
        return false;
    }
}
