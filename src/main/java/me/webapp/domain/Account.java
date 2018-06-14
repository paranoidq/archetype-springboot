package me.webapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
public class Account extends BaseDomain {

    private int id;
    private String username;
    private String email;
    private String password;

    public Account(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
