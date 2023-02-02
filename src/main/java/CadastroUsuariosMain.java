import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import br.com.desafio.service.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {

    public static void main(String[] args) {

        String continua = "S";
        UsuarioService service = new UsuarioService();
        Scanner scanner = new Scanner(System.in);
        while (continua.equals("S")) {
            System.out.println("#####################Sistema de Cadastro de Usu�rios#####################");
            System.out.print("Selecione a op��o para continuar: \n[1]Cadastrar um novo usu�rio ou Atualizar um usu�rio existente " +
                    "\n[2]Deletar um usu�rio existente \n[3]Listar usu�rios da lista \n[4]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1 -> {
                    //Realiza o Cadastro de Usu�rios.
                    System.out.println("Deseja realizar uma [C]adastro ou [A]tualiza��o: ");
                    String cadastro = scanner.nextLine();
                    System.out.print("Informe o nome do Usu�rio: ");
                    String nome = scanner.nextLine();
                    System.out.print("Informe o CPF do Usu�rio: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Informe o email do Usu�rio: ");
                    String email = scanner.nextLine();
                    System.out.print("Informe a data de nascimento do Usu�rio: ");
                    LocalDate dataNascimento;
                    try {
                        dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (Exception e) {
                        System.out.println("Data inv�lida. O campo ser� considerado vazio, assim colocando o existente.");
                        dataNascimento = null;
                    }
                    System.out.print("Informe o sexo do Usu�rio: ");
                    Sexo sexo = scanner.nextLine().equalsIgnoreCase("F") ? Sexo.FEMININO : scanner.nextLine().equalsIgnoreCase("M") ? Sexo.MASCULINO : null;
                    Usuario usuario = new Usuario(nome, cpf, email, dataNascimento, sexo);
                    if (cadastro.equalsIgnoreCase("C")) {
                        service.save(usuario);
                        System.out.println(usuario);
                    } else {
                        if (service.findAll() != null) {
                            System.out.println("Digite o ID que deseja atualizar: ");
                            int id = scanner.nextInt();
                            System.out.println("\nDeseja realizar essa altera��o ? [S/N]");
                            String alteracao = scanner.next();
                            if (alteracao.equalsIgnoreCase("s")) {
                                service.update(id, nome, cpf, email, dataNascimento, sexo);
                            }
                        } else {
                            System.out.println("N�o existem cadastros para serem atualizados.");
                        }
                    }
                }
                case 2 -> {
                    //Realiza a Exclus�o de algum cadastro dentro da lista.
                    try {
                        System.out.println("Qual ID voc� deseja deletar: ");
                        int deletarId = scanner.nextInt();
                        boolean deletado = service.delete(deletarId);
                        if (deletado) {
                            System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                        } else {
                            System.out.println("Esse ID: " + deletarId + " n�o pode ser excluido ou n�o existe !!!");
                        }
                    } catch (Exception exclusaoSemSucesso) {
                        System.err.println("n�o foi possivel realizar essa exclus�o !");
                    }
                }
                case 3 -> {
                    //Busca os cadastros da lista.
                    try {
                        List<Usuario> usuariosList = service.findAll();
                        for (Usuario usuario : usuariosList) {
                            System.out.println(usuario.toString());
                        }
                    } catch (NullPointerException semUsuariosNaLista) {
                        System.err.println("N�o existem usuarios na lista... !");
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
