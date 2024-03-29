package br.unitins.cinema.application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.unitins.cinema.model.Funcionario;
import br.unitins.cinema.model.Gerente;
import br.unitins.cinema.process.Categoria;
import br.unitins.cinema.process.Filme;
import br.unitins.cinema.process.Sessao;

public class Menu {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Gerente gerente = new Gerente(0, "Paulo", "09090990", LocalDate.of(1900, 3, 3), 4500.0, "Teste", "Tester");
		Funcionario funcionario = new Funcionario(0, "Pedro", "09090990", LocalDate.of(1900, 3, 3), 4500.0, "Gab",
				"Grab");
		String login;
		String senha;
		int count = 0;
		while (count != 3) {
			System.out.println("Por favor digite 1 para fazer o login e a senha");
			int opcao = scan.nextInt();
			if (opcao == 1) {
				System.out.println("Digite seu login: ");
				login = scan.next();
				System.out.println("Digite sua senha: ");
				senha = scan.next();
				if (login.equals(gerente.getLogin()) && senha.equals(gerente.getSenha())) {
					menuCinema(1);
					count = 4;
				} else if (login.equals(funcionario.getLogin()) && senha.equals(funcionario.getSenha())) {
					menuCinema(2);
					count = 4;
				} else {
					System.out.println("Dados incorretos");
					count++;
					System.out.println("Voce tem mais " + (3 - count) + " Tentativas");
				}
			} else if (opcao == 2) {
				System.out.println("Para confirmarmos sua identidade por favor digite seu login e senha antigo: ");
				String verificaLogin = scan.nextLine();
				String verificaSenha = scan.nextLine();
				if (verificaLogin.equals(gerente.getLogin()) && verificaSenha.equals(gerente.getSenha())) {
					System.out.println("Digite o novo Login");
					gerente.setLogin(scan.nextLine());
					System.out.println("Digite a nova senha: ");
					gerente.setSenha(scan.nextLine());
					System.out.println("Novos dados cadastrados");
					count = 4;
				} else {
					System.out.println("Senha ou Login Incorretos");
					count++;
					System.out.println("Voce tem mais " + count + " Tentativas");
				}

			}
		}
		scan.close();
	}

	public static void menuCinema(int teste) {

		List<Sessao> sessoes = new ArrayList<Sessao>();
		LocalTime hora;
		Integer sala;
		String escolhaNome;
		Integer id = sessoes.size();
		int intId = id.intValue();

		Scanner leitor = new Scanner(System.in);
		if (teste == 1) {

			System.out.println("Bem vindo Gerente");
			int opcao = 0;

			// Pr�-Cadastro de Filmes (Como solicitado para ganhar tempo)

			while (opcao != 5) {

				System.out.println(
						"O que deseja fazer? 1- Adicionar uma nova Sessao, 2- Remover uma Sessao Antiga, 3- Listar Todas as Sess�es, 4- Alterar uma sess�o, 5- Sair ");
				opcao = leitor.nextInt();
				if (opcao == 1) {
					System.out.println(
							"Por favor digite a Categoria do filme: 1-A��o, 2-Com�dia, 3 Drama, 4-Fic�ao, 5-Terror");

					int escolha = leitor.nextInt();

					Categoria categoria = null;

					if (escolha == 1)
						categoria = Categoria.ACAO;
					else if (escolha == 2)
						categoria = Categoria.COMEDIA;
					else if (escolha == 3)
						categoria = Categoria.DRAMA;
					else if (escolha == 4)
						categoria = Categoria.FICCAO;
					else if (escolha == 5)
						categoria = Categoria.TERROR;
					else
						System.out.println("Dados inv�lidos");

					System.out.println("Adicione o nome do filme: ");
					escolhaNome = leitor.next();
					System.out.println("Adicione a produtora do filme: ");
					String escolhaProdutora = leitor.next();
					System.out.println("Adicione a sala do filme: ");
					sala = leitor.nextInt();
					System.out.println("Por fim adicione o hor�rio: (Digite as horas primeiro e depois os minutos) ");
					System.out.println("Hora: ");
					int Hora = leitor.nextInt();
					System.out.println("Minutos: ");
					int Minutos = leitor.nextInt();
					hora = LocalTime.of(Hora, Minutos);

					sessoes.add(new Sessao(id, new Filme(categoria, escolhaNome, escolhaProdutora), sala, hora));
					id = sessoes.size();

				} else if (opcao == 2) {
					for (Sessao sessao : sessoes) {
						System.out.println(sessoes);
					}
					System.out.println("\nDigite o ID da Sess�o que voc� Deseja Remover: ");
					int digiteId = leitor.nextInt();
					sessoes.remove(digiteId);
					for (Sessao sessao1 : sessoes) {
						System.out.println(sessao1);
					}
				} else if (opcao == 3) {
					System.out.println(
							"Selecione qual tipo de lista voce quer imprimir: 1- Lista Ordenada por Nome do Filme\n2-Lista Ordenada por Numero da Sala\n3-Lista Ordenada por Catagoria do filme\n4-Lista Ordenada Por produtora\n5 - Lista Ordenada por Hor�rio");
					int listagem = leitor.nextInt();
					if (listagem == 1)
						imprimirListaOrdenadaPorNomeDoFilme(sessoes);
					else if (listagem == 2)
						imprimirListaOrdenadaPorNumeroDaSala(sessoes);
					else if (listagem == 3)
						imprimirListaOrdenadaPorCategoria(sessoes);
					else if (listagem == 4)
						imprimirListaOrdenadaPorProdutora(sessoes);
					else if (listagem == 5)
						imprimirListaOrdenadaPorHorario(sessoes);
					else
						System.out.println("Valor Invalido");

				} else if (opcao == 4) {
					Filme mudarFilme = new Filme(null, null, null);
					System.out.println("Por favor selecione o id da sess�o que deseja alterar: ");
					for (Sessao sessao : sessoes) {
						System.out.println(sessao);
					}
					int mudarSessao = leitor.nextInt();
					System.out.println(
							"Por favor digite a Categoria do filme: 1-A��o, 2-Com�dia, 3 Drama, 4-Fic�ao, 5-Terror");
					int categoria = leitor.nextInt();
					if (categoria == 1)
						mudarFilme.setCategoria(Categoria.ACAO);
					else if (categoria == 2)
						mudarFilme.setCategoria(Categoria.COMEDIA);
					else if (categoria == 3)
						mudarFilme.setCategoria(Categoria.DRAMA);
					else if (categoria == 4)
						mudarFilme.setCategoria(Categoria.FICCAO);
					else if (categoria == 5)
						mudarFilme.setCategoria(Categoria.TERROR);
					else
						System.out.println("Dados inv�lidos");

					System.out.println("Adicione o nome do filme: ");
					mudarFilme.setNome(leitor.next());
					System.out.println("Adicione a produtora do filme: ");
					mudarFilme.setProdutora(leitor.next());
					System.out.println("Adicione a sala do filme: ");
					sala = leitor.nextInt();
					System.out.println("Por fim adicione o hor�rio: (Digite a hora e depois minutos) ");
					System.out.println("Hora: ");
					int Hora = leitor.nextInt();
					System.out.println("Minutos: ");
					int Minutos = leitor.nextInt();
					hora = LocalTime.of(Hora, Minutos);
					sessoes.set(mudarSessao, new Sessao(id, mudarFilme, sala, hora));
				} else if (opcao == 5) {
					System.out.println("Sistema Deslogado");
				}
			}
		} else {
			System.out.println("Bem vindo Funcionario");
			System.out.println("O que deseja fazer? 1- Listar Filmes, 2- Falar com gerente, 3-Sair");
			int opcaoF = leitor.nextInt();
			while (opcaoF != 3)
				if (opcaoF == 1) {
					System.out.println(
							"Selecione qual tipo de lista voce quer imprimir: 1- Lista Ordenada por Nome do Filme\n2-Lista Ordenada por Numero da Sala\n3-Lista Ordenada por Catagoria do filme\n4-Lista Ordenada Por produtora\nLista Ordenada por Hor�rio");
					int listagem = leitor.nextInt();
					if (listagem == 1)
						imprimirListaOrdenadaPorNomeDoFilme(sessoes);
					else if (listagem == 2)
						imprimirListaOrdenadaPorNumeroDaSala(sessoes);
					else if (listagem == 3)
						imprimirListaOrdenadaPorCategoria(sessoes);
					else if (listagem == 4)
						imprimirListaOrdenadaPorProdutora(sessoes);
					else if (listagem == 5)
						imprimirListaOrdenadaPorHorario(sessoes);
					else
						System.out.println("Valor Invalido");

					System.out.println("O que deseja fazer a seguir? 1- Falar com gerente, 2-Sair, 3- Sair ");
					opcaoF = leitor.nextInt();
				} else if (opcaoF == 2) {
					System.out.println("Ainda n�o h� essa op��o");
				} else {
					System.out.println("Saindo");
				}
		}
		leitor.close();
		
	}   //Express�es Lambda para ordena��o da impress�o de listas

	public static void imprimirListaOrdenadaPorNomeDoFilme(List<Sessao> lista) {
		lista.sort((o1, o2) -> o1.getFilme().getNome().compareTo(o2.getFilme().getNome()));
		lista.forEach(p -> System.out.println(p.getFilme().getNome()));
	}

	public static void imprimirListaOrdenadaPorNumeroDaSala(List<Sessao> lista) {
		lista.sort((o1, o2) -> o1.getSala().compareTo(o2.getSala()));
		lista.forEach(p -> System.out.println(p.getSala()));
	}

	public static void imprimirListaOrdenadaPorCategoria(List<Sessao> lista) {
		lista.sort((o1, o2) -> o1.getFilme().getCategoria().compareTo(o2.getFilme().getCategoria()));
		lista.forEach(p -> System.out.println(p.getFilme().getCategoria()));
	}

	public static void imprimirListaOrdenadaPorProdutora(List<Sessao> lista) {
		lista.sort((o1, o2) -> o1.getFilme().getProdutora().compareTo(o2.getFilme().getProdutora()));
		lista.forEach(p -> System.out.println(p.getFilme().getProdutora()));
	}

	public static void imprimirListaOrdenadaPorHorario(List<Sessao> lista) {
		lista.sort((o1, o2) -> o1.getHorarios().compareTo(o2.getHorarios()));
		lista.forEach(p -> System.out.println(p.getHorarios()));
	}

}
