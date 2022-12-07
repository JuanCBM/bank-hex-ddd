package com.juablaz.bankhexddd.accounting.application.port;

import org.springframework.web.bind.annotation.RequestParam;

public interface ForCreatingAccountsOperation {

  String create(String name, @RequestParam String currency);

}
