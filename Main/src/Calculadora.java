import javax.swing.*;
import java.awt.*; 

@SuppressWarnings("serial")
public class Calculadora extends JFrame{
    Teclado teclado;
    Tela tela;
   
    public Calculadora (){
        setLayout(new GridBagLayout());

        GridBagConstraints cC = new GridBagConstraints();

        cC.fill = GridBagConstraints.BOTH;

        teclado = new Teclado();
        cC.gridx = 0;
        cC.gridy = 1;
        add(teclado, cC);

        tela = new Tela();
        cC.gridx = 0;
        cC.gridy = 0;
        add(tela, cC);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(316,467); 
        setResizable(false);
        setVisible(true);
    }  
}
