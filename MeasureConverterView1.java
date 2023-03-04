import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MeasureConverterView1 extends JFrame implements ActionListener{
	JLabel from, to,unidad,result, mensage;
    JComboBox fromSelect, toSelect;
    JTextField medidasText,resulView;
    ArrayList<String> measureOptions = new ArrayList<>(Arrays.asList("CM", "M", "KM"));
    JButton convert, exit;
    MeasureManager1 measureManager = new MeasureManager1();

    MeasureConverterView1() {
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
        this.add(convert);
        this.add(exit);
        
        this.add(unidad);
        this.add(result);
        this.add(medidasText);
        this.add(resulView);    
        this.add(mensage);
    }

    private void setupViewComponents() {
       	mensage = new JLabel("CHALLENGE CONVERSOR DE MEDIDAS PARA ORACLE-ONE:");
        mensage.setBounds(20, 50, 350, 15);
        mensage.setFont(new Font("Arial", Font.BOLD, 11));
      
    	
        from = new JLabel("De:");
        from.setBounds(270, 100, 50, 15);
        fromSelect = new JComboBox<>(measureOptions.toArray());
        fromSelect.setBounds(270, 120, 100, 30);

        to = new JLabel("Convertir a:");
        to.setBounds(270, 160, 100, 15);
        toSelect = new JComboBox<>(measureOptions.toArray());
        toSelect.setBounds(270, 180, 100, 30);

        convert = new JButton();
        convert.setText("Convertir");
        convert.setBounds(20, 220, 100, 30);
        convert.addActionListener(this);

        exit = new JButton();
        exit.setText("Atras");
        exit.setBounds(270, 220, 100, 30);
        exit.addActionListener(this);
        
        unidad = new JLabel("Cantidad a convertir:");
        unidad.setBounds(20, 100, 140, 15);
        medidasText = new JTextField ();
        medidasText.setBounds(20, 120, 100, 30);

        result = new JLabel("Cantidad Convertida:");
        result.setBounds(20, 160, 140, 15);
        resulView = new JTextField ();
        resulView.setBounds(20, 180, 100, 30);
        resulView.setEditable(false);
   
    }

    private void setupFrame() {
    	 this.setTitle("Conversor de Medidas");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setLayout(null);
         this.setSize(400, 300);
         this.getContentPane().setBackground(Color.green);
         this.setResizable(false);
    }

    private int getMeasure(String from, String to) {
        Boolean valid = false;
        int amount = 0;
            try {
            	String input = medidasText.getText();

        if (input == null || input.equals("0")){	
                	   
                    return amount;
                }
                
               medidasText.setText("");
               amount = Integer.parseInt(input);
                valid = true;
            }
             
       catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor valido", "Error", JOptionPane.ERROR_MESSAGE);
	
       }
      
        
        return amount;
            
    }

    private void printResult(String measure, Double val) {
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
                        "Selecciona otro tipo de medida para convertir",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int measure = this.getMeasure(
                    (String) fromSelect.getSelectedItem(),
                    (String) toSelect.getSelectedItem()
            );

            if(measure != 0) {
                Double val = this.measureManager.convert(
                        (String) fromSelect.getSelectedItem(),
                        (String) toSelect.getSelectedItem(),
                        measure
                );

                this.printResult((String) toSelect.getSelectedItem(), val);
            }
        }

        if (source.equals(exit)) {
            this.dispose();
            new SelectionConverterView1();
        }
    }

}
