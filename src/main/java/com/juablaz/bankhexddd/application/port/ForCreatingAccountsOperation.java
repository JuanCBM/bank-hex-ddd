package com.juablaz.bankhexddd.application.port;

import org.springframework.web.bind.annotation.RequestParam;

public interface ForCreatingAccountsOperation {

  Long create(String name, @RequestParam String currency);

}
