package br.com.desafio.model.entity;

import br.com.desafio.model.enums.Sexo;

import java.time.LocalDate;

public class Usuario {

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private Sexo sexo;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String email, LocalDate dataNascimento, Sexo sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public Usuario(int id, String nome, String cpf, String email, LocalDate dataNascimento, Sexo sexo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public LocalDate setDataNascimento(LocalDate dataNascimento) {
        return this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public Sexo setSexo(Sexo sexo) {
        this.sexo = sexo;
        return sexo;
    }

    public boolean campoNulo(Object campo) {
        if(campo == null){
            return true;
        }else if(campo instanceof String){
           return ((String) campo).isEmpty();
        }
        return false;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                '}';
    }

}
