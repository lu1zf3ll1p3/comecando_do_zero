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
            System.out.println("#####################Sistema de Cadastro de Usuários#####################");
            System.out.print("Selecione a opção para continuar: \n[1]Cadastrar um novo usuário \n[2]Atualizar um usuário existente \n[3]Deletar um usuário existente \n[4]Listar usuários da lista \n[5]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1: {
                    //Realiza o Cadastro de Usuários.
                    service.insert(null);
                    break;
                }

                case 2: {
                    //Faz a Atualização das informações desejadas do cadastro.
                    service.update(null);
                    break;
                }

                case 3: {
                    //Realiza a Exclusão de algum cadastro dentro da lista.
                    System.out.println("Qual ID você deseja deletar: ");
                    int deletarId = scanner.nextInt();
                    boolean deletado = service.delete(deletarId);
                    if (deletado) {
                        System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                    } else {
                        System.out.println("Esse ID: " + deletarId + " não pode ser excluido ou não existe !!!");
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

