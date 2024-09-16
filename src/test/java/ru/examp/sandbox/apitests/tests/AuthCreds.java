package ru.examp.sandbox.apitests.tests;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCreds {
    private String username;
    private String password;
}
