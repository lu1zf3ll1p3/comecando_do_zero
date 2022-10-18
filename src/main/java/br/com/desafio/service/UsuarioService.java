package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UsuarioService {

    private List<Usuario> usuarios;

    //Cria uma nova lista de cadastros.
    public Usuario save(Usuario usuario) {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        setarId(usuario);
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> findAll() {
        return this.usuarios;
    }

    //Gera um novo id para cada cadastro.
    private void setarId(Usuario usuario) {
        int id = 0;
        if (usuarios.isEmpty()) {
            id++;
            usuario.setId(id);
        } else {
            if (usuarios.get(usuarios.size() - 1) != null) {
                id = usuarios.get(usuarios.size() - 1).getId(id);
                id++;
                usuario.setId(id);
            }
        }
    }

    //Deleta um cadastro.
    public boolean delete(int id) {
        boolean find = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (id == usuarios.get(i).getId(id)) {
                find = true;
                usuarios.remove(i);
                break;
            }
        }
        return find;
    }

    //Retorna um id de cadastro da lista.
    public Usuario retornaUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (id == usuario.getId(id)) {
                return usuario;
            }
        }
        return null;
    }

    //Retorna a altera��o solicitada no cadastro.
    public Usuario create(Usuario novoUsuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#####################Sistema de Cadastro de Usu�rios#####################");
        System.out.println("Informe o nome do Usu�rio:");
        String nome = scanner.nextLine();
        System.out.println("Informe o CPF do Usu�rio:");
        String cpf = scanner.nextLine();
        System.out.println("Informe o email do Usu�rio:");
        String email = scanner.nextLine();
        LocalDate dataNascimento = null;
        try {
            System.out.println("Informe a data de nascimento do Usu�rio:");
            dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            System.err.println("O Formato da data est� errado");
        }
        System.out.println("Informe o sexo do Usu�rio: ");
        Sexo sexo = scanner.nextLine().equalsIgnoreCase("F") ? Sexo.FEMININO : Sexo.MASCULINO;
        Usuario usuario = new Usuario(nome, cpf, email, dataNascimento, sexo);
        if (usuario.equals(null)) {
            save(usuario);
        }
        return novoUsuario;
    }

    public Usuario insert(Usuario usuario) {
        create(usuario);
        save(usuario);
        return usuario;
    }


    public Usuario update(Usuario update) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID que deseja atualizar");
        int id = scanner.nextInt();
        Usuario usuarioEncontrado = retornaUsuario(id);
        System.out.println(usuarioEncontrado.toString());
        create(update);
        int position = id - 1;
        usuarios.set(position, usuarioEncontrado);
        System.out.println(usuarioEncontrado);
        return update;
    }


}