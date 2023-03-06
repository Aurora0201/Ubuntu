package com.framework.ss.service;

import com.framework.ss.pojo.Account;

import java.util.List;

public interface AccountService {
    int save(Account account);

    int removeByActno(String actno);

    int modifyByActno(Account account);

    Account getByActno(String actno);

    List<Account> getAllAccount();

    void transfer(String fromActno, String toActno, double balance);
}
