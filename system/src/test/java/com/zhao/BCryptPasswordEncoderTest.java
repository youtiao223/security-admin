package com.zhao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class BCryptPasswordEncoderTest {

    @Autowired
    private BCryptPasswordEncoder encoder;

    public String encode() {
        String code = encoder.encode("visitor");
        System.out.println(code);
        return code;
    }

    @Test
    public void match() {
        System.out.println(encoder.matches("visitor", encode()));
    }
}
