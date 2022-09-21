package ex7;
import java.lang.Math;

public class Conta {
	private Pessoa pessoa;
	private String email;
	private String senha;
	private int id;
	private double saldo;
	
	
	public Conta(Pessoa pessoa, String email, double saldo) {
		this.pessoa = pessoa;
		this.email = email;
		this.saldo = saldo;
		this.id = Math.random()
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
}
