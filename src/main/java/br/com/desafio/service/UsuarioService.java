package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;
import java.util.ArrayList;
import java.util.List;

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
                id = usuarios.get(usuarios.size() - 1).getId();
                id++;
                usuario.setId(id);
            }
        }
    }

    public boolean delete(int id) {
        boolean find = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (id == usuarios.get(i).getId()) {
                find = true;
                usuarios.remove(i);
                break;
            }
        }
        return find;
    }


    public int update(int id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.contains(id)){
                usuarios.listIterator(id);
            }
        }
        return id;
    }

}

