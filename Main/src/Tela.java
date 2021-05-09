import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JLabel;



public class Tela extends JPanel  {
    Border exterior = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createRaisedBevelBorder());
    Border borderRaised = BorderFactory.createRaisedBevelBorder();
    GridBagConstraints cG = new GridBagConstraints();
    Font texto = new Font("Consolas", Font.PLAIN, 20);
    Color cinza = new Color(24, 27, 31);
    Color cinzaClaro = new Color(53, 55, 61);
    Color Verde = new Color(55, 112, 55);
    Color laranja = new Color(199, 92, 16);
    static JLabel X,Y,Z, S, S2, S3;

    public Tela(){ 
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

        X = new JLabel();
        X.setPreferredSize(new Dimension(220, 20));
        X.setHorizontalAlignment(JLabel.LEFT);
        X.setVerticalAlignment(JLabel.TOP);
        X.setFont(texto);
        cG.gridx = 0;
        cG.gridy = 2;
        telaIn.add(X, cG);

        Y = new JLabel();
        Y.setPreferredSize(new Dimension(220, 20));
        Y.setHorizontalAlignment(JLabel.LEFT);
        Y.setVerticalAlignment(JLabel.TOP);
        Y.setFont(texto);
        cG.gridx = 0;
        cG.gridy = 1;
        telaIn.add(Y, cG);

        Z = new JLabel();
        Z.setPreferredSize(new Dimension(220, 20));
        Z.setHorizontalAlignment(JLabel.LEFT);
        Z.setVerticalAlignment(JLabel.TOP);
        Z.setFont(texto);
        cG.gridx = 0;
        cG.gridy = 0;
        telaIn.add(Z, cG);

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
        BigDecimal SZ;
        SZ = Teclado.z;
        if((((Teclado.z.scale()*-1) + Teclado.Ndecimal) > 15)||((Teclado.z.scale()) > Teclado.Ndecimal)){
            SZ = SZ.movePointLeft((SZ.precision() - SZ.scale())-1);//move o ponto para ficar em notação cinetífica
            SZ = SZ.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
            Z.setText("Z: " + SZ + "E" + ((Teclado.z.precision() - Teclado.z.scale())-1));
        }else{
            SZ = SZ.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);
            Z.setText("Z: " + SZ);
        }

        BigDecimal SY;
        SY = Teclado.y;
        if((((Teclado.y.scale()*-1) + Teclado.Ndecimal) > 15)||((Teclado.y.scale()) > Teclado.Ndecimal)){
            SY = SY.movePointLeft((SY.precision() - SY.scale())-1);//move o ponto para ficar em notação cinetífica
            SY = SY.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
            Y.setText("Y: " + SY + "E" + ((Teclado.y.precision() - Teclado.y.scale())-1));
        }else{
            SY = SY.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);
            Y.setText("Y: " + SY);
        }

        if((Teclado.preX.size() == 1)){
            BigDecimal SX;
            SX = Teclado.x;
            while(SX.compareTo(SX.setScale(SX.scale()-1)) == 0){
                SX = SX.setScale(SX.scale()-1);
            }
            if((((Teclado.x.scale()*-1) + Teclado.Ndecimal) > 15)||((Teclado.x.scale()) > Teclado.Ndecimal)){
                SX = SX.movePointLeft((SX.precision() - SX.scale())-1);//move o ponto para ficar em notação cinetífica
                SX = SX.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
                X.setText("X: " + SX + "E" + ((Teclado.x.precision() - Teclado.x.scale())-1));
            }else{
                SX = SX.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);
                X.setText("X: " + SX);
            }
        }else{//escreve os símbos ao digitar
            stringToX();
        }
        if(Teclado.modo == Teclado.Modo.LARANJA){
            S.setText("Λ");
        }else{
            S.setText("");
        }
    }
    public static void writeSci(){
        BigDecimal SZ;
        SZ = Teclado.w;
        SZ = SZ.movePointLeft((SZ.precision() - SZ.scale())-1);//move o ponto para ficar em notação cinetífica
        SZ = SZ.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
        Z.setText("Z: " + SZ + "E" + ((Teclado.z.precision() - Teclado.z.scale())-1));

        BigDecimal SY;
        SY = Teclado.y;
        SY = SY.movePointLeft((SY.precision() - SY.scale())-1);//move o ponto para ficar em notação cinetífica
        SY = SY.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
        Y.setText("Y: " + SY + "E" + ((Teclado.y.precision() - Teclado.y.scale())-1));

        if((Teclado.preX.size() == 1)){
            BigDecimal SX;
            SX = Teclado.x;
            SX = SX.movePointLeft((SX.precision() - SX.scale())-1);//move o ponto para ficar em notação cinetífica
            SX = SX.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
            X.setText("X: " + SX + "E" + ((Teclado.x.precision() - Teclado.x.scale())-1));
        }else{//escreve os símbos ao digitar
            stringToX();
        }
        if(Teclado.modo == Teclado.Modo.LARANJA){
            S.setText("Λ");
        }else{
            S.setText("");
        }
    }

    public static void writeEng(){
        BigDecimal SZ;
        SZ = Teclado.z;
        int ZE = ((Teclado.z.precision() - Teclado.z.scale())-1);
        SZ = SZ.movePointLeft((SZ.precision() - SZ.scale())-1);//move o ponto para ficar em notação científica
        if(ZE < 0){
            if(ZE%3 != 0){
                SZ = SZ.movePointRight(3 + (ZE%-3));
                ZE = ZE - (3 + (ZE%-3));
            } else{
                SZ = SZ.movePointRight(ZE%-3);
                ZE = ZE - (ZE%-3);
            }
        }else{
            SZ = SZ.movePointRight(ZE%3);
            ZE = ZE - (ZE%3);
        }
        SZ = SZ.setScale(Teclado.Ndecimal, RoundingMode.HALF_EVEN);// determina a quantidade de dígitos depois da vírgula
        Z.setText("Z: " + SZ + "E" + ZE);
        

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

        if((Teclado.preX.size() == 1)){
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
        }else{//escreve os símbos ao digitar
            stringToX();
        }
        if(Teclado.modo == Teclado.Modo.LARANJA){
            S.setText("Λ");
        }else{
            S.setText("");
        }
    } 
    static void stringToX(){ //apenas escre s dígitos, sem criar um valor numérico 
        String xWrite = new String();
        xWrite = "";
        for (int i = 0; i < Teclado.preX.size(); i++) {
            if(Teclado.preX.get(i) != '+'){
                xWrite = xWrite + Teclado.preX.get(i);
            }
        }          
        System.out.println("escrita: " + xWrite);
        X.setText("X: " + xWrite);
    }
}   
