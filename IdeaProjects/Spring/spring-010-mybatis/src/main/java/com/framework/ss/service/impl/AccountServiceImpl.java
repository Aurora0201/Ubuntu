package com.framework.ss.service.impl;

import com.framework.ss.mapper.AccountMapper;
import com.framework.ss.pojo.Account;
import com.framework.ss.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public int save(Account account) {
        return accountMapper.insert(account);
    }

    @Override
    public int removeByActno(String actno) {
        return accountMapper.deleteByActno(actno);
    }

    @Override
    public int modifyByActno(Account account) {
        return accountMapper.update(account);
    }

    @Override
    public Account getByActno(String actno) {
        return accountMapper.selectByActno(actno);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountMapper.selectAll();
    }

    @Override
    public void transfer(String fromActno, String toActno, double balance) {
        Account fromAccount = accountMapper.selectByActno(fromActno);
        Account toAccount = accountMapper.selectByActno(toActno);
        if (fromAccount.getBalance() < balance) {
            throw new RuntimeException("Insufficient Balance");
        }
        toAccount.setBalance(toAccount.getBalance() + balance);
        fromAccount.setBalance(fromAccount.getBalance() - balance);
        int count = accountMapper.update(toAccount);

        count += accountMapper.update(fromAccount);

        if (count != 2) {
            throw new RuntimeException("Transfer Failed");
        }
    }
}
