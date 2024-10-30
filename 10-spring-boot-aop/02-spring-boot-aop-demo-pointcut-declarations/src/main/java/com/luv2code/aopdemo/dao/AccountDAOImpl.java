package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements  AccountDAO {

    private String name;
    private String servieCode;

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        if(tripWire) {
            throw new RuntimeException("no soup for you");
        }
        List<Account> myAccounts = new ArrayList<>();

        // create sample accounts
        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Madhu", "Platinum");
        Account temp3 = new Account("Luca", "Gold");

        // add them to our accounts list
        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);


        return myAccounts;
    }

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": Doing my db work. Adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " in doWork");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + " in getName");

        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " in setName");

        this.name = name;
    }

    public String getServieCode() {
        System.out.println(getClass() + " in getServiceCode");

        return servieCode;
    }

    public void setServieCode(String servieCode) {
        System.out.println(getClass() + " in setServiceCode");

        this.servieCode = servieCode;
    }
}
