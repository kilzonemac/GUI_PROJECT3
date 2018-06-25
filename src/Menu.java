import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends JFrame {
    private JPanel panel;
    private JPanel buttons;
    private JButton new_game;
    private JButton highscore;
    private JButton exit;
    private Application game;

    public Menu(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400,500));
        setLocation(300,300);
        setVisible(true);
        setResizable(false);
        pack();
        setTitle("Snake");
        setLayout(new BorderLayout());
        panel=new JPanel(new GridLayout(3,1));
        buttons=new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));


        JLabel entry_label=new JLabel("Snake", SwingConstants.CENTER);
        entry_label.setFont(new Font("Serif", Font.BOLD, 28));
        JLabel entry_label1=new JLabel("s17231", SwingConstants.CENTER);
        entry_label1.setFont(new Font("Serif", Font.PLAIN, 18));

        //PRZYCISKI
        new_game=new JButton("New Game");
        new_game.setFont(new Font("Serif", Font.BOLD, 16));
        new_game.setBackground(Color.ORANGE);
        new_game.setAlignmentX(Component.CENTER_ALIGNMENT);
        new_game.addActionListener(e -> {
           String selection = (String) JOptionPane.showInputDialog(this, "Podaj rozmiar planszy do gry", "Nowa Gra",
                    JOptionPane.QUESTION_MESSAGE, null, new String[] { "Duże", "Średnie", "Małe" },
                    "Małe");
           if(!(selection==null)) {
               //APLICATION JAVAFX
               switch (selection){
                   case "Małe":
                       game = new NewGame(400,300);
                       break;
                   case "Średnie":
                       game = new NewGame(600,450);
                       break;
                   case "Duże":
                       game = new NewGame(800,600);
                       break;
               }

               setVisible(false);
               Platform.setImplicitExit(false);
               Platform.runLater(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           game.init();
                           game.start(new Stage());
                       } catch (Exception e1) {
                           e1.printStackTrace();
                       }
                   }
               });
           }

        });

        highscore=new JButton("High Scores");
        highscore.setFont(new Font("Serif", Font.PLAIN, 14));
        highscore.setAlignmentX(Component.CENTER_ALIGNMENT);
        highscore.addActionListener(e -> {
            JFrame highscore = new HighScore();
            setVisible(false);
            highscore.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    setVisible(true);
                }
            });
        });

        exit=new JButton("EXIT");
        exit.setFont(new Font("Serif", Font.PLAIN, 14));
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.addActionListener(e ->  {
                System.exit(0);
        });

        buttons.add(new_game);
        buttons.add(Box.createRigidArea(new Dimension(0, 18)));
        buttons.add(highscore);
        buttons.add(Box.createRigidArea(new Dimension(0, 16)));
        buttons.add(exit);

        panel.add(entry_label);
        panel.add(entry_label1);
        panel.add(buttons);

        add(panel,BorderLayout.CENTER);

    }


}
