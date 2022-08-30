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

    //Retorna a alteração solicitada no cadastro.
    public Usuario updateUsuario(String atualizaInfo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID que deseja atualizar: ");
        int id = scanner.nextInt();
        Usuario usuario = retornaUsuario(id);
        Integer updateId = usuario.getId(id);
        if (updateId != null) {
            switch (atualizaInfo) {
                case "nome":
                    System.out.println("Digite o novo nome: ");
                    String nome = scanner.next();
                    usuario.setNome(nome);
                    System.out.println("Este é o nome escolhido: " + nome);
                    break;

                case "cpf":
                    System.out.println("Digite o novo cpf: ");
                    String cpf = scanner.next();
                    usuario.setCpf(cpf);
                    System.out.println("Este é o cpf escolhido: " + cpf);
                    break;

                case "email":
                    System.out.println("Digite o novo email: ");
                    String email = scanner.next();
                    usuario.setEmail(email);
                    System.out.println("Este é o email escolhido: " + email);
                    break;

                case "nascimento":
                    System.out.println("Digite o nova data do nascimento: ");
                    LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    usuario.setDataNascimento(dataNascimento);
                    System.out.println("Esta é a nova data do nascimento escolhido: " + dataNascimento);
                    break;

                case "sexo":
                    System.out.println("Digite o novo sexo: ");
                    Sexo sexo = scanner.next().equals("F") ? Sexo.FEMININO : Sexo.MASCULINO;
                    usuario.setSexo(sexo);
                    System.out.println("Este é o sexo escolhido: " + sexo);
                    break;

                default:
                    System.out.println("Não foi possível realizar a sua alteração, verifique se está correta a informação a ser substituída.");
            }
        } else {
            System.out.print("Id: " + id + " não encontrado.");
        }

        return null;
    }

}