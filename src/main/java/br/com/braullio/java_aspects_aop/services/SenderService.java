package br.com.braullio.java_aspects_aop.services;

import org.springframework.stereotype.Service;

import br.com.braullio.java_aspects_aop.domain.Pessoa;

@Service
public class SenderService {

    public boolean email(Pessoa pessoa) {
        System.out.println("~> Enviando Email para " + pessoa.getEmail());

        return true;
    }
}
