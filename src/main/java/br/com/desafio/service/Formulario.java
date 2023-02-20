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
        System.out.println("Informe o nome do Usuário: ");
        return scanner.nextLine();
    }

    public String cpf() {
        System.out.println("Informe o CPF do Usuário: ");
        return scanner.nextLine();
    }

    public String email() {
        System.out.println("Informe o email do Usuário: ");
        return scanner.nextLine();
    }

    public LocalDate dataNascimento() {
        System.out.println("Informe a data de nascimento do Usuário: ");
        String data = scanner.nextLine();
        LocalDate dataNascimento = null;
        if (data != null && !data.isEmpty()) {
            dataNascimento = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return dataNascimento;
    }

    public Sexo sexo() {
        System.out.println("Informe o sexo do Usuário: ");
        String atualizaSexo = scanner.nextLine();
        Sexo sexo = null;
        if (atualizaSexo != null && !atualizaSexo.isEmpty()) {
            sexo = atualizaSexo.equalsIgnoreCase("f") ? Sexo.FEMININO : Sexo.MASCULINO;
        }
        return sexo;
    }

    public String campoVazio(String entrada) {
        do {
            System.out.println("O campo deve ser preenchido corretamente ! Digite novamente: ");
            entrada = scanner.nextLine();
        } while (usuario.campoNulo(entrada));
        return entrada;
    }

    public LocalDate campoVazio(LocalDate entrada) {
        while (usuario.campoNulo(entrada)) {
            System.out.println("O campo deve ser preenchido corretamente ! Digite novamente: ");
            entrada = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return entrada;
    }

    public Sexo campoVazio(Sexo entrada) {
        while (usuario.campoNulo(entrada)) {
            System.out.println("O campo deve ser preenchido corretamente ! Digite novamente: ");
            entrada = scanner.next().equalsIgnoreCase("f") ? Sexo.FEMININO : Sexo.MASCULINO;
        }
        return entrada;
    }

}
