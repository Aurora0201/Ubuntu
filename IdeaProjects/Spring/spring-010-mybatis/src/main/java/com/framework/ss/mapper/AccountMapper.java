package com.framework.ss.mapper;

import com.framework.ss.pojo.Account;

import java.util.List;

public interface AccountMapper {
    /**
     * insert an item into DB
     * @param account the item should be inserted
     * @return the amount of item
     */
    int insert(Account account);

    /**
     * delete item by actno
     * @param actno the actno should be deleted
     * @return the amount of delete item
     */
    int deleteByActno(String actno);

    /**
     * update item
     * @param account the update data
     * @return amount
     */
    int update(Account account);

    /**
     * select item by actno
     * @param actno actno
     * @return item
     */
    Account selectByActno(String actno);

    /**
     * select all items
     * @return list of items
     */
    List<Account> selectAll();
}
