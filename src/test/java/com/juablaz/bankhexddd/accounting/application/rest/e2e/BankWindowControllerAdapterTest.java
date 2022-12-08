package com.juablaz.bankhexddd.accounting.application.rest.e2e;

import static com.juablaz.bankhexddd._shared.Constants.DEPOSIT_OPERATION;
import static com.juablaz.bankhexddd._shared.Constants.INITIAL_ACCOUNT_VALUE;
import static com.juablaz.bankhexddd._shared.Constants.WITHDRAW_OPERATION;
import static com.juablaz.bankhexddd.accounting.domain.exception.IncompatibleCurrencyException.INCOMPATIBLE_CURRENCY;
import static com.juablaz.bankhexddd.accounting.infraestructure.exception.AccountNotFoundHTTPException.ACCOUNT_NOT_FOUND;
import static com.juablaz.bankhexddd.utils.AccountTestConstants.EUR;
import static com.juablaz.bankhexddd.utils.AccountTestConstants.ONE_HUNDRED;
import static com.juablaz.bankhexddd.utils.AccountTestConstants.TEN;
import static com.juablaz.bankhexddd.utils.AccountTestConstants.USD;
import static com.juablaz.bankhexddd.utils.AccountTestConstants.USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.juablaz.bankhexddd._core.util.Constants;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class BankWindowControllerAdapterTest {

  static final String API_URL_ACCOUNTS_ENDPOINT = "/api/window/accounts/";

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createAndRetrieveAccountTest() throws Exception {
    // Create
    String id = createAccount();

    // Retrieve and validate
    mockMvc.perform(
            MockMvcRequestBuilders.get(API_URL_ACCOUNTS_ENDPOINT + id)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath(("$.id"), Matchers.equalTo(id)))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.currency"),
            Matchers.equalTo(EUR)))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.value"),
            Matchers.equalTo(Double.valueOf(INITIAL_ACCOUNT_VALUE))))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.name"),
            Matchers.equalTo(USERNAME)));
  }

  @Test
  void retrieveNonExistingAccountTest() throws Exception {
    // Retrieve
    mockMvc.perform(
            MockMvcRequestBuilders.get(API_URL_ACCOUNTS_ENDPOINT + "INV_ID")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(
            res -> assertEquals(ACCOUNT_NOT_FOUND, res.getResolvedException().getMessage()));
  }

  @Test
  void depositSameCurrencyAmountTest() throws Exception {

    // Create
    String id = createAccount();

    // Deposit
    executeAccountOperation(id, DEPOSIT_OPERATION, TEN, EUR);

    // Retrieve and validate
    mockMvc.perform(
            MockMvcRequestBuilders.get(API_URL_ACCOUNTS_ENDPOINT + id)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath(("$.id"), Matchers.equalTo(id)))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.currency"),
            Matchers.equalTo(EUR)))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.value"),
            Matchers.equalTo(Double.valueOf(INITIAL_ACCOUNT_VALUE + TEN))))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.name"),
            Matchers.equalTo(USERNAME)));

  }

  @Test
  void depositDifferentCurrencyAmountTest() throws Exception {

    // Create
    String id = createAccount();

    // Deposit
    mockMvc.perform(
            MockMvcRequestBuilders.get(
                    API_URL_ACCOUNTS_ENDPOINT + id + Constants.URL_DELIMITER
                        + DEPOSIT_OPERATION)
                .param("amount", String.valueOf(TEN))
                .param("currency", USD))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(
            res -> assertEquals(INCOMPATIBLE_CURRENCY, res.getResolvedException().getMessage()));

  }


  @Test
  void withdrawSameCurrencyAmountTest() throws Exception {

    // Create
    String id = createAccount();

    // Deposit
    executeAccountOperation(id, DEPOSIT_OPERATION, ONE_HUNDRED, EUR);

    // Withdraw
    executeAccountOperation(id, WITHDRAW_OPERATION, TEN, EUR);

    // Retrieve and validate
    mockMvc.perform(
            MockMvcRequestBuilders.get(API_URL_ACCOUNTS_ENDPOINT + id)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath(("$.id"), Matchers.equalTo(id)))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.currency"),
            Matchers.equalTo(EUR)))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.value"),
            Matchers.equalTo(Double.valueOf(ONE_HUNDRED - TEN))))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.name"),
            Matchers.equalTo(USERNAME)));
  }

  @Test
  void withdrawDifferentCurrencyAmountTest() throws Exception {
    // Create
    String id = createAccount();

    // Deposit
    executeAccountOperation(id, DEPOSIT_OPERATION, ONE_HUNDRED, EUR);

    // Withdraw
    mockMvc.perform(
            MockMvcRequestBuilders.get(
                    API_URL_ACCOUNTS_ENDPOINT + id + Constants.URL_DELIMITER
                        + WITHDRAW_OPERATION)
                .param("amount", String.valueOf(TEN))
                .param("currency", USD))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(
            res -> assertEquals(INCOMPATIBLE_CURRENCY, res.getResolvedException().getMessage()));

  }


  @Test
  void withdrawMoreThanBalanceTest() throws Exception {
    String id = createAccount();

    // Deposit
    executeAccountOperation(id, DEPOSIT_OPERATION, ONE_HUNDRED, EUR);

    // Withdraw
    executeAccountOperation(id, WITHDRAW_OPERATION, TEN + ONE_HUNDRED, EUR);

    // Retrieve and validate
    mockMvc.perform(
            MockMvcRequestBuilders.get(API_URL_ACCOUNTS_ENDPOINT + id)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath(("$.id"), Matchers.equalTo(id)))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.currency"),
            Matchers.equalTo(EUR)))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.value"),
            Matchers.equalTo(Double.valueOf(ONE_HUNDRED - (ONE_HUNDRED + TEN)))))
        .andExpect(MockMvcResultMatchers.jsonPath(("$.name"),
            Matchers.equalTo(USERNAME)));

  }

  private void executeAccountOperation(String id, String operation, float oneHundred,
      String currency) throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get(
                    API_URL_ACCOUNTS_ENDPOINT + id + Constants.URL_DELIMITER
                        + operation)
                .param("amount", String.valueOf(oneHundred))
                .param("currency", currency))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  private String createAccount() throws Exception {
    // Create
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(API_URL_ACCOUNTS_ENDPOINT)
            .param("name", USERNAME)
            .param("currency", EUR))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

    // Get ID
    // String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
    return result.getResponse().getContentAsString();
  }

}
