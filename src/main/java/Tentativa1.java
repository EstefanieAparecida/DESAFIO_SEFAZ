import java.util.ArrayList;
import java.util.Scanner;

import br.com.start.entitys.User;

public class Tentativa1 {

	public static void main(String[] args) {
		Scanner scanner = new java.util.Scanner(System.in);

		ArrayList listaDeUsuarios = new ArrayList();
		
		User user = new User();
		
		int op = 0;

		System.out.println("Digite 1 para cadastrar usu�rios ou 0 para sair !");
		op = scanner.nextInt();

		while (op != 0) {
			System.out.println("Bem vindo ao sistema de cadastros de usu�rios! ");
			
			System.out.println("Digite o nome do usu�rio: ");
			user.setName(scanner.nextLine());
			
			System.out.println("Digite o email do usu�rio: ");
			user.setEmail(scanner.nextLine());

			System.out.println("Digite o id: ");
			user.setId(scanner.nextLine());

			System.out.println("Digite a senha: ");
			user.setPassword(scanner.nextLine());

			listaDeUsuarios.add(user);
			user = new User();

			System.out.println("Digite 1 para cadastrar usu�rios ou 0 para sair !");
			op = scanner.nextInt();
		}

	}
}
