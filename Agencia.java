package ex7;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class Agencia {
	static ArrayList<Conta> listaContas = new ArrayList<Conta>();
	public static void main(String[] args) {	
		JOptionPane.showMessageDialog(null, "----------------------------------------------------------------------------"
				+ "\n------------------- BEM VINDO A MACOBANK! -------------------"
				+ "\n----------------------------------------------------------------------------", "Fala papis!", JOptionPane.CLOSED_OPTION);
		menu();
	}
	
	public static void menu() {
		int op = Integer.parseInt(JOptionPane.showInputDialog(null, "[1] Logar na conta"
																+ "\n[2] Criar uma conta"
																+ "\n[3] Sair", "MacoBank", JOptionPane.PLAIN_MESSAGE));
		switch (op) {
		case 1:
			logarConta();
			break;
		case 2:
			criarConta();
			break;
		case 3:
			int input = JOptionPane.showConfirmDialog(null, "Tem certeza disso? ", null , JOptionPane.YES_NO_OPTION);
			if (input == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Adiós, muchacho!");
				System.exit(0);
			} else {
				menu();
			}
		}
		
	}
	
	public static void logarConta() {
		JPasswordField senha = new JPasswordField(20);
		senha.setEchoChar('*');
		JTextField email = new JTextField(20);
		JLabel labelEmail = new JLabel("Digite o email: ");
		JLabel labelSenha = new JLabel("Digite a senha: ");
		
		JPanel entUsuario = new JPanel();
		entUsuario.setLayout(new BoxLayout(entUsuario, BoxLayout.Y_AXIS));
		entUsuario.add(labelEmail);
		entUsuario.add(email);
		entUsuario.add(labelSenha);
		entUsuario.add(senha);
		
		JOptionPane.showMessageDialog(null, entUsuario, "Logar na conta", JOptionPane.PLAIN_MESSAGE);
		
		String emailstring = email.getText();
		String senhastring = String.copyValueOf(senha.getPassword());
		
		// Modificar isso aqui depois!!!!!
		if (listaContas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Esse email não está cadastrado no banco. Por favor, crie sua conta.", "[ERRO]", JOptionPane.PLAIN_MESSAGE);
			menu();			
		} else {
			for (Conta arr : listaContas) {
				if (emailstring.equals(arr.getEmail())) {
					if (senhastring.equals(arr.getSenha())) {
						JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!", null, JOptionPane.PLAIN_MESSAGE);
						menuConta(arr);
					} else {
						JOptionPane.showMessageDialog(null, "Senha inválida. Por favor, tente novamente.", "[ERRO]", JOptionPane.WARNING_MESSAGE);
						logarConta();
					}
				}
			}
		}
		
		
	}
	
	public static void criarConta() {
		JPanel entUsuario = new JPanel();
		JTextField nome = new JTextField(20);
		JTextField sobrenome = new JTextField(20);
		JTextField email = new JTextField(20);
		JTextField idade = new JTextField(2);
		JTextField cpf = new JTextField(15);
		JPasswordField senha = new JPasswordField(20);
		JPasswordField confirmaSenha = new JPasswordField(20);
		senha.setEchoChar('*');
		confirmaSenha.setEchoChar('*');
		
		entUsuario.setLayout(new BoxLayout(entUsuario, BoxLayout.Y_AXIS));
		entUsuario.add(new JLabel("Nome: "));
		entUsuario.add(nome);
		entUsuario.add(new JLabel("Sobrenome: "));
		entUsuario.add(sobrenome);
		entUsuario.add(new JLabel("Email: "));
		entUsuario.add(email);
		entUsuario.add(new JLabel("Idade: "));
		entUsuario.add(idade);
		entUsuario.add(new JLabel("CPF: "));
		entUsuario.add(cpf);
		entUsuario.add(new JLabel("Senha: "));
		entUsuario.add(senha);
		entUsuario.add(new JLabel("Confirme a senha: "));
		entUsuario.add(confirmaSenha);
		
		JOptionPane.showMessageDialog(null, entUsuario);
		
		String emailstring = email.getText();
		String senhastring = String.copyValueOf(senha.getPassword());
		
		for (Conta arr : listaContas) {
			if (!emailstring.equals(arr.getEmail())) {
				JOptionPane.showMessageDialog(null, "O email digitado já está registrado no banco.", null, JOptionPane.PLAIN_MESSAGE);
				criarConta();
			} 
		}
		
		String erros = "";
		
		if (nome.getText().isBlank() || sobrenome.getText().isBlank()) {
			erros += "\nO nome/sobrenome deve ser preenchido";
			
			// JOptionPane.showMessageDialog(null, "O nome/sobrenome deve ser preenchido", null, JOptionPane.PLAIN_MESSAGE);
		}
		
		if (!emailstring.contains("@")) {
			erros += "\nDigite um endereço de email válido";
			
			// JOptionPane.showMessageDialog(null, "Digite um endereço de email válido.", null, JOptionPane.PLAIN_MESSAGE);
		}
		
		if (cpf.getText().length() != 11) {
			erros += "\nO CPF deve ter 11 caracteres";
			
			// JOptionPane.showMessageDialog(null, "O CPF deve ter 11 caracteres", null, JOptionPane.PLAIN_MESSAGE);
		}
		
		if (Integer.parseInt(idade.getText()) < 1 || Integer.parseInt(idade.getText()) > 150) {
			erros += "\nDigite uma idade válida";
		} 
		
		if (!senhastring.equals(String.copyValueOf(confirmaSenha.getPassword()))) {
			erros += "\nAs senhas devem ser iguais";
			
			// JOptionPane.showMessageDialog(null, "Por favor, digite duas senhas iguais.", null, JOptionPane.PLAIN_MESSAGE);
		}
		
		
		if (erros.length() > 0) {
			JTextArea area = new JTextArea(erros);
			JPanel painel = new JPanel();
			painel.add(area);
			JOptionPane.showMessageDialog(null, painel, null, JOptionPane.PLAIN_MESSAGE);
			criarConta();
		} else {
			JOptionPane.showMessageDialog(null, "Conta criada com sucesso!", "Login efetuado", JOptionPane.PLAIN_MESSAGE);
			Pessoa pessoa = new Pessoa(nome.getText(), sobrenome.getText(), Integer.parseInt(idade.getText()), cpf.getText());
			Conta contaPessoa = new Conta(pessoa, emailstring, 0);
			listaContas.add(contaPessoa);
			menuConta(contaPessoa);
		}
			
		
		
		
	}
	
	public static void menuConta(Conta contaPessoa) {
		int op = Integer.parseInt(JOptionPane.showInputDialog("Selecione uma das opções a seguir: "
				+ "\n [1] Depositar"
				+ "\n [2] Sacar"
				+ "\n [3] Transferir"
				+ "\n [4] Ver informações da conta"
				+ "\n [5] Sair"));
		
		switch (op) {
		case 1:
			// depositar();
			break;
			
		case 2:
			//Depositar();
			
		case 3:
			//Sacar();
			
		case 4:
			//Transferir();
			
		case 5:
			menu();
			
		default:
			JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente.", "[ERRO]", JOptionPane.ERROR_MESSAGE);
			menu();
			break;
		}	
	}
	
	
}
