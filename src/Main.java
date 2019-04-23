import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    public Main(){
        setSize(400,500);
        setLayout(new BorderLayout());
        Perceptron perceptron= new Perceptron("perceptron.data.txt");

        JTextPane xField = new JTextPane();xField.setText("3");
        JTextPane yField = new JTextPane();yField.setText("0.1");
        JButton refresh = new JButton("Refresh");

        JPanel myPanel = new JPanel();this.add(myPanel,BorderLayout.PAGE_START);
        myPanel.add(new JLabel("Teta:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("n:"));
        myPanel.add(yField);
        myPanel.add(refresh);

        double teta=Double.parseDouble(xField.getText());
        double n=Double.parseDouble(yField.getText());
        perceptron.learn(teta,n);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        this.add(panel,BorderLayout.PAGE_END);

        JPanel wektory = new JPanel();
        wektory.setLayout(new GridLayout(1,5));
        panel.add(wektory);

        TextField w1 = new TextField();wektory.add(w1);
        TextField w2 = new TextField();wektory.add(w2);
        TextField w3 = new TextField();wektory.add(w3);
        TextField w4 = new TextField();wektory.add(w4);
        JButton obliczbutton = new JButton("OK");wektory.add(obliczbutton);

        JLabel wynik=new JLabel();
        wynik.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(wynik);

        obliczbutton.addActionListener(e -> {
            List <String> list=new ArrayList<>();
            list.add(w1.getText());
            list.add(w2.getText());
            list.add(w3.getText());
            list.add(w4.getText());

            wynik.setText(perceptron.whichFlower(list));
        });

        JTextArea text = new JTextArea();
        text.append(perceptron.checkDataTestAccuracy("perceptron.data.test.txt"));
        text.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(text);
        this.add(jScrollPane,BorderLayout.CENTER);

        refresh.addActionListener(e -> {
            double T=Double.parseDouble(xField.getText());
            double N=Double.parseDouble(yField.getText());
            perceptron.learn(T,N);
            text.append(perceptron.checkDataTestAccuracy("perceptron.data.test.txt"));

        });

        this.setTitle("Perceptron");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->new Main());
    }





}