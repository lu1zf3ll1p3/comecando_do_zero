package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Formulario {
    Scanner scanner = new Scanner(System.in);
    Usuario usuario = new Usuario();
    UsuarioService service = new UsuarioService();

    public int id(){
        System.out.print("Informe o id a ser atualizado: ");
        int id = scanner.nextInt();
       campoVazio(String.valueOf(id));
        //Usuario usuarioEncontrado = service.retornaUsuario(id);
       // System.out.println(usuarioEncontrado.toString());
        return id;
    }

    public String nome() {
        System.out.print("Informe o nome do Usu�rio: ");
        String nome = scanner.nextLine();
        campoVazio(nome);
        return nome;
    }

    public String cpf() {
        System.out.print("Informe o CPF do Usu�rio: ");
        String cpf = scanner.nextLine();
        campoVazio(cpf);
        return cpf;
    }

    public String email() {
        System.out.print("Informe o email do Usu�rio: ");
        String email = scanner.nextLine();
        campoVazio(email);
        return email;
    }

    public LocalDate dataNascimento() {
        System.out.print("Informe a data de nascimento do Usu�rio: ");
        String data = scanner.nextLine();
        campoVazio(data);
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Sexo sexo() {
        System.out.print("Informe o sexo do Usu�rio: ");
        Sexo sexo;
        String atualizaSexo = scanner.nextLine();
        campoVazio(atualizaSexo);
        sexo = atualizaSexo.equalsIgnoreCase("f") ? Sexo.FEMININO : Sexo.MASCULINO;
        return sexo;
    }
    public void campoVazio(String entrada) {
        while (usuario.campoNulo(entrada)) {
            System.out.print("O campo deve ser preenchido corretamente ! Digite novamente: ");
            entrada = scanner.nextLine();
        }
    }

}
