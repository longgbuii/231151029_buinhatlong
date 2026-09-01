package vn.edu.crs.authservice;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenTest {
    @Test
    public void generateHashes() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("HASH FOR admin123: " + encoder.encode("admin123"));
        System.out.println("HASH FOR student123: " + encoder.encode("student123"));
    }
}
