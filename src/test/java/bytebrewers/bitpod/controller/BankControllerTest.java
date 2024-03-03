package bytebrewers.bitpod.controller;


import bytebrewers.bitpod.entity.Bank;
import bytebrewers.bitpod.repository.BankRepository;
import bytebrewers.bitpod.service.impl.AuthServiceImpl;
import bytebrewers.bitpod.service.impl.BankServiceImpl;
import bytebrewers.bitpod.utils.dto.ControllerResponse;
import bytebrewers.bitpod.utils.dto.request.bank.BankDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class BankControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BankServiceImpl bankService;
    @MockBean
    private AuthServiceImpl authService;


    private static String id;
    private static String address;
    private static String token;
    private BankRepository bankRepository;

//    @BeforeEach
//    public void setUp() throws Exception {
//        loginAsSuperAdmin();
//    }


    @Order(1)
    @Test
    public void loginAsSuperAdmin() throws Exception {
        Map<String, Object> req = new HashMap<>();
        req.put("email", "superadmin@gmail.com");
        req.put("password", "superadmin");



        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(req))
                ).andExpectAll(status().isAccepted())
                .andDo(result -> {
                    String jsonString = result.getResponse().getContentAsString();
                    Map<String, Object> mapResponse = objectMapper.readValue(jsonString, new TypeReference<>() {
                    });

                    token = mapResponse.get("data").toString();
                    assertNotNull(token);
                });
    }
//
//    @Order(2)
//    @Test
//    public void testCreateBank() throws Exception {
//        // Prepare data
//        BankDTO bankDTO = new BankDTO("Mandiri", "Jl. Jendral Sudirman No. 1");
//        when(service.create(ArgumentMatchers.any(BankDTO.class)))
//                .thenReturn(new Bank("Mandiri", "Jl. Jendral Sudirman No. 1"));
//
//        // perform action create data
//        mockMvc.perform(post("/api/banks")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .header("Authorization", "Bearer " + token)
//                        .content(objectMapper.writeValueAsString(bankDTO))
//                )
//                .andExpectAll(status().isCreated())
//                .andDo(
//                        result -> {
//                            // validate response
//                            String jsonString = result.getResponse().getContentAsString();
//                            ControllerResponse controllerResponse = objectMapper.readValue(jsonString, ControllerResponse.class);
//                            Bank bank = (Bank) controllerResponse.getData();
//
//                            assertEquals(bank.getName(), bankDTO.getName());
//                            assertEquals(bank.getAddress(), bankDTO.getAddress());
//                        });
//    }
//
//    @Order(3)
//    @Test
//    public void givenBankObject_whenCreateBank_thenReturnSavedBank() throws Exception {
//        String id = "1";
//        mockMvc.perform(get("/api/banks/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .header("Authorization", "Bearer " + token)
//                ).andExpectAll(status().isOk())
//                .andDo(result -> {
//                    String jsonString = result.getResponse().getContentAsString();
//                    Map<String, Object> mapResponse = objectMapper.readValue(jsonString, new TypeReference<>() {
//                    });
//                    Map<String, Object> data = (Map<String, Object>) mapResponse.get("data");
//                    assertEquals(id, data.get("id"));
//                });
//    }
//
//    @Order(4)
//    @Test
//    public void givenInvalidBankId_whenGetBank_thenReturnNull() throws Exception {
//        String id = "1";
//        mockMvc.perform(get("/api/banks/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .header("Authorization", "Bearer " + token)
//                ).andExpectAll(status().isBadRequest())
//                .andDo(result -> {
//                    String jsonString = result.getResponse().getContentAsString();
//                    ControllerResponse controllerResponse = objectMapper.readValue(jsonString, ControllerResponse.class);
//                    assertEquals("Bad Request", controllerResponse.getStatus());
//                    assertEquals("Bank not found", controllerResponse.getMessage());
//                    assertEquals(null, controllerResponse.getData());
//                });
//    }
//
//    @Order(5)
//    @Test
//    public void testUpdateBank() throws Exception {
//        String id = "1";
//        Map<String, Object> req = new HashMap<>();
//        req.put("address", "Jl. Jendral Sudirman No. 2");
//        mockMvc.perform(put("/api/banks/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .header("Authorization", "Bearer " + token)
//                        .content(objectMapper.writeValueAsString(req))
//                ).andExpectAll(status().isOk())
//                .andDo(result -> {
//                    String jsonString = result.getResponse().getContentAsString();
//                    Map<String, Object> mapResponse = objectMapper.readValue(jsonString, new TypeReference<>() {
//                    });
//                    Map<String, Object> data = (Map<String, Object>) mapResponse.get("data");
//                    assertEquals(req.get("address"), data.get("address"));
//                });
//    }

}
