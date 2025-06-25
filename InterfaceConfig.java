import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceConfig {
    
    private static GuiBeginner aGuiBeginner = new GuiBeginner();
    private static GuiIntermediary aGuiIntermediary = new GuiIntermediary();
    private static GuiPro aGuiPro = new GuiPro();
    
    
        
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
        {
            try
            {
                 new InterfaceConfig().createGUI();
            }
            catch (java.net.URISyntaxException urise)
            {
                urise.printStackTrace();
            }
        });
    }

    public void createGUI() throws java.net.URISyntaxException {
        JFrame frame = new JFrame("Choix de Niveau");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new FlowLayout());

        // Champs texte avec bordures orange
        JTextField champ1 = new JTextField(15);
        champ1.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        frame.add(new JLabel("Nom 1:"));
        frame.add(champ1);

        JTextField champ2 = new JTextField(15);
        champ2.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        frame.add(new JLabel("Nom 2:"));
        frame.add(champ2);

        // Groupe de boutons radio pour le niveau
        frame.add(new JLabel("Level:"));
        JRadioButton beginnerBtn = new JRadioButton("Beginner");
        JRadioButton intermediaryBtn = new JRadioButton("Intermediary");
        JRadioButton proBtn = new JRadioButton("Pro");

        beginnerBtn.setActionCommand("Beginner");
        intermediaryBtn.setActionCommand("Intermediary");
        proBtn.setActionCommand("Pro");

        ButtonGroup group = new ButtonGroup();
        group.add(beginnerBtn);
        group.add(intermediaryBtn);
        group.add(proBtn);

        frame.add(beginnerBtn);
        frame.add(intermediaryBtn);
        frame.add(proBtn);

        // Bouton de validation
        JButton valider = new JButton("Validate");
        valider.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        frame.add(valider);
        
        frame.setVisible(true);

        // Action du bouton
        valider.addActionListener(e -> {
            String champ1Texte = champ1.getText().trim();
            String champ2Texte = champ2.getText().trim();
            ButtonModel selection = group.getSelection();

            if (champ1Texte.isEmpty() || champ2Texte.isEmpty() || selection == null) {
                JOptionPane.showMessageDialog(frame, "Please fill in all the fields and choose your level of analysis.");
            }
            else {
                String pNiveau = selection.getActionCommand();
                       
                JFrame fenetreNiveau = new JFrame("Fenêtre " + pNiveau);
                
                if (pNiveau == "Beginner"){
                    aGuiBeginner.createGUI();
                }
                else if (pNiveau == "Intermediary"){
                    aGuiIntermediary.createGUI();
                }
                else if (pNiveau == "Pro"){
                    try
                    {
                        try
                        {
                            aGuiPro.createGUI(champ1Texte, champ2Texte);
                        }
                        catch (java.io.IOException ioe)
                        {
                            ioe.printStackTrace();
                        }
                    }
                    catch (java.net.URISyntaxException urise)
                    {
                        urise.printStackTrace();
                    }
                }
                    
                
            }
        });
        
        

    }
}