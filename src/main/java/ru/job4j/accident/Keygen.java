package ru.job4j.accident;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Keygen {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd1 = encoder.encode("masterkey");
        String pwd2 = encoder.encode("password");
        System.out.println(pwd1);
        System.out.println(pwd2);
    }
}