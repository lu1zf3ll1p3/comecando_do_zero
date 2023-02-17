package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Formulario {
    Scanner scanner = new Scanner(System.in);
    Usuario usuario = new Usuario();

    public String nome() {
        System.out.print("Informe o nome do Usuário: ");
        return scanner.nextLine();
    }

    public String cpf() {
        System.out.print("Informe o CPF do Usuário: ");
        return scanner.nextLine();
    }

    public String email() {
        System.out.print("Informe o email do Usuário: ");
        return scanner.nextLine();
    }

    public LocalDate dataNascimento() {
        System.out.print("Informe a data de nascimento do Usuário: ");
        String data = scanner.nextLine();
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Sexo sexo() {
        System.out.print("Informe o sexo do Usuário: ");
        Sexo sexo;
        String atualizaSexo = scanner.nextLine();
        sexo = atualizaSexo.equalsIgnoreCase("f") ? Sexo.FEMININO : Sexo.MASCULINO;
        return sexo;
    }

    public String campoVazio(String entrada) {
        while (usuario.campoNulo(entrada)) {
            System.out.print("O campo deve ser preenchido corretamente ! Digite novamente: ");
            entrada = scanner.nextLine();
        }
        return entrada;
    }

}
