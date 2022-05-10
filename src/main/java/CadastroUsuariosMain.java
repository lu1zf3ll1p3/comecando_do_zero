import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import br.com.desafio.service.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {

    public static void main(String[] args){
        String teste = "S";
        UsuarioService service = new UsuarioService();
        while (teste == "S") {
            Scanner scanner = new Scanner(System.in);
            System.out.println("#####################Sistema de Cadastro de Usuários#####################");
            System.out.println("Informe o nome do Usuário:");
            String nome = scanner.nextLine();
            System.out.println("Informe o CPF do Usuário:");
            String cpf = scanner.nextLine();
            System.out.println("Informe o email do Usuário:");
            String email = scanner.nextLine();
            System.out.println("Informe a data de nascimento do Usuário:");
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.println("Informe o sexo do Usuário:");
            Sexo sexo = scanner.next().equals("F") ? Sexo.FEMININO : Sexo.MASCULINO;
            Usuario usuario = new Usuario(nome, cpf, email, dataNascimento, sexo);
            service.save(usuario);
            System.out.println("Deseja continuar? [S/N]");
            if (scanner.next().equals("N")) {
                teste = "N";
            }

            System.out.print("Deseja Atualizar algum usuário ? [S/N]");
            if (scanner.next().equals("S")) {
                System.out.println("Qual item da lista acima você deseja Atualizar: ");
                String atualizaItem = scanner.next();
                switch (atualizaItem){
                    case novoNome :{
                        System.out.println("Digite o novo nome: ");


                    }

                }

            }


            System.out.print("Deseja excluir algum usuário ? [S/N]");
            if (scanner.next().equals("S")){
                System.out.println("Qual ID você deseja deletar: ");
                int deletarId = scanner.nextInt();
                boolean deletado = service.delete(deletarId);
                if (deletado){
                    System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                }else {
                    System.out.println("Esse ID: " + deletarId + " não pode ser excluido ou não existe !!!");
                }
            }
            System.out.println("Deseja ver a lista de usuários cadastrados? [S/N]");
            if (scanner.next().equals("S")) {
                List<Usuario> usuariosList = service.findAll();
                for (int i= 0; i < usuariosList.size(); i++) {
                    System.out.println(usuariosList.get(i).toString());
                }
            }
        }
    }
}
