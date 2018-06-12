package me.webapp.dao;


import me.webapp.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Repository
public interface AccountDao {

    List<Account> queryAll();

    Account query(int id);

    int queryRows();

    void add(Account account);

    void delete(Account account);

    void update(Account account);

    Account queryByEmail(String email);

}
