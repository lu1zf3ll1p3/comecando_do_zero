import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import br.com.desafio.service.UsuarioService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {

    public static void main(String[] args) {

        String continua = "S";
        UsuarioService service = new UsuarioService();
        while (continua == "S") {
            //Realiza o Cadastro de Usu?rios.
            Scanner scanner = new Scanner(System.in);
            service.insert(null);


            //Faz a Atualização das informações desejadas do cadastro.
            System.out.println("Deseja Atualizar algum Usuário ? [S/N]");
            if (scanner.next().equalsIgnoreCase("S")) {
                service.update(null);
            }



            //Realiza a Exclusão de algum cadastro dentro da lista.
            System.out.println("Deseja excluir algum usuário ? [S/N]");
            if (scanner.next().equalsIgnoreCase("S")) {
                System.out.println("Qual ID você deseja deletar: ");
                int deletarId = scanner.nextInt();
                boolean deletado = service.delete(deletarId);
                if (deletado) {
                    System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                } else {
                    System.out.println("Esse ID: " + deletarId + " não pode ser excluido ou não existe !!!");
                }
            }

            //Busca os cadastros da lista.
            System.out.println("Deseja ver a lista de usuários cadastrados? [S/N]");
            if (scanner.next().equalsIgnoreCase("S")) {
                List<Usuario> usuariosList = service.findAll();
                for (int i = 0; i < usuariosList.size(); i++) {
                    System.out.println(usuariosList.get(i).toString());
                }
            }

            System.out.println("Deseja continuar? [S/N]");
            if (scanner.next().equalsIgnoreCase("N")) {
                continua = "N";
            }

            if (continua.equals("N")) {
                System.err.println("Fim do Programa !!!");
            }

        }
    }
}
