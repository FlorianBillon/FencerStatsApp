import javax.swing.JRadioButton;
import javax.swing.ButtonModel;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Décrivez votre classe Accueil ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Acceuil{
    private InterfaceAnalyse aInterfaceAnalyse;
    private InterfaceConfig aInterfaceConfig;
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() ->
            {
                try
                {
                     new Acceuil().createGUI();
                }
                catch (java.net.URISyntaxException urise)
                {
                    urise.printStackTrace();
                }
            });
        }
        
        
        public void createGUI() throws java.net.URISyntaxException {
            JFrame frame = new JFrame("Acceuil");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 250);
            frame.setLayout(new FlowLayout());
    
            // Groupe de boutons radio pour le choix
            frame.add(new JLabel("Acceuil"));
            JRadioButton EnterDataBtn = new JRadioButton("Enter data");
            JRadioButton SearchDataBtn = new JRadioButton("Search data");
            
            EnterDataBtn.setActionCommand("Enter data");
            SearchDataBtn.setActionCommand("Search data");

          
            //Défintion du groupe
            ButtonGroup groupe = new ButtonGroup();
            groupe.add(SearchDataBtn);
            groupe.add(EnterDataBtn);
            
            
            // Bouton de validation
            JButton valider = new JButton("Validate");
            valider.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));        

            
            frame.add(SearchDataBtn);
            frame.add(EnterDataBtn);
            frame.add(valider);
            frame.setVisible(true);
            
            
            //Action des boutons
            valider.addActionListener(e -> {
                
                ButtonModel selection = groupe.getSelection();
                String pChoix = selection.getActionCommand();
                
                if (pChoix == "Search data"){
                    aInterfaceAnalyse = new InterfaceAnalyse();
                    aInterfaceAnalyse.createGUI();
                }
                else if (pChoix == "Enter data"){
                    aInterfaceConfig = new InterfaceConfig();
                    try
                    {
                        aInterfaceConfig.createGUI();
                    }
                    catch (java.net.URISyntaxException urise)
                    {
                        urise.printStackTrace();
                    }
                }
            });
        }
    }