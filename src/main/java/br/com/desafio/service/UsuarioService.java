package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private List<Usuario> usuarios;

    //Gera um novo id para cada cadastro.
    private void setarId(Usuario usuario) {
        int id = 0;
        if (usuarios.isEmpty()) {
            id++;
            usuario.setId(id);
        } else {
            if (usuarios.get(usuarios.size() - 1) != null) {
                id = usuarios.get(usuarios.size() - 1).getId();
                id++;
                usuario.setId(id);
            }
        }
    }

    //Cria uma nova lista de cadastros.
    public void save(Usuario usuario) {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        setarId(usuario);
        usuarios.add(usuario);
    }

    //busca todos os Usuarios da Lista
    public List<Usuario> findAll() {
        return this.usuarios;
    }

    //Deleta um cadastro.
    public boolean delete(int id) {
        try {
            return usuarios.remove(usuarios.stream().filter(usuario -> id == usuario.getId()).findFirst().orElseThrow());
        } catch (NullPointerException erroDeletarId) {
            System.out.println("Esse ID: " + id + " não pode ser excluido ou não existe !!!");
        }
        return false;
    }

    //Retorna um usuario de cadastro da lista.
    public Usuario findOne(int id) {
        return usuarios.stream().filter(usuario -> id == usuario.getId()).findFirst().orElseThrow();
    }

    //Atualiza o usuario selecionado da lista
    public void update(int id, Usuario usuario) {
        Usuario usuarioEncontrado = findOne(id);
        System.out.println(usuarioEncontrado.toString());
        if (!verificaCampoNulo(usuario.getNome())) usuarioEncontrado.setNome(usuario.getNome());
        if (!verificaCampoNulo(usuario.getCpf())) usuarioEncontrado.setCpf(usuario.getCpf());
        if (!verificaCampoNulo(usuario.getEmail())) usuarioEncontrado.setEmail(usuario.getEmail());
        if (!verificaCampoNulo(usuario.getDataNascimento()))
            usuarioEncontrado.setDataNascimento(usuario.getDataNascimento());
        if (!verificaCampoNulo(usuario.getSexo())) usuarioEncontrado.setSexo(usuario.getSexo());
    }

    public boolean verificaCampoNulo(Object campo) {
        if (campo == null) {
            return true;
        } else if (campo instanceof String) {
            return ((String) campo).isEmpty() || ((String) campo).isBlank();
        }
        return false;
    }

}
