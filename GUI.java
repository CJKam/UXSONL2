import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 562;

    private static JLabel userNamePrompt, passWordPrompt;
    private static JTextField userNameBox, passWordBox;
    private static JButton enter;

    public GUI() {
        setTitle("Hello!");
        setSize(WIDTH, HEIGHT);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loginPane();

        setVisible(true);
    }

    public void loginPane(){
        userNamePrompt = new JLabel("User Name:");
        userNameBox = new JTextField(15);
        passWordPrompt = new JLabel("Password: ");
        passWordBox = new JPasswordField(15);
        enter = new JButton("Enter");
        add(userNamePrompt);
        add(userNameBox);
        add(passWordPrompt);
        add(passWordBox);
        add(enter);
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                teacherPane();
            }
        });
    }

    public void teacherPane(){
        // Code for teacher pane goes here
        // This would include viewing schedule, and changing confident ratings
        //Below is a drop down menu for making classes
        JComboBox<String> classSelect = new JComboBox<>(new String[]{"Math", "Science", "English"});
        add(classSelect);
    }

    public void adminPane(){
        // Code for admin pane goes here
        // This would include methods for adding, removing, and editing teachers and courses
    }

    /* 
    public static void greet(){
        String message = "Glad to meet you, " + nameBox.getText() + "!";
        nameBox.setText("");
        greeting.setText(message);
    }
        */

    public static void main(String[] args){
        new GUI();
    }
}





//Meaning a save button would be neded, menaning I could jsut write a simple class to "push" and "pull" from the files. Then the save or load button simply just saves or loads the data from the csv file.



// GUI would need to be able to edit, remove, and add teachers and courses. 
// And then the user could click on a teacher and it would display their schedule and allow them to change their confidence ratings.

