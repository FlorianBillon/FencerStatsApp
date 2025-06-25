import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class InterfaceAnalyse {
    
    public void createGUI(){
        JFrame frame = new JFrame("Rechercher un joueur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new FlowLayout());

        // Champs texte avec bordures orange
        JTextField champ1 = new JTextField(15);
        champ1.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        frame.add(new JLabel("Nom :"));
        frame.add(champ1);
        
        // champs affichage de texte
        JTextArea textArea = new JTextArea(20, 40);
        JScrollPane scroll = new JScrollPane(textArea);
        
        
        String cheminFichier = (GuiPro.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        File dossier = new File(cheminFichier); // Remplace par ton chemin

        
        
        // Bouton de validation
        JButton valider = new JButton("Validate");
        valider.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        frame.add(valider);
        
        frame.setVisible(true);
        textArea.setEditable(false);

    
        // Action du bouton
        valider.addActionListener(e -> {
            String champ1Texte = champ1.getText().trim();

            if (champ1Texte.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please center the name of the person you are searching.");
            }
            else {
                if (dossier.isDirectory()) {
                    File[] fichiers = dossier.listFiles();
                    if (fichiers != null) {
                        for (File fichier : fichiers) {
                            if (fichier.isFile() && fichier.getName().contains(champ1Texte)) {
                                System.out.println("Files founded : " + fichier.getName());

                                
                                try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
                                    String ligne;
                                    textArea.append(fichier.getName()+ "\n");
                                    while ((ligne = reader.readLine()) != null) {
                                        textArea.append(ligne + "\n");
                                    }
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(frame, "Erreur de lecture du fichier !");
                                }
                                                
                                System.out.println("-----------");
                            }
                        }
                    }
                }
        
            }
        });
                          
        
        frame.add(scroll, "Center");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}