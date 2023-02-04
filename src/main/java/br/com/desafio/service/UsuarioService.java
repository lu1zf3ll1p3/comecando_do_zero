package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class UsuarioService {

    private List<Usuario> usuarios;

    //Cria uma nova lista de cadastros.
    public void save(Usuario usuario) {
        if (usuarios == null) usuarios = new ArrayList<>();
        setarId(usuario);
        usuarios.add(usuario);
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
            if (id == usuario.getId(id)) return usuario;
        }
        return null;
    }

    public void update(int id, String nome, String cpf, String email, LocalDate dataNascimento, Sexo sexo) {
        Usuario usuarioEncontrado = retornaUsuario(id);
        if (nome.isEmpty()) nome = usuarioEncontrado.getNome(null);
        if (cpf.isEmpty()) cpf = usuarioEncontrado.getCpf(null);
        if (email.isEmpty()) email = usuarioEncontrado.getEmail(null);
        if (dataNascimento == null) dataNascimento = usuarioEncontrado.getDataNascimento(null);
        if (sexo == null) sexo = usuarioEncontrado.getSexo(null);
        Usuario usuario = new Usuario(id, nome, cpf, email, dataNascimento, sexo);
        int pessoa = id - 1;
        usuarios.set(pessoa, usuario);
        System.out.println(usuario);
    }

}
