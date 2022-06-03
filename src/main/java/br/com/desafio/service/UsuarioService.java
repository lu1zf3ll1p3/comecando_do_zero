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


    public Usuario retornaUsuario(int id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (id == usuarios.get(i).getId(id)) {
                return usuarios.get(i);
            }
        }
        return null;
    }


    public Usuario retornaInfo(String atualizaInfo){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual item da lista acima você deseja Atualizar: ");
        switch (atualizaInfo) {
            case "nome":
                System.out.println("Digite o novo nome: ");
                String nome = scanner.next();
                usuarios.set(nome);
                System.out.println("Este é o nome escolhido: " + nome);
                break;

            case "cpf":
                System.out.println("Digite o novo cpf: ");
                String cpf = scanner.next();
                usuarios.set(cpf);
                System.out.println("Este é o cpf escolhido: " + cpf);
                break;

            case "email":
                System.out.println("Digite o novo email: ");
                String email = scanner.next();
                usuarios.set(email);
                System.out.println("Este é o email escolhido: " + email);
                break;

            case "nascimento":
                System.out.println("Digite o nova data do nascimento: ");
                LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                usuarios.setDataNascimento(LocalDate.parse(String.valueOf(dataNascimento)));
                System.out.println("Esta é a nova data do nascimento escolhido: " + dataNascimento);
                break;

            case "sexo":
                System.out.println("Digite o novo sexo: ");
                Sexo sexo = scanner.next().equals("F") ? Sexo.FEMININO : Sexo.MASCULINO;
                usuarios.setSexo(sexo);
                System.out.println("Este é o sexo escolhido: " + sexo);
                break;

            default:
                System.out.println("Não foi possível realizar a sua alteração, verifique se está correta a informação a ser substituída.");
        }
        return null;
    }

}