package ex7;

import javax.swing.JOptionPane;

public class NegativeNumberException extends Exception {
	
	private static final long serialVersionUID = 1149241039409861914L;


    public NegativeNumberException(String msg, String erro){
        JOptionPane.showMessageDialog(null, "Não é possível depositar um número negativo!", "[ERRO]", JOptionPane.ERROR_MESSAGE);
        
    }

    public NegativeNumberException(String msg, Throwable cause){
        super(msg, cause);
    }
}
