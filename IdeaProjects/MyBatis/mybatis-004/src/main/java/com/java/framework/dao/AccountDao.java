package com.java.framework.dao;

import com.java.framework.bean.Account;

/**
 * Account database access object
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
public interface AccountDao {
    /**
     *
     * @param actno the select account num
     * @return Account
     */
    Account selectByActno(String actno);

    /**
     * update DB by Actno
     * @param account update account
     */
    int updateByActno(Account account);
}
