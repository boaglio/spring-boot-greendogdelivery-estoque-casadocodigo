package com.boaglio.casadocodigo.greendogdelivery.estoque.carga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.EstoqueRepository;

import reactor.core.publisher.Mono;

@Component
public class RepositoryTest implements ApplicationRunner {

	private static final long TOTAL_DE_REGISTROS = 100000l;

	@Autowired
	private EstoqueRepository repository;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		Long totalAntes = repository.count();

		if (totalAntes <  TOTAL_DE_REGISTROS) {

			System.out.println(">>> Iniciando carga de dados...");

			System.out.println(">>> Total antes: " + Mono.just(totalAntes));

			int contador;
			for (contador = 0; contador < TOTAL_DE_REGISTROS; contador++) {
				repository.save(new Estoque(getRandomIntegerBetweenRange(1, 3), getRandomIntegerBetweenRange(1, 5)));

				if (contador % 100 == 0)
					System.out.println(">>> Gravado estoque: " + (contador + 1));
			}

			System.out.println(">>> Total de registros gravados: " + contador);

			Long totalDepois = repository.count();

			System.out.println(">>> Total depois: " + totalDepois);
		}

	}

	public static long getRandomIntegerBetweenRange(double min, double max) {
		long x = (long) ((int) (Math.random() * ((max - min) + 1)) + min);
		return x;
	}

}
