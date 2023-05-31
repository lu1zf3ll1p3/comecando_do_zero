package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private List<Usuario> usuarios;

    //Cria uma nova lista de cadastros.
    public void save(Usuario usuario) {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        setarId(usuario);
        usuarios.add(usuario);
    }

    public List<Usuario> findAll() {
        return this.usuarios;
    }

    //Gera um novo id para cada cadastro.
    private void setarId(Usuario usuario) {
        int id = usuarios.isEmpty() ? 1 : usuarios.get(usuarios.size() - 1).getId() + 1;
        usuario.setId(id);
    }

    //Deleta um cadastro.
    public boolean delete(int id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (id == usuarios.get(i).getId()) {
                usuarios.remove(i);
                return true;
            }
        }
        return false;
    }

    //Retorna um id de cadastro da lista.
    public Usuario retornaUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (id == usuario.getId()) {
                return usuario;
            }
        }
        return null;
    }

    public void update(Usuario usuario) {
        Usuario usuarioEncontrado = retornaUsuario(usuario.getId());
        if (usuario.getNome().isEmpty()) usuario.setNome(usuarioEncontrado.getNome());
        if (usuario.getCpf().isEmpty()) usuario.setCpf(usuarioEncontrado.getCpf());
        if (usuario.getEmail().isEmpty()) usuario.setEmail(usuarioEncontrado.getEmail());
        if (usuario.getDataNascimento() == null) usuario.setDataNascimento(usuarioEncontrado.getDataNascimento());
        if (usuario.getSexo() == null) usuario.setSexo(usuarioEncontrado.getSexo());
        usuarios.set(usuario.getId() -1, usuario);
    }

}
