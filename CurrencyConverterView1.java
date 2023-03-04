import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

public class CurrencyConverterView1 extends JFrame implements ActionListener{
	JLabel from, to,currency,result, mensage;
    JComboBox fromSelect, toSelect;
    JTextField currencyText,resulView;
    JButton convert, exit;
    
	ExchangeRate1 exchangeRateManager = new ExchangeRate1();
	 ArrayList<String> currencyOptions = new ArrayList<>(Arrays.asList("COP","USD", "EUR", "MXN", "AED","AUD"));

    CurrencyConverterView1() {
        this.setupViewComponents();
        this.setupFrame();
        this.addComponentsToFrame();

        this.setVisible(true);
    }
	

    private void addComponentsToFrame() {
        this.add(from);
        this.add(to);
        this.add(fromSelect);
        this.add(toSelect);
        this.add(currency);
        this.add(result);
        this.add(currencyText);
        this.add(resulView);
        this.add(convert);
        this.add(exit);
        this.add(mensage);
    }

    private void setupViewComponents() {
    	mensage = new JLabel("CHALLENGE CONVERSOR DE MONEDA PARA ORACLE-ONE:");
        mensage.setBounds(20, 50, 350, 15);
        mensage.setFont(new Font("Arial", Font.BOLD, 11));
      
    	
        from = new JLabel("De:");
        from.setBounds(270, 100, 50, 15);
        fromSelect = new JComboBox<>(currencyOptions.toArray());
        fromSelect.setBounds(270, 120, 100, 30);

        to = new JLabel("Convertir a:");
        to.setBounds(270, 160, 100, 15);
        toSelect = new JComboBox<>(currencyOptions.toArray());
        toSelect.setBounds(270, 180, 100, 30);

        convert = new JButton();
        convert.setText("Convertir");
        convert.setBounds(20, 220, 100, 30);
        convert.addActionListener(this);

        exit = new JButton();
        exit.setText("Atras");
        exit.setBounds(270, 220, 100, 30);
        exit.addActionListener(this);
        
        currency = new JLabel("Cantidad a convertir:");
        currency.setBounds(20, 100, 140, 15);
        currencyText = new JTextField ();
        currencyText.setBounds(20, 120, 100, 30);

        result = new JLabel("Cantidad Convertida:");
        result.setBounds(20, 160, 140, 15);
        resulView = new JTextField ();
        resulView.setBounds(20, 180, 100, 30);
        resulView.setEditable(false);
    }

    private void setupFrame() {
        this.setTitle("Conversor de monedas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(400, 300);
        this.getContentPane().setBackground(Color.green);
        this.setResizable(false);
    }

    private int getAmount(String from, String to) {
        Boolean valid = false;
        int amount = 0;
           try {
            	String input = currencyText.getText();

        if (input == null || input.equals("0")){	
                	   
                    return amount;
                }
                
                currencyText.setText("");
               amount = Integer.parseInt(input);
                valid = true;
            } 
       catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor valido", "Error", JOptionPane.ERROR_MESSAGE);
	
       }
      
        
        return amount;
    }

    private void printResult(String exchange, Double val) {
        Formatter f = new Formatter();
        resulView.setText(String.valueOf(f.format(" %.2f", val)));
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(convert)) {
            if (fromSelect.getSelectedItem() == toSelect.getSelectedItem()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona otro tipo de modena para convertir",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int amount = this.getAmount(
                    (String) fromSelect.getSelectedItem(),
                    (String) toSelect.getSelectedItem()
            );

            if(amount != 0) {
                Double val = this.exchangeRateManager.convert(
                        (String) fromSelect.getSelectedItem(),
                        (String) toSelect.getSelectedItem(),
                        amount
                );

                this.printResult((String) toSelect.getSelectedItem(), val);
            }
        }

        if (source.equals(exit)) {
            this.dispose();         
            //if(e.getSource() == exit) {
            //    System.exit(0);
            //}
            new SelectionConverterView1();
        
    }


}
}