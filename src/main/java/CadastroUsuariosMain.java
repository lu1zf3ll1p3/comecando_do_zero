import br.com.desafio.model.entity.Usuario;
import br.com.desafio.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {

    public static void main(String[] args) {

        String continua = "S";
        UsuarioService service = new UsuarioService();
        Scanner scanner = new Scanner(System.in);
        while (continua == "S") {
            System.out.println("#####################Sistema de Cadastro de Usu�rios#####################");
            System.out.print("Selecione a op��o para continuar: \n[1]Cadastrar um novo usu�rio \n[2]Atualizar um usu�rio existente \n[3]Deletar um usu�rio existente \n[4]Listar usu�rios da lista \n[5]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1: {
                    //Realiza o Cadastro de Usu�rios.
                    service.insert(null);
                    break;
                }

                case 2: {
                    //Faz a Atualiza��o das informa��es desejadas do cadastro.
                    service.update(null);
                    break;
                }

                case 3: {
                    //Realiza a Exclus�o de algum cadastro dentro da lista.
                    System.out.println("Qual ID voc� deseja deletar: ");
                    int deletarId = scanner.nextInt();
                    boolean deletado = service.delete(deletarId);
                    if (deletado) {
                        System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                    } else {
                        System.out.println("Esse ID: " + deletarId + " n�o pode ser excluido ou n�o existe !!!");
                    }
                    break;
                }


                case 4: {
                    //Busca os cadastros da lista.
                    List<Usuario> usuariosList = service.findAll();
                    for (int i = 0; i < usuariosList.size(); i++) {
                        System.out.println(usuariosList.get(i).toString());
                    }
                    break;
                }


                default: {
                    continua = "N";
                }
            }
        }

        if (continua.equals("N")) {
            System.err.println("Fim do Programa !!!");
        }

    }
}

