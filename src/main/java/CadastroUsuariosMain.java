import br.com.desafio.model.entity.Usuario;
import br.com.desafio.service.Formulario;
import br.com.desafio.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {

    public static void main(String[] args) {
        UsuarioService service = new UsuarioService();
        Formulario formulario = new Formulario();
        Scanner scanner = new Scanner(System.in);

        String continua = "S";
        while (continua.equals("S")) {
            System.out.println("#####################Sistema de Cadastro de Usuários#####################");
            System.out.print("Selecione a opção para continuar: \n[1]Cadastrar um novo usuário ou Atualizar um usuário existente " +
                    "\n[2]Deletar um usuário existente \n[3]Listar usuários da lista \n[4]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1 -> {
                    System.out.println("Deseja realizar um novo [C]adrastro ou uma [A]tualização: ");
                    String selecao = scanner.nextLine();
                    if (selecao.equalsIgnoreCase("c")) {
                        Usuario usuario = new Usuario(
                                formulario.campoVazio(formulario.nome()),
                                formulario.campoVazio(formulario.cpf()),
                                formulario.campoVazio(formulario.email()),
                                formulario.campoVazio(formulario.dataNascimento()),
                                formulario.campoVazio(formulario.sexo()));
                        service.save(usuario);
                    } else if (selecao.equalsIgnoreCase("a")) {
                        System.out.println("Digite o id a ser atualizado: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Usuario usuarioEncontrado = service.retornaUsuario(id);
                        System.out.println(usuarioEncontrado.toString());
                        Usuario atualizaUsuario = new Usuario(id, formulario.nome(),
                                formulario.cpf(),
                                formulario.email(),
                                formulario.dataNascimento(),
                                formulario.sexo());
                        service.update(atualizaUsuario);
                    } else {
                        System.out.println("Não foi possivel realizar sua solicitação tente novamente !");
                    }
                }

                case 2 -> {
                    //Realiza a Exclusão de algum cadastro dentro da lista.
                    System.out.println("Qual ID você deseja deletar: ");
                    int deletarId = scanner.nextInt();
                    boolean deletado = service.delete(deletarId);
                    if (deletado) {
                        System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                    } else {
                        System.out.println("Esse ID: " + deletarId + " não pode ser excluido ou não existe !!!");

                    }
                }

                case 3 -> {
                    //Busca os cadastros da lista.
                    List<Usuario> usuariosList = service.findAll();
                    for (Usuario usuarios : usuariosList) {
                        System.out.println(usuarios.toString());
                    }
                }
                default -> {
                    continua = "N";
                    System.err.println("Fim do Programa !!!");
                }
            }
        }

    }
}
