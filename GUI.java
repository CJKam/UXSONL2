*/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 562;

    private static JLabel helloPrompt, newLine;
    private static JTextField userNameBox, passWordBox;
    private static JButton enter, editTeach, editCourse, editConf, saveProg, createSched;
    
    ArrayList<Teacher> teacherList = new ArrayList<>(); //The list of teacher objects
    ArrayList<Course> courseList = new ArrayList<>(); //The list of course objects
    TeacherCourseOrganizer organizer = new TeacherCourseOrganizer(); //In Charge of all Teacher - Course operations
    public GUI() {
        setTitle("Hello!");
        setSize(WIDTH, HEIGHT);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        greet();

        setVisible(true);
    }

   /* public void loginPane(){
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
    } */

   /* public void teacherPane(){
        // Code for teacher pane goes here
        // This would include viewing schedule, and changing confident ratings
        //Below is a drop down menu for making classes
        JComboBox<String> classSelect = new JComboBox<>(new String[]{"Math", "Science", "English"});
        add(classSelect);
    } */

    public void startFromScratchOrNo(){
        // code for starting from scratch or not
        // This would be a simple yes or no question with two buttons
        // If yes, then it would create a new file with all the data in it to be used in the scheduler
        // If no, then it would load the data from the csv file and use that to create the schedule
        // This would be a simple yes or no question with two buttons
        JButton startFromScratch = new JButton("Start from scratch");
        JButton loadFromFile = new JButton("Load from file");
        startFromScratch.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadFromFile.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(startFromScratch);
        add(loadFromFile);
        startFromScratch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // code for starting from scratch
                // This would create a new file with all the data in it to be used in the scheduler
                remove(startFromScratch);
                remove(loadFromFile);
                revalidate();
                repaint();
                choicer();
            }
        });
        loadFromFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // code for loading from file
                // This would load the data from the csv file and use that to create the schedule
                String fileName = JOptionPane.showInputDialog("Loading from file named? (must be a valid csv file saved from a previous session)");
                // The file name would be inputted here (should be a csv file)
                if (fileName != null && fileName.toLowerCase().endsWith(".csv")) {
                    // proceed to load the CSV file
                    loadCsv(fileName);
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "Please enter a valid CSV file name.", "Invalid File", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // and then the data would be loaded from the file to be edited
            }
        });
    }

    public void loadCsv(String fileName) {
        // code for loading the csv file goes here
        
        choicer();
    }
    public void choicer(){
        // code for choosing to between for adding, removing, and editing teachers, courses, and confidence ratings
        // This would be three buttons that would be used to choose between the three options
        //after the choice is made the gui would then open an option pane with first a dropdown menu with the option to add, remove, or edit
        // and then the user would then input the name of the teacher or course they want to add, remove, or edit in a new option pane text box
        // when all the data is inputted a new button will appear in the gui and when clicked it will create a new file with all the data in it to be used in the scheduler
        editTeach = new JButton("Edit Teachers");
        editCourse = new JButton("Edit Courses");
        editConf = new JButton("Edit Confidence Ratings");
        saveProg = new JButton("Save current data as CSV");
        createSched = new JButton("Create Schedule");
        editTeach.setAlignmentX(Component.CENTER_ALIGNMENT);
        editCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
        editConf.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveProg.setAlignmentX(Component.CENTER_ALIGNMENT);
        createSched.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(editTeach);
        add(editCourse);
        add(editConf);
        add(saveProg);
        add(createSched);
        revalidate();
        repaint();
        editTeach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // code for editing teachers goes here
                // This would be a simple option pane with a dropdown menu with the option to add, remove, or edit
                // and then the user would then input the name of the teacher they want to add, remove, or edit in a new option pane text box
                // when all the data is inputted a new button will appear in the gui and when clicked it will create a new file with all the data in it to be used in the scheduler
                Object[] options = {"Add", "Remove", "Edit"};
                int choice = JOptionPane.showOptionDialog(
                    GUI.this,
                    "Select an action for teachers:",
                    "Edit Teachers",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
                );
                
                if (choice == 0) {
                    // Add teacher
                    String teacherName = JOptionPane.showInputDialog("Enter the name of the teacher to add:");
                    return; // User closed the dialog
                }
                else if (choice == 1) {
                    // Remove teacher
                    if (teacherList.isEmpty()) {
                        JOptionPane.showMessageDialog(GUI.this, "No teachers to remove.", "Remove Teacher", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    String[] teacherNames = teacherList.stream().map(t -> t.getName()).toArray(String[]::new);
                    String teacherName = (String) JOptionPane.showInputDialog(
                        GUI.this,
                        "Select the teacher to remove:",
                        "Remove Teacher",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        teacherNames,
                        teacherNames[0]
                    );
                    return; // User closed the dialog
                }
                else if (choice == 2) {
                    // Edit teacher
                    String teacherName = JOptionPane.showInputDialog("Enter the name of the teacher to edit:");
                    return; // User closed the dialog
                }
                else {
                    // User closed the dialog or clicked "Cancel"
                    return;
                }
            }
        });
        editCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // code for editing courses goes here
                // This would be a simple option pane with a dropdown menu with the option to add, remove, or edit
                // and then the user would then input the name of the course they want to add, remove, or edit in a new option pane text box
                // when all the data is inputted a new button will appear in the gui and when clicked it will create a new file with all the data in it to be used in the scheduler
            }
        });
        editConf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // code for editing confidence ratings goes here
                // This would be a simple option pane with a dropdown menu with the option to add, remove, or edit
                // and then the user would then input the name of the teacher they want to add, remove, or edit in a new option pane text box
                // when all the data is inputted a new button will appear in the gui and when clicked it will create a new file with all the data in it to be used in the scheduler
            }
        });
        saveProg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // code for saving the program goes here
            }
        });
        createSched.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // code for creating the schedule goes here
            }
        });
        
    }
    
    public void greet(){
        helloPrompt = new JLabel("Hello User!");
        helloPrompt.setFont(new Font("Arial", Font.PLAIN, 20));
        enter = new JButton("Enter");
        helloPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        enter.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(30));
        add(helloPrompt);
        add(Box.createVerticalStrut(20));
        add(enter);
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            // Remove greet message and button
            remove(helloPrompt);
            remove(enter);
            startFromScratchOrNo();
            revalidate();
            repaint();
            }
        });
        }
        
        

    public static void main(String[] args){
        new GUI();
    }
}
*/




//Meaning a save button would be neded, menaning I could jsut write a simple class to "push" and "pull" from the files. Then the save or load button simply just saves or loads the data from the csv file.



// GUI would need to be able to edit, remove, and add teachers and courses. 
// And then the user could click on a teacher and it would display their schedule and allow them to change their confidence ratings.

