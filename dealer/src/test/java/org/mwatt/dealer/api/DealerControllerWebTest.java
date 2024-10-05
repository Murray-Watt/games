package org.mwatt.dealer.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Base64;
import java.util.Base64.Encoder;

@WebMvcTest(DealerController.class)
public class DealerControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Test
    public void createDeck_Success()  throws Exception {
        Encoder encoder = Base64.getEncoder();
        String authHeaderValue = "Basic " + encoder.encodeToString((username + ":" + password).getBytes());

        mockMvc.perform(MockMvcRequestBuilders.post("/deck")
                        .header("Authorization", authHeaderValue)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
