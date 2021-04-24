import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Tela extends JPanel  {
    Border exterior = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createRaisedBevelBorder());
    Border borderRaised = BorderFactory.createRaisedBevelBorder();
    GridBagConstraints cG = new GridBagConstraints();
    Font texto = new Font("Consolas", Font.PLAIN, 20);
    Color cinza = new Color(24, 27, 31);
    Color cinzaClaro = new Color(53, 55, 61);
    Color Verde = new Color(55, 112, 55);
    Color laranja = new Color(199, 92, 16);
    static JLabel Y,X,A, S, S2, S3;

    Tela(){
        setBorder(exterior);
        setBackground(cinza);
        setLayout(new GridBagLayout());

        JPanel EBaixo= new JPanel();
        EBaixo.setOpaque(false);
        EBaixo.setPreferredSize(new Dimension(100, 20));
        cG.gridx = 0;
        cG.gridy = 2;
        add(EBaixo, cG);

        JPanel ECima= new JPanel();
        ECima.setOpaque(false);
        ECima.setPreferredSize(new Dimension(100, 20));
        cG.gridx = 0;
        cG.gridy = 0;
        add(ECima, cG);

        JPanel telaOut = new JPanel();
        telaOut.setLayout(new GridBagLayout());
        telaOut.setOpaque(true);
        telaOut.setPreferredSize(new Dimension(280, 100));
        telaOut.setBackground(cinzaClaro);
        cG.gridx = 0;
        cG.gridy = 1;
        add(telaOut, cG);

        JLabel telaIn = new JLabel();
        telaIn.setLayout(new GridBagLayout());
        telaIn.setOpaque(true);
        telaIn.setPreferredSize(new Dimension(260, 80));
        telaIn.setBackground(Verde);
        cG.gridx = 0;
        cG.gridy = 0;
        telaOut.add(telaIn, cG);

        A = new JLabel();
        A.setPreferredSize(new Dimension(220, 20));
        A.setHorizontalAlignment(JLabel.LEFT);
        A.setVerticalAlignment(JLabel.TOP);
        A.setFont(texto);
        cG.gridx = 0;
        cG.gridy = 2;
        telaIn.add(A, cG);

        X = new JLabel();
        X.setPreferredSize(new Dimension(220, 20));
        X.setHorizontalAlignment(JLabel.LEFT);
        X.setVerticalAlignment(JLabel.TOP);
        X.setFont(texto);
        cG.gridx = 0;
        cG.gridy = 1;
        telaIn.add(X, cG);

        Y = new JLabel();
        Y.setPreferredSize(new Dimension(220, 20));
        Y.setHorizontalAlignment(JLabel.LEFT);
        Y.setVerticalAlignment(JLabel.TOP);
        Y.setFont(texto);
        cG.gridx = 0;
        cG.gridy = 0;
        telaIn.add(Y, cG);

        S = new JLabel();
        S.setPreferredSize(new Dimension(20, 20));
        S.setHorizontalAlignment(JLabel.CENTER);
        S.setVerticalAlignment(JLabel.BOTTOM);
        S.setFont(texto);
        S.setForeground(laranja);
        cG.gridx = 1;
        cG.gridy = 2;
        telaIn.add(S, cG);

        Teclado.display();

    }

    public static void writeFix(){
        String aWrite = new String();
        
        BigDecimal SY;
        SY = Teclado.y;
        if(Teclado.y.precision() > 12){
            SY = SY.movePointLeft((SY.precision() - SY.scale())-1);//move o ponto para ficar em notação cinetífica
            SY = SY.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
            Y.setText("Y: " + SY + "E" + ((Teclado.y.precision() - Teclado.y.scale())-1));
        }else{
            SY = SY.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);
            Y.setText("Y: " + SY);
        }

        BigDecimal SX;
        SX = Teclado.x;
        if(Teclado.x.precision() > 12){
            SX = SX.movePointLeft((SX.precision() - SX.scale())-1);//move o ponto para ficar em notação cinetífica
            SX = SX.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
            X.setText("X: " + SX + "E" + ((Teclado.x.precision() - Teclado.x.scale())-1));
        }else{
            SX = SX.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);
            X.setText("X: " + SX);
        }

        if((Teclado.preX.size() == 1) && (Teclado.preX.get(0) == 1)){
            BigDecimal SA;
            SA = Teclado.a;
            if(Teclado.a.precision() > 12){
                SA = SA.movePointLeft((SA.precision() - SA.scale())-1);//move o ponto para ficar em notação cinetífica
                SA = SA.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
                A.setText("A: " + SA + "E" + ((Teclado.a.precision() - Teclado.a.scale())-1));
            }else{
                SA = SA.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);
                A.setText("A: " + SA);
            }
        }else{//escreve os símbos ao digitar
            ArrayList<Integer> escrita = new ArrayList<Integer>();
            for (int i = 0; i < Teclado.preX.size(); i++) {
                escrita.add(Teclado.preX.get(i));
            }
            if(escrita.get(0) == -1){
                aWrite = "-";
            }
            if(escrita.get(0) == 1){
                aWrite = "";
            }
            escrita.remove(0);
            while(escrita.size() > 0){
                switch(escrita.get(0)){
                    case 11:
                        aWrite = aWrite + ".";
                        escrita.remove(0);
                        break;

                    case 10:
                        aWrite = aWrite + "E";
                        escrita.remove(0);
                        if(escrita.get(0) == -1){
                            aWrite = aWrite + "-";
                        }
                        if(escrita.get(0) == 1){
                            aWrite = aWrite + "";
                        }
                        escrita.remove(0);
                        break;
                        
                    default:
                        aWrite = aWrite + escrita.get(0);
                        escrita.remove(0);
                        break;
                }
            }
            System.out.println("esrita: " + aWrite);
            A.setText("A: " + aWrite);
        }
        if(Teclado.modo == Teclado.Modo.LARANJA){
            S.setText("Λ");
        }else{
            S.setText("");
        }
    }
    public static void writeSci(){
        String aWrite = new String();
        
        BigDecimal SY;
        SY = Teclado.y;
        SY = SY.movePointLeft((SY.precision() - SY.scale())-1);//move o ponto para ficar em notação cinetífica
        SY = SY.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
        Y.setText("Y: " + SY + "E" + ((Teclado.y.precision() - Teclado.y.scale())-1));

        BigDecimal SX;
        SX = Teclado.x;
        SX = SX.movePointLeft((SX.precision() - SX.scale())-1);//move o ponto para ficar em notação cinetífica
        SX = SX.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
        X.setText("X: " + SX + "E" + ((Teclado.x.precision() - Teclado.x.scale())-1));

        if((Teclado.preX.size() == 1) && (Teclado.preX.get(0) == 1)){
            BigDecimal SA;
            SA = Teclado.a;
            SA = SA.movePointLeft((SA.precision() - SA.scale())-1);//move o ponto para ficar em notação cinetífica
            SA = SA.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
            A.setText("A: " + SA + "E" + ((Teclado.a.precision() - Teclado.a.scale())-1));
        }else{//escreve os símbos ao digitar
            ArrayList<Integer> escrita = new ArrayList<Integer>();
            for (int i = 0; i < Teclado.preX.size(); i++) {
                escrita.add(Teclado.preX.get(i));
            }
            if(escrita.get(0) == -1){
                aWrite = "-";
            }
            if(escrita.get(0) == 1){
                aWrite = "";
            }
            escrita.remove(0);
            while(escrita.size() > 0){
                switch(escrita.get(0)){
                    case 11:
                        aWrite = aWrite + ".";
                        escrita.remove(0);
                        break;

                    case 10:
                        aWrite = aWrite + "E";
                        escrita.remove(0);
                        if(escrita.get(0) == -1){
                            aWrite = aWrite + "-";
                        }
                        if(escrita.get(0) == 1){
                            aWrite = aWrite + "";
                        }
                        escrita.remove(0);
                        break;
                        
                    default:
                        aWrite = aWrite + escrita.get(0);
                        escrita.remove(0);
                        break;
                }
            }
            System.out.println("esrita: " + aWrite);
            A.setText("A: " + aWrite);
        }
        if(Teclado.modo == Teclado.Modo.LARANJA){
            S.setText("Λ");
        }else{
            S.setText("");
        }
    }

    public static void writeEng(){
        String aWrite = new String();
        
        BigDecimal SY;
        SY = Teclado.y;
        int YE = ((Teclado.y.precision() - Teclado.y.scale())-1);
        SY = SY.movePointLeft((SY.precision() - SY.scale())-1);//move o ponto para ficar em notação científica
        if(YE < 0){
            if(YE%3 != 0){
                SY = SY.movePointRight(3 + (YE%-3));
                YE = YE - (3 + (YE%-3));
            } else{
                SY = SY.movePointRight(YE%-3);
                YE = YE - (YE%-3);
            }
        }else{
            SY = SY.movePointRight(YE%3);
            YE = YE - (YE%3);
        }
        SY = SY.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
        Y.setText("Y: " + SY + "E" + YE);
        

        BigDecimal SX;
        SX = Teclado.x;
        int XE = ((Teclado.x.precision() - Teclado.x.scale())-1);
        SX = SX.movePointLeft((SX.precision() - SX.scale())-1);//move o ponto para ficar em notação científica
        if(XE < 0){
            if(XE%3 != 0){
                SX = SX.movePointRight(3 + (XE%-3));
                XE = XE - (3 + (XE%-3));
            } else{
                SX = SX.movePointRight(XE%-3);
                XE = XE - (XE%-3);
            }
        }else{
            SX = SX.movePointRight(XE%3);
            XE = XE - (XE%3);
        }
        SX = SX.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
        X.setText("X: " + SX + "E" + XE);

        if((Teclado.preX.size() == 1) && (Teclado.preX.get(0) == 1)){
            BigDecimal SA;
            SA = Teclado.a;
            int AE = ((Teclado.a.precision() - Teclado.a.scale())-1);
            SA = SA.movePointLeft((SA.precision() - SA.scale())-1);//move o ponto para ficar em notação científica
            if(AE < 0){
                if(AE%3 != 0){
                    SA = SA.movePointRight(3 + (AE%-3));
                    AE = AE - (3 + (AE%-3));
                } else{
                    SA = SA.movePointRight(AE%-3);
                    AE = AE - (AE%-3);
                }
            }else{
                SA = SA.movePointRight(AE%3);
                AE = AE - (AE%3);
            }
            SA = SA.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
            A.setText("A: " + SA + "E" + AE);
        }else{//escreve os símbos ao digitar
            ArrayList<Integer> escrita = new ArrayList<Integer>();
            for (int i = 0; i < Teclado.preX.size(); i++) {
                escrita.add(Teclado.preX.get(i));
            }
            if(escrita.get(0) == -1){
                aWrite = "-";
            }
            if(escrita.get(0) == 1){
                aWrite = "";
            }
            escrita.remove(0);
            while(escrita.size() > 0){
                switch(escrita.get(0)){
                    case 11:
                        aWrite = aWrite + ".";
                        escrita.remove(0);
                        break;

                    case 10:
                        aWrite = aWrite + "E";
                        escrita.remove(0);
                        if(escrita.get(0) == -1){
                            aWrite = aWrite + "-";
                        }
                        if(escrita.get(0) == 1){
                            aWrite = aWrite + "";
                        }
                        escrita.remove(0);
                        break;
                        
                    default:
                        aWrite = aWrite + escrita.get(0);
                        escrita.remove(0);
                        break;
                }
            }
            System.out.println("esrita: " + aWrite);
            A.setText("A: " + aWrite);
        }
        if(Teclado.modo == Teclado.Modo.LARANJA){
            S.setText("Λ");
        }else{
            S.setText("");
        }
    } 
}   
