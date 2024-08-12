package br.com.braullio.java_aspects_aop.domain;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pessoa {
    private String name;
    private String email;
    private String status;

    public Pessoa(String name, String email) {
        this.name = name;
        this.email = email;
        this.status = "UNKNOWN";
    }

    public void emailSending() {
        this.status = "EMAIL SENT";
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}