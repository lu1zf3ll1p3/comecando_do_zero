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

    public Usuario(String nome, String cpf, String email, LocalDate dataNascimento, Sexo sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public Usuario(Integer id, String nome, String cpf, String email, LocalDate dataNascimento, Sexo sexo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public Integer getId(int id) {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome(String nome) {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf(String cpf) {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento(LocalDate dataNascimento) {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo(Sexo sexo) {
        return this.sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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
