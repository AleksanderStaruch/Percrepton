

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Panel extends JFrame{

        public Panel(){

            setSize(300,300);
            setLayout(new GridLayout(2,2));


            //pierwsze okno do wektorow
            JPanel wektory = new JPanel();
            wektory.setLayout(new GridLayout(4,1));
            TextArea w1 = new TextArea();
            TextArea w2 = new TextArea();
            TextArea w3 = new TextArea();
            TextArea w4 = new TextArea();

            wektory.add(w1);
            wektory.add(w2);
            wektory.add(w3);
            wektory.add(w4);

            this.add(wektory);

            //drugie okno - button
            JButton obliczbutton = new JButton("START");
            this.add(obliczbutton);

            //trzecie okno, pierwszy JtextArea
            JTextArea obliczenia = new JTextArea();
            obliczenia.setEditable(false);
            this.add(obliczenia);

            //czwartek okno, wynik
            JTextArea wynik = new JTextArea();
            wynik.setEditable(false);
            this.add(wynik);

            Border border = BorderFactory.createLineBorder(Color.BLACK);
            obliczenia.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            wynik.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SwingUtilities.updateComponentTreeUI(this);
        }

    }