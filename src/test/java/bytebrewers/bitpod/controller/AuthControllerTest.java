package bytebrewers.bitpod.controller;

import bytebrewers.bitpod.service.impl.AuthServiceImpl;
import bytebrewers.bitpod.service.impl.BankServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthServiceImpl authService;

    private String token;

    @Order(1)
    @Test
    public void loginAsSuperAdmin() throws Exception {
        Map<String, Object> req = new HashMap<>();
        req.put("email", "superadmin@gmail.com");
        req.put("password", "superadmin");

        BDDMockito.given(authService.login(req.get("email").toString(), req.get("password").toString()))
                .willAnswer(invocationOnMock -> invocationOnMock.getArguments());

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
}
