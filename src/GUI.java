import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GUI extends JFrame{


    private JButton button1;
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton button2;

    private JFileChooser jfc;

    private JMenu menuFile;
    private JMenuBar menuBar;
    private JMenuItem miOpenFile;

    private ArrayList<String> list=new ArrayList<String>();


    public GUI(){

        setTitle("Otevírač souborů");
        setContentPane(panel1);
        jfc = new JFileChooser();
        menuBar = new JMenuBar();
        menuFile = new JMenu("Výběr");
        menuBar.add(menuFile);
        miOpenFile = new JMenuItem("Vyberte soubor");
        menuFile.add(miOpenFile);
        miOpenFile.addActionListener(e -> readFile());
        setJMenuBar(menuBar);

        button1.addActionListener(e -> selectFile());
        button2.addActionListener(e -> vypis());
    }

    private void readFile(){
        selectFile();
    }

    private void selectFile(){
        int result = jfc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = jfc.getSelectedFile();
            loadFileContents(selectedFile);
        }
    }


    private void loadFileContents(File selectedFile) {
        try ( Scanner s = new Scanner(new BufferedReader(
                                           new FileReader(selectedFile)))
        ) {while (s.hasNextLine()){
            list.add(s.nextLine());
        }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void vypis(){
        if(list.size()==0){
            textArea1.setText("seznam jest prázdný :(");
        }else{
            String celyObsah = String.join("\n", list);
            textArea1.setText(celyObsah);
        }
    }

    public static void main(String[] args) {
        GUI g=new GUI();
        g.setSize(500, 500);
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}