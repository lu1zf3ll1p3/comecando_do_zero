package br.com.desafio;

import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import br.com.desafio.service.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {
    static Scanner scanner = new Scanner(System.in);
    static UsuarioService service = new UsuarioService();

    public static void main(String[] args) {

        String continua = "S";
        while (continua.equals("S")) {
            System.out.println("#####################Sistema de Cadastro de Usuários#####################");
            System.out.print("Selecione a opção para continuar: \n[1]Cadastrar um novo usuário  \n[2]Atualizar um usuário existente " +
                    "\n[3]Deletar um usuário existente \n[4]Listar usuários da lista \n[5]Listar um usuário \n[6]Sair \nSelecione: ");
            switch (scanner.nextInt()) {
                case 1 -> service.save(usuario());
                case 2 -> {
                    try {
                        System.out.println("Digite o id a ser atualizado: ");
                        Usuario usuarioEncontrado = service.findOne(scanner.nextInt());
                        service.update(usuarioEncontrado.getId(), usuario());
                    } catch (RuntimeException idNaoEncontrado) {
                        System.out.println("O ID informado não foi encontrado! ");
                    }
                }
                case 3 -> {
                    //Realiza a Exclusão de algum cadastro dentro da lista.
                    System.out.println("Qual ID você deseja deletar: ");
                    boolean deletado = service.delete(scanner.nextInt());
                    if (deletado) {
                        System.out.println("O ID Selecionado foi exluido com sucesso.");
                    }
                }
                case 4 -> {
                    service.findAll().forEach(usuarios -> System.out.println(usuarios.toString()));
                }
                case 5 -> {
                    try {
                        System.out.print("Selecione o ID correspondente ao usuario desejado: ");
                        System.out.println(service.findOne(scanner.nextInt()));
                    } catch (RuntimeException idNaoExistente) {
                        System.out.println("ID não encontrado !");
                    }
                }
                case 6 -> {
                    continua = "N";
                    System.err.println("Fim do Programa !!!");
                }
                default -> System.out.println("Seleção invalida");
            }
        }
    }

    public static Usuario usuario() {
        scanner.nextLine();
        System.out.print("Insira seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Insira seu cpf: ");
        String cpf = scanner.nextLine();
        System.out.print("Insira seu email: ");
        String email = scanner.nextLine();
        System.out.print("Insira sua data de nascimento [dd/MM/aaaa]: ");
        String entradaDataNascimento = scanner.nextLine();
        LocalDate dataNascimento = service.verificaCampoNulo(entradaDataNascimento) ? null : LocalDate.parse(entradaDataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Insira seu sexo [F/M]: ");
        String escolhaSexo = scanner.nextLine();
        Sexo sexo = service.verificaCampoNulo(escolhaSexo) ? null : escolhaSexo.equalsIgnoreCase("f") ? Sexo.FEMININO : Sexo.MASCULINO;
        return new Usuario(nome, cpf, email, dataNascimento, sexo);
    }

}
