package br.com.braullio.java_aspects_aop;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.braullio.java_aspects_aop.domain.Pessoa;
import br.com.braullio.java_aspects_aop.services.SenderService;

@SpringBootApplication
public class JavaAspectsAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAspectsAopApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(SenderService sending) {
        return args -> {
            Pessoa pessoa = new Pessoa("João", "joao@email.com");
            sending.email(pessoa);
            pessoa.emailSending();
			sending.email(pessoa);
        };
	}
}