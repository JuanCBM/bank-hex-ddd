package com.juablaz.bankhexddd.accounting.application.rest;

import static com.juablaz.bankhexddd.util.Constants.URL_DELIMITER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.juablaz.bankhexddd.accounting.domain.response.FullAccountResponseDto;
import com.juablaz.bankhexddd.accounting.domain.service.AccountService;
import com.juablaz.bankhexddd.utils.AccountTestConstants;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ATMControllerAdapterTest {

  public static final String API_URL_ACCOUNTS_ENDPOINT = "/api/atm/accounts/";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AccountService accountService;

  @Test
  void retrieveAccountTest() throws Exception {

    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);
    Mockito.when(accountService.find(Mockito.anyString())).thenReturn(fullAccountResponseDto);

    mockMvc.perform(MockMvcRequestBuilders.get(API_URL_ACCOUNTS_ENDPOINT + AccountTestConstants.ONE)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath(("$.id"),
                Matchers.equalTo(AccountTestConstants.ONE)));

  }

  @Test
  void depositAmountTest() throws Exception {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);
    Mockito.when(accountService.find(Mockito.anyString())).thenReturn(fullAccountResponseDto);

    mockMvc.perform(
            get(API_URL_ACCOUNTS_ENDPOINT + AccountTestConstants.ONE + URL_DELIMITER + "deposit")
                .param("amount", String.valueOf(AccountTestConstants.TEN))
                .param("currency", AccountTestConstants.EUR))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void withdrawAmountTest() throws Exception {
    FullAccountResponseDto fullAccountResponseDto = new FullAccountResponseDto(
        AccountTestConstants.ONE,
        AccountTestConstants.USERNAME,
        AccountTestConstants.ONE_HUNDRED, AccountTestConstants.EUR);
    Mockito.when(accountService.find(Mockito.anyString())).thenReturn(fullAccountResponseDto);

    mockMvc.perform(
            get(API_URL_ACCOUNTS_ENDPOINT + AccountTestConstants.ONE + URL_DELIMITER + "withdraw")
                .param("amount", String.valueOf(AccountTestConstants.TEN))
                .param("currency", AccountTestConstants.EUR))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
