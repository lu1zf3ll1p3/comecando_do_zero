import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import br.com.desafio.service.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {
    static Scanner scanner = new Scanner(System.in);
    static UsuarioService service = new UsuarioService();

    public static void main(String[] args) {

        String continua = "S";
        while (continua.equals("S")) {
            System.out.println("#####################Sistema de Cadastro de Usu�rios#####################");
            System.out.print("Selecione a op��o para continuar: \n[1]Cadastrar um novo usu�rio  \n[2]Atualizar um usu�rio existente " +
                    "\n[3]Deletar um usu�rio existente \n[4]Listar usu�rios da lista \n[5]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1 -> service.save(usuario());
                case 2 -> {
                    try {
                        System.out.println("Digite o id a ser atualizado: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Usuario usuarioEncontrado = service.retornaUsuario(id);
                        System.out.println(usuarioEncontrado.toString());
                        service.update(id, usuario());
                    } catch (Exception idNaoEncontrado) {
                        System.out.println("O ID informado n�o foi encontrado! ");
                    }
                }
                case 3 -> {
                    //Realiza a Exclus�o de algum cadastro dentro da lista.
                    System.out.println("Qual ID voc� deseja deletar: ");
                    int deletarId = scanner.nextInt();
                    boolean deletado = service.delete(deletarId);
                    if (deletado) {
                        System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                    } else {
                        System.out.println("Esse ID: " + deletarId + " n�o pode ser excluido ou n�o existe !!!");
                    }
                }
                case 4 -> {
                    //Busca os cadastros da lista.
                    List<Usuario> usuariosList = service.findAll();
                    for (Usuario usuarios : usuariosList) {
                        System.out.println(usuarios.toString());
                    }
                }
                case 5 -> {
                    continua = "N";
                    System.err.println("Fim do Programa !!!");
                }
                default -> System.out.println("Sele��o invalida");
            }
        }
    }

    public static Usuario usuario() {
        System.out.print("Insira seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Insira seu cpf: ");
        String cpf = scanner.nextLine();
        System.out.print("Insira seu email: ");
        String email = scanner.nextLine();
        System.out.print("Insira sua data de nascimento [dd/MM/aaaa]: ");
        String entradaDataNascimento = scanner.nextLine();
        LocalDate dataNascimento = entradaDataNascimento.isEmpty() ? null : LocalDate.parse(entradaDataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Insira seu sexo [F/M]: ");
        String escolhaSexo = scanner.nextLine();
        Sexo sexo = escolhaSexo.isEmpty() ? null : escolhaSexo.equalsIgnoreCase("f") ? Sexo.FEMININO : Sexo.MASCULINO;
        return new Usuario(nome, cpf, email, dataNascimento, sexo);
    }

}
