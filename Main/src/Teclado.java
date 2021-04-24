import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener {
    Border borderRaised = BorderFactory.createRaisedBevelBorder();
    GridBagConstraints cT = new GridBagConstraints();
    GridBagConstraints cNF = new GridBagConstraints();
    Font fPrincipal = new Font("monospace", Font.PLAIN, 9);
    Font fPrincipal2 = new Font("monospace", Font.PLAIN, 8);
    Color cinza2 = new Color(13, 16, 18);
    Color cinza = new Color(24, 27, 31);
    Color laranja = new Color(199, 92, 16);
    JPanel funcoes;
    JPanel numeros;
    JButton b7, b8, b9, bd, b4, b5, b6, bm, b1, b2, b3, bs, b0, bp, bf, ba, bl, be;
    JButton bent, bxy, bmm, bnot, bcle, bsto, brcl, brdown, bsin, bcos, btan, bsum, binv, bsqrt, bsqr, bpot, blog;
    static ArrayList<Character> preX;
    static BigDecimal x, y, z, w, lx;
    static int Ndecimal;
    
    BigDecimal[] storage = new BigDecimal[100];

    enum Modo {
        NORMAL,
        LARANJA,
        DISPLAY,
        STORAGE
    }
    static Modo modo = Modo.NORMAL;  

    enum Display {
        FIX,
        SCI,
        ENG
    }
    static Display display = Display.FIX;



    Teclado() {
        setLayout(new GridBagLayout());

        funcoes = new JPanel();
        funcoes.setBackground(cinza);
        funcoes.setLayout(new GridBagLayout());
        cT.gridx = 0;
        cT.gridy = 0;
        add(funcoes, cT);

        numeros = new JPanel();
        numeros.setBackground(cinza);
        numeros.setLayout(new GridBagLayout());
        cT.gridx = 0;
        cT.gridy = 1;
        add(numeros, cT);

        // início de colocação dos números
        cNF.fill = GridBagConstraints.BOTH;
        cNF.insets = new Insets(0, 10, 10, 10);
        cNF.anchor = GridBagConstraints.CENTER;

        b7 = new JButton("7");
        gridNumeros(1, 1, 1, 1, b7);

        b8 = new JButton("8");
        gridNumeros(2, 1, 1, 1, b8);

        b9 = new JButton("9");
        gridNumeros(3, 1, 1, 1, b9);

        bd = new JButton(":");
        gridNumeros(4, 1, 1, 1, bd);
        // -----------------------------
        b4 = new JButton("4");
        gridNumeros(1, 3, 1, 1, b4);

        b5 = new JButton("5");
        gridNumeros(2, 3, 1, 1, b5);

        b6 = new JButton("6");
        gridNumeros(3, 3, 1, 1, b6);

        bm = new JButton("X");
        gridNumeros(4, 3, 1, 1, bm);
        // -----------------------------
        b1 = new JButton("1");
        gridNumeros(1, 5, 1, 1, b1);

        b2 = new JButton("2");
        gridNumeros(2, 5, 1, 1, b2);

        b3 = new JButton("3");
        gridNumeros(3, 5, 1, 1, b3);

        bs = new JButton("-");
        gridNumeros(4, 5, 1, 1, bs);
        numeros.add(bs, cNF);
        // -----------------------------
        b0 = new JButton("0");
        gridNumeros(1, 7, 1, 1, b0);

        bp = new JButton(".");
        gridNumeros(2, 7, 1, 1, bp);

        bf = new JButton("F(x)");
        gridNumeros(3, 7, 1, 1, bf);

        ba = new JButton("+");
        gridNumeros(4, 7, 1, 1, ba);
        // -----------------------------
        bl = new JButton(" ");
        gridNumeros(0, 5, 1, 1, bl);
        bl.setBackground(laranja);

        be = new JButton("EXIT");
        gridNumeros(0, 7, 1, 1, be);

        // início de colocação dAs labels do números
        cNF.fill = GridBagConstraints.BOTH;
        cNF.insets = new Insets(0, 0, 0, 0);
        cNF.anchor = GridBagConstraints.CENTER;

        setLabelNumeros(0, 6, 1, 1, new JLabel("OFF"));
        setLabelNumeros(2, 6, 1, 1, new JLabel("CONVERT"));
        setLabelNumeros(3, 6, 1, 1, new JLabel("PRINT"));

        setLabelNumeros(0, 4, 1, 1, new JLabel(""));

        setLabelNumeros(0, 2, 1, 1, new JLabel(""));

        setLabelNumeros(0, 0, 1, 1, new JLabel(""));

        // início de colocação das funções
        cNF.fill = GridBagConstraints.BOTH;
        cNF.insets = new Insets(0, 10, 10, 10);
        cNF.anchor = GridBagConstraints.CENTER;

        bent = new JButton("ENTER");
        gridfuncoes(0, 5, 2, 1, bent);

        bxy = new JButton("X<>Y");
        gridfuncoes(2, 5, 1, 1, bxy);

        bmm = new JButton("+/-");
        gridfuncoes(3, 5, 1, 1, bmm);
        funcoes.add(bmm, cNF);

        bnot = new JButton("E");
        gridfuncoes(4, 5, 1, 1, bnot);

        bcle = new JButton("<-");
        gridfuncoes(5, 5, 1, 1, bcle);
        // -----------------------------
        bsto = new JButton("STO");
        gridfuncoes(0, 3, 1, 1, bsto);

        brcl = new JButton("RCL");
        gridfuncoes(1, 3, 1, 1, brcl);

        brdown = new JButton("R");
        gridfuncoes(2, 3, 1, 1, brdown);

        bsin = new JButton("SIN");
        gridfuncoes(3, 3, 1, 1, bsin);

        bcos = new JButton("COS");
        gridfuncoes(4, 3, 1, 1, bcos);

        btan = new JButton("TAN");
        gridfuncoes(5, 3, 1, 1, btan);
        // -----------------------------
        bsum = new JButton("SUM");
        gridfuncoes(0, 1, 1, 1, bsum);

        binv = new JButton("1/x");
        gridfuncoes(1, 1, 1, 1, binv);

        bsqrt = new JButton("SQRT");
        gridfuncoes(2, 1, 1, 1, bsqrt);

        bsqr = new JButton("X^2");
        gridfuncoes(3, 1, 1, 1, bsqr);

        bpot = new JButton("Y^X");
        gridfuncoes(4, 1, 1, 1, bpot);

        blog = new JButton("LOG");
        gridfuncoes(5, 1, 1, 1, blog);

        // início de colocação dAs labels do funções
        cNF.fill = GridBagConstraints.BOTH;
        cNF.insets = new Insets(0, 0, 0, 0);
        cNF.anchor = GridBagConstraints.CENTER;

        setLabelFuncoes(2, 4, 1, 1, new JLabel("LAST X"));
        setLabelFuncoes(3, 4, 1, 1, new JLabel("MODES"));
        setLabelFuncoes(4, 4, 1, 1, new JLabel("DISP"));
        setLabelFuncoes(5, 4, 1, 1, new JLabel("CLEAR"));

        setLabelFuncoes(1, 2, 1, 1, new JLabel("%"));
        setLabelFuncoes(2, 2, 1, 1, new JLabel("PI"));
        setLabelFuncoes(3, 2, 1, 1, new JLabel("ASIN"));
        setLabelFuncoes(4, 2, 1, 1, new JLabel("ACOS"));
        setLabelFuncoes(5, 2, 1, 1, new JLabel("ATAN"));

        setLabelFuncoes(0, 0, 1, 1, new JLabel(""));

        // ================================================================

        x = BigDecimal.valueOf(0);
        y = BigDecimal.valueOf(0);
        z = BigDecimal.valueOf(0);
        w = BigDecimal.valueOf(0);
        lx = BigDecimal.valueOf(0);

        preX = new ArrayList<Character>();
        preX.add('+');
        Ndecimal = 3;

        for(int i = 0; i < storage.length; i++){
            storage[i] = BigDecimal.ZERO;
        }
    }

    void gridNumeros(int x, int y, int dx, int dy, JButton button) {
        cNF.gridx = x;
        cNF.gridy = y;
        cNF.gridwidth = dx;
        cNF.gridheight = dy;

        button.setBackground(cinza2);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setPreferredSize(new Dimension(40, 20));
        button.setFont(fPrincipal);
        button.setForeground(Color.WHITE);
        button.setBorder(borderRaised);
        button.setFocusable(false);
        numeros.add(button, cNF);
        button.addActionListener(this);
    }

    void gridfuncoes(int x, int y, int dx, int dy, JButton button) {
        cNF.gridx = x;
        cNF.gridy = y;
        cNF.gridwidth = dx;
        cNF.gridheight = dy;

        button.setBackground(cinza2);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setPreferredSize(new Dimension(30, 20));
        button.setFont(fPrincipal);
        button.setForeground(Color.WHITE);
        button.setBorder(borderRaised);
        button.setFocusable(false);
        funcoes.add(button, cNF);
        button.addActionListener(this);
    }

    void setLabelNumeros(int x, int y, int dx, int dy, JLabel label) {
        cNF.gridx = x;
        cNF.gridy = y;
        cNF.gridwidth = dx;
        cNF.gridheight = dy;

        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(30, 10));
        label.setFont(fPrincipal2);
        label.setForeground(laranja);
        numeros.add(label, cNF);
    }

    void setLabelFuncoes(int x, int y, int dx, int dy, JLabel label) {
        cNF.gridx = x;
        cNF.gridy = y;
        cNF.gridwidth = dx;
        cNF.gridheight = dy;

        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(30, 10));
        label.setFont(fPrincipal2);
        label.setForeground(laranja);
        funcoes.add(label, cNF);
    }

    void aparecer() {
        String preBigDecimal = new String();
        preBigDecimal = "";
        for (int i = 0; i < preX.size(); i++) {
            preBigDecimal = preBigDecimal + preX.get(i);
        }
        if (preX.get(preX.size()-1) == '+' || preX.get(preX.size()-1) == '-'){
            preBigDecimal = preBigDecimal + "0";
        }
        if (preX.size() > 1 && preX.get(1) == '.'){
            preBigDecimal = "0" + preBigDecimal;
        }
        System.out.println(preBigDecimal);
        x = new BigDecimal(preBigDecimal);
    }
    


    void xtoy() {
        BigDecimal t;
        lx = x;
        t = x;
        x = y;
        y = t;
    }

    void changesig() {
        if(preX.size() == 1){
            x = x.multiply(new BigDecimal(-1));
            return;
        }
        for (int i = (preX.size() - 1); i >= 0; i--) {
            if(preX.get(i) == '+'){
                preX.set(i, '-');
                aparecer();
                return;
            }
            if(preX.get(i) == '-'){
                preX.set(i, '+');
                aparecer();
                return;
            }
        }
    }

    void point() {
        for (int i = 0; i < preX.size(); i++) {
            if ((preX.get(i) == '.') || (preX.get(i) == 'E')) {
                return;
            }
        }
        preX.add('.');
    }

    void notation() {//não funciona
        for (int i = 0; i < preX.size(); i++) {
            if (preX.get(i) == 'e') {
                return;
            }
        }
        if(preX.size() == 1){
            preX.add('1');
        }
        preX.add('e');
        preX.add('+');
    }

    void clean (){
        if (preX.size() > 1) {
            if(((preX.get(preX.size() - 1)) == '+') && ((preX.get(preX.size() - 2)) == 'E')){
                preX.remove((preX.size() - 1));
                preX.remove((preX.size() - 1));
                return;
            }
            if(((preX.get(preX.size() - 1)) == '-') && ((preX.get(preX.size() - 2)) == 'E')){
                preX.set((preX.size() - 1), '+');
                return;
            }
            preX.remove((preX.size() - 1));
        }

    }

    void RDown(){
        BigDecimal i = x;
        lx = x;
        x = y;
        y = z;
        z = w;
        w = i;
    }

    void recal(){
        if((x.intValue() >= 0) && (x.intValue() < 100)){
            lx = x;
            x = storage[x.intValue()];
            preX.clear();
            preX.add('+');
            display();
        }else{
            Tela.X.setText("Invalid number");
        }
    }

    void lastX(){
        x = lx;
    }

    void add(){
        lx = x;
        x = x.add(y);
        y = z;
        z = w;
    }

    void subtract(){
        lx = x;
        x = y.subtract(x);
        y = z;
        z = w;
    }

    void multiply(){
        lx = x;
        x = x.multiply(y);
        y = z;
        z = w;
    }

    void divide(){
        try{
            lx = x;
            x = y.divide(x);
            y = z;
            z = w;
            display();
        }catch (Exception e){
            if(e.getMessage() == "Division by zero"){
                Tela.X.setText("Cannot divide by 0");
            }else{
                System.out.println(e.getMessage());
            }
        }   
    }

    void inverse(){
        try{
            lx = x;
            x = (BigDecimal.ONE).divide(x);
            x = x.setScale(16, RoundingMode.HALF_EVEN);
            display();
        }catch (Exception e){
            if(e.getMessage() == "Division by zero"){
                Tela.X.setText("Cannot divide by 0");
            }else{
                System.out.println(e.getMessage());
            }
        }  
    }

    void sqrt(){
        try{
            lx = x;
            x = x.sqrt(new MathContext(16));
            display();
        }catch (Exception e){
            if(e.getMessage() == "Attempted square root of negative BigDecimal"){
                Tela.X.setText("Compex value");
            }else{
                System.out.println(e.getMessage());
            }
        }
    }

    void sqr(){
        lx = x;
        x = x.pow(2);
    }

    BigDecimal sin(BigDecimal tx){
        BigDecimal sin = BigDecimal.ZERO;
        sin = sin.setScale(16, RoundingMode.HALF_EVEN);


        for(int i = 1; i<=30; i = i+2){
            BigDecimal pow = BigDecimal.ZERO;
            pow = pow.setScale(16, RoundingMode.HALF_EVEN);

            BigDecimal fac = BigDecimal.ONE;
            fac = fac.setScale(16, RoundingMode.HALF_EVEN);

            BigDecimal sig = BigDecimal.ZERO;
            sig = sig.setScale(16, RoundingMode.HALF_EVEN);

            BigDecimal val = BigDecimal.ZERO;
            val = val.setScale(16, RoundingMode.HALF_EVEN);

            pow = tx.pow(i);
            sig = (new BigDecimal(-1)).pow((i-1)/2);
            
            for(int f = 1; f<=i; f++){
                fac = fac.multiply(new BigDecimal(f));
            }

            val = sig.multiply(pow);
            val = val.setScale(16, RoundingMode.HALF_EVEN);
            val = val.divide(fac, RoundingMode.HALF_EVEN);
            sin = sin.add(val);
        }
        return sin;
    }

    BigDecimal cos(BigDecimal tx){
        BigDecimal cos = BigDecimal.ONE;
        cos = cos.setScale(16, RoundingMode.HALF_EVEN);


        for(int i = 2; i<=30; i = i+2){
            BigDecimal pow = BigDecimal.ZERO;
            pow = pow.setScale(16, RoundingMode.HALF_EVEN);

            BigDecimal fac = BigDecimal.ONE;
            fac = fac.setScale(16, RoundingMode.HALF_EVEN);

            BigDecimal sig = BigDecimal.ZERO;
            sig = sig.setScale(16, RoundingMode.HALF_EVEN);

            BigDecimal val = BigDecimal.ZERO;
            val = val.setScale(16, RoundingMode.HALF_EVEN);

            pow = tx.pow(i);
            sig = (new BigDecimal(-1)).pow((i)/2);
            
            for(int f = 1; f<=i; f++){
                fac = fac.multiply(new BigDecimal(f));
            }
            
            val = sig.multiply(pow);
            val = val.setScale(16, RoundingMode.HALF_EVEN);
            val = val.divide(fac, RoundingMode.HALF_EVEN);
            cos = cos.add(val);
        }
        return cos;

    }
    
    BigDecimal tan(BigDecimal tx){
        BigDecimal cos = BigDecimal.ZERO;
        cos = cos.setScale(16, RoundingMode.HALF_EVEN);
        cos = cos(tx);

        BigDecimal sin = BigDecimal.ZERO;
        sin = sin.setScale(16, RoundingMode.HALF_EVEN);
        sin = sin(tx);

        BigDecimal tan = BigDecimal.ZERO;
        tan = tan.setScale(16, RoundingMode.HALF_EVEN);
        tan = sin.divide(cos, RoundingMode.HALF_EVEN);
        return tan;
    }

    BigDecimal arcsin(BigDecimal tx){
        BigDecimal arcsin = BigDecimal.ZERO;
        arcsin = arcsin.setScale(16, RoundingMode.HALF_EVEN);

        BigDecimal x1 = new BigDecimal("3.14159265358979323846");
        x1 = x1.divide(new BigDecimal(4), RoundingMode.HALF_EVEN);
        x1 = x1.setScale(16, RoundingMode.HALF_EVEN);

        //f(x1) = sin(x1) - x
        //f'(x1) = cos(x1)
        for(int i = 1 ; i<=30; i++){
            BigDecimal val1 = sin(x1).subtract(tx);
            BigDecimal val2 = cos(x1);
            x1 = x1.subtract(val1.divide((val2), RoundingMode.HALF_EVEN));
            System.out.println(x1);

        }
        return x1;
    }

    BigDecimal arccos(BigDecimal tx){
        BigDecimal arcsin = arcsin(tx);
        BigDecimal arccos = new BigDecimal("3.14159265358979323846");
        arccos = arccos.divide(new BigDecimal(2), RoundingMode.HALF_EVEN);
        arccos = arcsin.subtract(arccos);
        return arccos;
    }
    
    BigDecimal arctan(BigDecimal tx){
        BigDecimal arctan = BigDecimal.ZERO;
        arctan = arctan.setScale(16, RoundingMode.HALF_EVEN);

        BigDecimal x1 = new BigDecimal("3.14159265358979323846");
        x1 = x1.divide(new BigDecimal(4), RoundingMode.HALF_EVEN);
        x1 = x1.setScale(16, RoundingMode.HALF_EVEN);

        //f(x1) = tan(x1) - x
        //f'(x1) = sec^2(x) = 1/cos^2(x) = 2/(cos(2x) + 1)
        for(int i = 1 ; i<=30; i++){        
            BigDecimal val1 = tan(x1).subtract(tx);
            BigDecimal val2 = cos(x1.multiply(new BigDecimal(2)));
            val2 = val2.add(BigDecimal.ONE);
            val2 = new BigDecimal(2).divide(val2, RoundingMode.HALF_EVEN);
            x1 = x1.subtract(val1.divide((val2), RoundingMode.HALF_EVEN));
            System.out.println(x1);

        }
        return x1;
    }
    

        BigDecimal GX = BigDecimal.ZERO;
        BigDecimal GY = BigDecimal.ZERO;
        BigDecimal GZ = BigDecimal.ZERO;
    public void actionPerformed(ActionEvent e) {
        switch(modo){
            case NORMAL:
                if(e.getSource() == bl){
                    modo = Modo.LARANJA;
                }
                numwrite(e);
                if (e.getSource() == bp) {
                    point();
                    aparecer();
                }
                if (e.getSource() == bent) {
                    lx = x;
                    w = z;
                    z = y;
                    y = x;
                    preX.clear();
                    preX.add('+');
                }
                if (e.getSource() == bxy) {
                    xtoy();
                    preX.clear();
                    preX.add('+');
                }
                if (e.getSource() == bmm) {
                    changesig();
                }
                if (e.getSource() == bnot) {
                    notation();
                    aparecer();
                }
                if (e.getSource() == bcle) {
                    clean ();
                    aparecer();
                }
                if (e.getSource() == brdown){
                    RDown();
                }
                if (e.getSource() == bsto){
                    modo = Modo.STORAGE;
                    GX = x;
                    GY = y;
                    y = x;
                    x = BigDecimal.ZERO;
                    preX.clear();
                    preX.add('+');
                    storageSet();
                    return;
                }
                if (e.getSource() == brcl){
                    recal();
                    break;
                }
                if (e.getSource() == bd){
                    divide();
                    preX.clear();
                    preX.add('+');
                }
                if (e.getSource() == bm){
                    multiply();
                    preX.clear();
                    preX.add('+');
                }
                if (e.getSource() == bs){
                    subtract();
                    preX.clear();
                    preX.add('+');
                }
                if (e.getSource() == ba){
                    add();
                    preX.clear();
                    preX.add('+');
                }
                if (e.getSource() == binv){
                    inverse();
                    preX.clear();
                    preX.add('+');
                    break;
                }
                if (e.getSource() == bsqrt){
                    sqrt();
                    preX.clear();
                    preX.add('+');
                    break;
                }
                if (e.getSource() == bsqr){
                    sqr();
                    preX.clear();
                    preX.add('+');
                }
                if (e.getSource() == bpot){
                }
                if (e.getSource() == blog){
                }
                if (e.getSource() == bsin){
                    lx = x;
                    x = sin(x);
                    preX.clear();
                    preX.add('+');
                }
                if (e.getSource() == bcos){
                    lx = x;
                    x = cos(x);
                    preX.clear();
                    preX.add('+');
                }
                if (e.getSource() == btan){
                    lx = x;
                    x = tan(x);
                    preX.clear();
                    preX.add('+');
                }
                
                display();
                break;
            case LARANJA:
                if(e.getSource() == bl){
                    modo = Modo.NORMAL;
                }
                if(e.getSource() == be){
                    System.exit(0);
                }
                if (e.getSource() == bnot) {
                    modo = Modo.DISPLAY;
                    x = BigDecimal.ZERO;
                    displaySet();
                    return;
                }
                if(e.getSource() == bxy){
                    lastX();
                    preX.clear();
                    preX.add('+');
                    modo = Modo.NORMAL;
                }
                if (e.getSource() == bsin){
                    lx = x;
                    x = arcsin(x);
                    preX.clear();
                    preX.add('+');
                    modo = Modo.NORMAL;
                }
                if (e.getSource() == bcos){
                    lx = x;
                    x = arccos(x);
                    preX.clear();
                    preX.add('+');
                    modo = Modo.NORMAL;
                }
                if (e.getSource() == btan){
                    lx = x;
                    x = arctan(x);
                    preX.clear();
                    preX.add('+');
                    modo = Modo.NORMAL;
                }
                if(e.getSource() == brdown){
                    lx = x;
                    x = new BigDecimal("3.14159265358979323846");
                    x = x.setScale(20, RoundingMode.HALF_EVEN);
                    preX.clear();
                    preX.add('+');
                    display();
                    modo = Modo.NORMAL;
                }
                display();
                break;
            case DISPLAY:
                numwrite(e);
                if (e.getSource() == be) {
                    modo = Modo.NORMAL;
                    display();
                    break;
                }
                if (e.getSource() == bsum) {
                    display = Display.FIX;
                    Tela.Y.setText("Fix");
                }
                if (e.getSource() == binv) {
                    display = Display.SCI;
                    Tela.Y.setText("Scientific");
                }
                if (e.getSource() == bsqrt) {
                    display = Display.ENG;
                    Tela.Y.setText("Engineer");
                }
                if (e.getSource() == bcle) {
                    clean ();
                    aparecer();
                }
                if (e.getSource() == bent) {
                    if((x.intValue() > 0) && (x.intValue() < 12)){
                        Ndecimal = x.intValue();
                        modo = Modo.NORMAL;
                        display();
                        return;
                    }
                }
                displaySet();
                break;
            case STORAGE:
                numwrite(e);
                if (e.getSource() == be){
                    x = GX;
                    y = GY;
                    aparecer();
                    modo = Modo.NORMAL;
                    display();
                    return;
                }
                if (e.getSource() == bcle) {
                    clean ();
                    aparecer();
                    storageSet();
                }
                if (e.getSource() == bent) {
                    if((x.intValue() >= 0) && (x.intValue() < 100)){
                        modo = Modo.NORMAL;
                        storage[x.intValue()] = y;
                        x = GX;
                        y = GY;
                        aparecer();
                        modo = Modo.NORMAL;
                        display();
                        return;
                    }
                }
                storageSet();
                break;    
        }
        

        System.out.println("PreX: " + preX);
        System.out.println("-------");
        System.out.println("w: "+ w);
        System.out.println("z: "+ z);
        System.out.println("y: "+ y);
        System.out.println("x: "+ x);
        System.out.println("-------");
        
        
    }

    void numwrite(ActionEvent e){
        if (e.getSource() == b0) {
            preX.add('0');
            aparecer();
        }
        if (e.getSource() == b1) {
            preX.add('1');
            aparecer();
        }
        if (e.getSource() == b2) {
            preX.add('2');
            aparecer();
        }
        if (e.getSource() == b3) {
            preX.add('3');
            aparecer();
        }
        if (e.getSource() == b4) {
            preX.add('4');
            aparecer();
        }
        if (e.getSource() == b5) {
            preX.add('5');
            aparecer();
        }
        if (e.getSource() == b6) {
            preX.add('6');
            aparecer();
        }
        if (e.getSource() == b7) {
            preX.add('7');
            aparecer();
        }
        if (e.getSource() == b8) {
            preX.add('8');
            aparecer();
        }
        if (e.getSource() == b9) {
            preX.add('9');
            aparecer();
        }
    }

    void storageSet(){
        display();
        Tela.Z.setText("STORAGE");
        Tela.Y.setText("X:" +  Tela.Y.getText().substring(2));
        Tela.X.setText("I:" +  Tela.X.getText().substring(2));
    }

    void displaySet(){
        switch(display){
            case FIX:
                Tela.writeFix();
                Tela.Y.setText("Fix");
                break;
            case SCI:
                Tela.writeSci();
                Tela.Y.setText("Scientific");
                break;
            case ENG:
                Tela.writeEng();
                Tela.Y.setText("Engineer");
                break;
        }
        Tela.Z.setText("FIX|SCI|ENG");
    }

    static void display(){
        switch(display){
            case FIX:
                Tela.writeFix();
                break;
            case SCI:
                Tela.writeSci();
                break;
            case ENG:
                Tela.writeEng();
                break;
        }
    }
}