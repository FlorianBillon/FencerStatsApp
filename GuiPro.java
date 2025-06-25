import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import javax.swing.*;
import java.awt.*;

public class GuiPro {
    // Variables globales pour enregistrer les deux sélections
    String[] doubleTouchSelections = new String[2];
    int selectionCount = 0;
    String[] selections = new String[2];
    // [0] = première action (droite), [1] = deuxième action (gauche)
    boolean doubleTouchMode = false; // indique si la 2ᵉ sélection est activée
    JButton boutonSelectionDroite = null;
    JButton boutonSelectionGauche = null;

    boolean premiereSelectionFait = false;
    boolean droiteDéjàChoisie = false;
    boolean gaucheDéjàChoisie = false;
    
    String[] zones = new String[2];            // zones associées (1 à 6)
    boolean premiereSelectionFaite = false;    // indique si une sélection est faite
    boolean droiteChoisie = false;
    boolean gaucheChoisie = false;
    boolean premiereActionFaite = false;
    
    int indice=0;
    


    public void createGUI(String nom1 , String nom2) throws java.net.URISyntaxException, java.net.URISyntaxException, java.net.URISyntaxException, java.net.URISyntaxException, java.net.URISyntaxException, java.net.URISyntaxException, java.net.URISyntaxException, IOException, IOException, java.net.URISyntaxException, IOException {
    
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Interface d'Escrime");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 450);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
    
            // Joueurs
            JLabel nameRed = new JLabel(nom1, SwingConstants.CENTER);
            nameRed.setForeground(Color.RED);
            nameRed.setBounds(100, 20, 100, 20);
            frame.add(nameRed);
    
            JLabel nameGreen = new JLabel(nom2, SwingConstants.CENTER);
            nameGreen.setForeground(new Color(0, 128, 0));
            nameGreen.setBounds(680, 20, 100, 20);
            frame.add(nameGreen);
    
            JLabel redFencer = new JLabel("🤺");
            redFencer.setFont(new Font("SansSerif", Font.PLAIN, 40));
            redFencer.setForeground(Color.RED);
            redFencer.setBounds(120, 50, 60, 50);
            frame.add(redFencer);
    
            JLabel greenFencer = new JLabel("🤺");
            greenFencer.setFont(new Font("SansSerif", Font.PLAIN, 40));
            greenFencer.setForeground(new Color(0, 128, 0));
            greenFencer.setBounds(700, 50, 60, 50);
            frame.add(greenFencer);
    
            // Couleurs de piste
            Color[] couleurs = {
                new Color(0, 100, 0),     // Vert foncé
                new Color(0, 180, 0),     // Vert normal
                new Color(144, 238, 144), // Vert clair
                new Color(255, 160, 160), // Rouge clair
                new Color(220, 20, 60),   // Rouge normal
                new Color(139, 0, 0)      // Rouge foncé
            };
    
            int startX = 200, startY = 150, width = 80, height = 40;
    
            // Boutons de zone cliquables
            for (int i = 0; i < 6; i++) {
                int zoneIndex = i;
                JButton zone = new JButton(String.valueOf(i + 1));
                zone.setBounds(startX + (i * width), startY, width, height);
                zone.setBackground(couleurs[i]);
                zone.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                zone.setFocusPainted(false);
                zone.addActionListener(e -> {
                    if (!premiereActionFaite) {
                        zones[0] = String.valueOf(zoneIndex + 1);
                        JOptionPane.showMessageDialog(frame, "Zone sélectionnée pour action 1 : " + zones[0]);
                    } else if (zones[1] == null) {
                        zones[1] = String.valueOf(zoneIndex + 1);
                        JOptionPane.showMessageDialog(frame, "Zone sélectionnée pour action 2 : " + zones[1]);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Deux zones déjà sélectionnées.");
                    }
                });
                frame.add(zone);
            }
    
            // Boutons à gauche
            String[] actions = {
                "simple", "compouned", "with blade", "remise att",
                "counter attack", "attack in attack", "remise ca",
                "parry riposte", "attack on recover", "remise def"
            };
            int btnGaucheX = 20, btnGaucheY = 80, btnLargeur = 130, btnHauteur = 25;
    
            for (int i = 0; i < actions.length; i++) {
                final int index = i;
                JButton btn = new JButton(actions[i]);
                btn.setBounds(btnGaucheX, btnGaucheY + (i * (btnHauteur + 5)), btnLargeur, btnHauteur);
                btn.addActionListener(e -> {
                    if (!gaucheChoisie) {
                        if (!premiereActionFaite && zones[0] != null) {
                            selections[0] = actions[index];
                            premiereActionFaite = true;
                            gaucheChoisie = true;
                            boutonSelectionGauche = btn;
                            btn.setBackground(Color.LIGHT_GRAY);
                            JOptionPane.showMessageDialog(frame, "Action 1 (gauche) : " + selections[0]);
                        } else if (zones[1] != null) {
                            selections[1] = actions[index];
                            gaucheChoisie = true;
                            boutonSelectionGauche = btn;
                            btn.setBackground(Color.LIGHT_GRAY);
                            JOptionPane.showMessageDialog(frame, "Action 2 (gauche) : " + selections[1]);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Clique d'abord sur une zone avant de choisir une action.");
                        }
                    }
                });
                frame.add(btn);
            }
    
            // Boutons à droite
            int btnDroiteX = 750, btnDroiteY = 80;
            for (int i = 0; i < actions.length; i++) {
                final int index = i;
                JButton btn = new JButton(actions[i]);
                btn.setBounds(btnDroiteX, btnDroiteY + (i * (btnHauteur + 5)), btnLargeur, btnHauteur);
                btn.addActionListener(e -> {
                    if (!droiteChoisie) {
                        if (!premiereActionFaite && zones[0] != null) {
                            selections[0] = actions[index];
                            premiereActionFaite = true;
                            droiteChoisie = true;
                            boutonSelectionDroite = btn;
                            btn.setBackground(Color.LIGHT_GRAY);
                            JOptionPane.showMessageDialog(frame, "Action 1 (droite) : " + selections[0]);
                        } else if (zones[1] != null) {
                            selections[1] = actions[index];
                            droiteChoisie = true;
                            boutonSelectionDroite = btn;
                            btn.setBackground(Color.LIGHT_GRAY);
                            JOptionPane.showMessageDialog(frame, "Action 2 (droite) : " + selections[1]);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Clique d'abord sur une zone avant de choisir une action.");
                        }
                    }
                });
                frame.add(btn);
            }
    
            // Bouton "OK"
            JButton boutonOK = new JButton("OK");
            boutonOK.setBounds(btnDroiteX, btnDroiteY + actions.length * (btnHauteur + 5) + 10, btnLargeur, btnHauteur);
            boutonOK.addActionListener(e -> {
                
                    String cheminFichier = (GuiPro.class.getProtectionDomain().getCodeSource().getLocation().getPath());
                    File repertoire = new File(cheminFichier);

                    // Créer une instance de File pour le fichier
                    File fichier = new File(repertoire, "Match " + nom1 + " " + nom2);

                if (selections[0] != null && zones[0] != null && selections[1] != null && zones[1] != null) {
                    
                    
                    if (!repertoire.exists()) {
                        repertoire.mkdirs();
                    }
            
                    try (BufferedWriter writers = new BufferedWriter(new FileWriter(fichier, true))) {
                        writers.write("Asssaut" + indice + " : " + zones[0] + " → " + selections[0] + "\n" + zones[1] + " → " + selections[1] + "\n");
                        writers.newLine(); // Pour ajouter un saut de ligne
                        System.out.println("Ligne ajoutée au fichier : " + fichier.getAbsolutePath());
                    } catch (IOException error) {
                        System.err.println("Erreur lors de l'écriture du fichier : " + error.getMessage());
                    }
                    
                    
                                      
                        JOptionPane.showMessageDialog(frame,
                        "Double Touch validée :\n" +
                        zones[0] + " → " + selections[0] + "\n" +
                        zones[1] + " → " + selections[1]);
                        
                }
                
                else if (selections[0] != null && zones[0] != null) {
                    JOptionPane.showMessageDialog(frame,
                        "Action simple validée : " + zones[0] + " → " + selections[0]);
                        try (BufferedWriter writers = new BufferedWriter(new FileWriter(fichier, true))) {
                            writers.write("Asssaut" + indice + " : " + zones[0] + " → " + selections[0] + "\n" + zones[1] + " → " + selections[1] + "\n");
                            writers.newLine(); // Pour ajouter un saut de ligne
                            System.out.println("Ligne ajoutée au fichier : " + fichier.getAbsolutePath());
                        } catch (IOException error) {
                            System.err.println("Erreur lors de l'écriture du fichier : " + error.getMessage());
                        }
                        
                } else {
                    JOptionPane.showMessageDialog(frame, "Sélection incomplète.");
                        try (BufferedWriter writers = new BufferedWriter(new FileWriter(fichier, true))) {
                            writers.write("Asssaut" + indice + " : " + zones[0] + " → " + selections[0] + "\n" + zones[1] + " → " + selections[1] + "\n");
                            writers.newLine(); // Pour ajouter un saut de ligne
                            System.out.println("Ligne ajoutée au fichier : " + fichier.getAbsolutePath());
                        } catch (IOException error) {
                            System.err.println("Erreur lors de l'écriture du fichier : " + error.getMessage());
                        }
                }
    
                // Réinitialisation
                indice+=1;
                selections[0] = selections[1] = null;
                zones[0] = zones[1] = null;
                premiereActionFaite = false;
                droiteChoisie = false;
                gaucheChoisie = false;
    
                if (boutonSelectionDroite != null) boutonSelectionDroite.setBackground(null);
                if (boutonSelectionGauche != null) boutonSelectionGauche.setBackground(null);
            });
            frame.add(boutonOK);
    
            // Bouton Double Touch (centré sous la piste)
            JButton doubleTouch = new JButton("Double Touch");
            doubleTouch.setBounds(startX + (width * 6 - btnLargeur) / 2, 200, btnLargeur, 30);
            doubleTouch.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Sélectionnez 2 zones + 2 actions, dans l’ordre que vous voulez.");
            });
            frame.add(doubleTouch);
    
            frame.setVisible(true);
        });
    }

}