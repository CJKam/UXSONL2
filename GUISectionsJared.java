import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

public class GUISectionsJared extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 562;
    //Create list methods that are new public classes that have operations to change and add to these arrays
    public static ArrayList<Teacher> teacherList = new ArrayList<>(); //The list of teacher objects
    public static ArrayList<Course> courseList = new ArrayList<>(); //The list of course objects
    public static TeacherCourseOrganizer organizer = new TeacherCourseOrganizer(); //In Charge of all Teacher - Course operations


    public GUISectionsJared() {
        setTitle("Hello!");
        setSize(WIDTH, HEIGHT);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPane();

        setVisible(true);
    }

    public void coursePane(ArrayList<Course> courseList){
        setLayout(new GridLayout(2, 1));
        JPanel coursePanel1 = new JPanel();
        JPanel optionPanel2 = new JPanel();
        //In course panel display coures, and number of sections
        // In OPtion Panel have an add coruse button, remove course button, save button, and back button (takes back to main pane)
        add(coursePanel1);
        add(optionPanel2);
        coursePanel1.setLayout(new GridLayout(courseList.size(), 3));
        for (Course course : courseList){
            coursePanel1.add(new JButton(course.getName()));
            coursePanel1.add(new JTextField(String.valueOf(course.numOfSections)));
        }
        JButton addCourseButton = new JButton("Add Course");
        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");
        JButton removeCourseButton = new JButton("Remove Course");
        optionPanel2.add(addCourseButton);
        optionPanel2.add(removeCourseButton);
        optionPanel2.add(saveButton);
        optionPanel2.add(backButton);
        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String courseName = JOptionPane.showInputDialog("What is the name of this new course?");
                Course temp = new Course();
                temp.Init(courseName, 0);
                organizer.addCourse(temp);
                remove(coursePanel1);
                remove(optionPanel2);
                coursePane(courseList);
                setVisible(true);
            }
        });
        removeCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String courseName = JOptionPane.showInputDialog("What is the name of the course you wish to remove?");
                for (Course course : courseList){
                    if (course.courseName.equals(courseName)){
                        organizer.removeCourse(course);
                        courseList.remove(course);
                        break;
                    }
                }
                remove(coursePanel1);
                remove(optionPanel2);
                coursePane(courseList);
                setVisible(true);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                for (Course course : courseList){
                    //int sectionSize = Integer.getInteger();
                    //course.AdjustSectionSize(sectionSize);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                remove(coursePanel1);
                remove(optionPanel2);
                mainPane();
                setVisible(true);
            }
        });
    }

    public void teacherPane(){
        setLayout(new GridLayout(2, 1));
        JPanel teacherPanel1 = new JPanel();
        JPanel optionPanel2 = new JPanel();
        //In course panel display coures, and number of sections
        // In Option Panel have an add coruse button, remove course button, save button, and back button (takes back to main pane)
        add(teacherPanel1);
        add(optionPanel2);
        JButton addTeacherButton = new JButton("Add Teacher");
        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");
        JButton removeTeacherButton = new JButton("Remove Teacher");
        optionPanel2.add(addTeacherButton);
        optionPanel2.add(removeTeacherButton);
        optionPanel2.add(saveButton);
        optionPanel2.add(backButton);

        //Need to modify this to be slightly differnt
        teacherPanel1.setLayout(new GridLayout(teacherList.size()+1, courseList.size()+5));
        teacherPanel1.add(new JLabel("Teacher Names"));
        for (Course course : courseList){
            teacherPanel1.add(new JLabel(course.getName()));
        }
            teacherPanel1.add(new JLabel("Experience"));
            teacherPanel1.add(new JLabel("Max Sections"));
            teacherPanel1.add(new JLabel("Min Sections"));
        for (Teacher teacher : teacherList){
            teacherPanel1.add(new JButton(teacher.getName()));
            System.out.println(teacher.courseConfidence.size());
            for (Integer confidence : teacher.courseConfidence){
                teacherPanel1.add(new JTextField(String.valueOf(confidence)));
            }
            teacherPanel1.add(new JTextField(String.valueOf(teacher.experience)));
            teacherPanel1.add(new JTextField(String.valueOf(teacher.maxSections)));
            teacherPanel1.add(new JTextField(String.valueOf(teacher.minSections)));
        }
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                remove(teacherPanel1);
                remove(optionPanel2);
                mainPane();
                setVisible(true);
            }
        });
        removeTeacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String teacherName = JOptionPane.showInputDialog("What is the name of the course you wish to remove?");
                for (Teacher teacher : teacherList){
                    if (teacher.name.equals(teacherName)){
                        organizer.removeTeacher(teacher);
                        teacherList.remove(teacher);
                        break;
                    }
                }
                remove(teacherPanel1);
                remove(optionPanel2);
                teacherPane();
                setVisible(true);
            }
        });
        addTeacherButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String teacherName = JOptionPane.showInputDialog("What is the name of this new teacher?");
                Teacher temp = new Teacher();
                temp.Init(teacherName, 0, 0, 0);
                organizer.addTeacher(teacherName, 0, 0, 0);
                //add course ocnfidence of all 0's
                for (int i = 0; i < courseList.size()-1;i++){
                    temp.courseConfidence.add(0);
                }
                remove(teacherPanel1);
                remove(optionPanel2);
                teacherPane();
                setVisible(true);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                for (Course course : courseList){
                    //int sectionSize = Integer.getInteger();
                    //course.AdjustSectionSize(sectionSize);
                }
            }
        });
    }

    public void schedulePane(){

    }

    public void mainPane(){
        setLayout(new GridLayout(3, 1));
        JLabel title = new JLabel("Welcome to generative AI Schedule Application!");
        JButton courseButton = new JButton("Course Management");
        JButton teacherButton = new JButton("Teacher Management");
        JButton scheduleButton = new JButton("Schedule Management");
        JButton exit = new JButton("Exit");
        JPanel textPanel = new JPanel();
        JPanel buttonPanel1 = new JPanel();
        JPanel buttonPanel2 = new JPanel();
        add(textPanel);
        add(buttonPanel1);
        add(buttonPanel2);
        textPanel.add(title);
        buttonPanel1.add(courseButton);
        buttonPanel1.add(teacherButton);
        buttonPanel1.add(scheduleButton);
        buttonPanel2.add(exit);
        courseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                remove(textPanel);
                remove(buttonPanel1);
                remove(buttonPanel2);
                coursePane(courseList);
                setVisible(true);
            }
        });

        teacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                remove(textPanel);
                remove(buttonPanel1);
                remove(buttonPanel2);
                teacherPane();
                setVisible(true);
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        scheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                schedulePane();
            }
        });
    }

    public static void main(String[] args){
        // Initialize organizer
        organizer.Init(teacherList, courseList);

        
        organizer.addTeacher("Mrs. Sherrer", 5, 6, 10);
        organizer.addTeacher("Mrs. Vogel", 5, 6, 2);
        organizer.addTeacher("Mrs. Eldredge", 5, 6, 30);
        organizer.addTeacher("Mr. Smith", 5, 6, 4);
        organizer.addTeacher("Mrs. Barth", 5, 6, 2);

        // Create and add courses with section counts
        Course alg1 = new Course(); alg1.Init("Algerbra 1", 4); organizer.addCourse(alg1);
        Course accAlg1 = new Course(); accAlg1.Init("Acc Algerbra 1", 2); organizer.addCourse(accAlg1);
        Course geo = new Course(); geo.Init("Geometry", 3); organizer.addCourse(geo);
        Course accGeo = new Course(); accGeo.Init("Acc Geometry", 2); organizer.addCourse(accGeo);
        Course alg2Trig = new Course(); alg2Trig.Init("Algerbra 2 & Trig", 3); organizer.addCourse(alg2Trig);
        Course apPreCalc = new Course(); apPreCalc.Init("Ap Pre-calc", 2); organizer.addCourse(apPreCalc);
        Course apCalcAB = new Course(); apCalcAB.Init("AP Calc AB", 2); organizer.addCourse(apCalcAB);
        Course apCalcBC = new Course(); apCalcBC.Init("AP Calc BC", 2); organizer.addCourse(apCalcBC);
        Course apStats = new Course(); apStats.Init("AP Statistics", 2); organizer.addCourse(apStats);
        Course mathApps = new Course(); mathApps.Init("Integrated Math apps", 3); organizer.addCourse(mathApps);

        // Set confidence values for each teacher
        Teacher sherrer = organizer.getTeacherByName("Mrs. Sherrer");
        organizer.setConfidence(sherrer, alg1, -3);
        organizer.setConfidence(sherrer, accAlg1, -3);
        organizer.setConfidence(sherrer, geo, -3);
        organizer.setConfidence(sherrer, accGeo, -3);
        organizer.setConfidence(sherrer, alg2Trig, -3);
        organizer.setConfidence(sherrer, apPreCalc, 0);
        organizer.setConfidence(sherrer, apCalcAB, 1);
        organizer.setConfidence(sherrer, apCalcBC, 3);
        organizer.setConfidence(sherrer, apStats, 3);
        organizer.setConfidence(sherrer, mathApps, 3);

        Teacher vogel = organizer.getTeacherByName("Mrs. Vogel");
        organizer.setConfidence(vogel, alg1, -3);
        organizer.setConfidence(vogel, accAlg1, -3);
        organizer.setConfidence(vogel, geo, 3);
        organizer.setConfidence(vogel, accGeo, -3);
        organizer.setConfidence(vogel, alg2Trig, 2);
        organizer.setConfidence(vogel, apPreCalc, -3);
        organizer.setConfidence(vogel, apCalcAB, -3);
        organizer.setConfidence(vogel, apCalcBC, -3);
        organizer.setConfidence(vogel, apStats, -3);
        organizer.setConfidence(vogel, mathApps, 3);

        Teacher eldredge = organizer.getTeacherByName("Mrs. Eldredge");
        organizer.setConfidence(eldredge, alg1, -3);
        organizer.setConfidence(eldredge, accAlg1, -3);
        organizer.setConfidence(eldredge, geo, -3);
        organizer.setConfidence(eldredge, accGeo, 3);
        organizer.setConfidence(eldredge, alg2Trig, -3);
        organizer.setConfidence(eldredge, apPreCalc, 3);
        organizer.setConfidence(eldredge, apCalcAB, -3);
        organizer.setConfidence(eldredge, apCalcBC, -3);
        organizer.setConfidence(eldredge, apStats, -3);
        organizer.setConfidence(eldredge, mathApps, -3);

        Teacher smith = organizer.getTeacherByName("Mr. Smith");
        organizer.setConfidence(smith, alg1, 2);
        organizer.setConfidence(smith, accAlg1, 2);
        organizer.setConfidence(smith, geo, -3);
        organizer.setConfidence(smith, accGeo, -3);
        organizer.setConfidence(smith, alg2Trig, 3);
        organizer.setConfidence(smith, apPreCalc, -3);
        organizer.setConfidence(smith, apCalcAB, -3);
        organizer.setConfidence(smith, apCalcBC, -3);
        organizer.setConfidence(smith, apStats, 2);
        organizer.setConfidence(smith, mathApps, -3);

        Teacher barth = organizer.getTeacherByName("Mrs. Barth");
        organizer.setConfidence(barth, alg1, 3);
        organizer.setConfidence(barth, accAlg1, 3);
        organizer.setConfidence(barth, geo, -3);
        organizer.setConfidence(barth, accGeo, -3);
        organizer.setConfidence(barth, alg2Trig, -3);
        organizer.setConfidence(barth, apPreCalc, -3);
        organizer.setConfidence(barth, apCalcAB, -3);
        organizer.setConfidence(barth, apCalcBC, -3);
        organizer.setConfidence(barth, apStats, -3);
        organizer.setConfidence(barth, mathApps, -3);
        /*
        // This will generate 1000 schedules and print the top 3 with the highest fitness scores
        ArrayList<ArrayList<Teacher>> topThreeSchedules = Scheduler.generateSchedules(teacherList, courseList, organizer, 100000); 
        for (ArrayList<Teacher> schedule : topThreeSchedules) {
            Scheduler.printSchedule(schedule);
            System.out.println("Fitness Score: " + Scheduler.getFitnessScore(schedule, courseList));
            System.out.println("------");
        }
        */

        new GUISectionsJared();
    }
}





//Meaning a save button would be neded, menaning I could jsut write a simple class to "push" and "pull" from the files. Then the save or load button simply just saves or loads the data from the csv file.



// GUI would need to be able to edit, remove, and add teachers and courses. 
// And then the user could click on a teacher and it would display their schedule and allow them to change their confidence ratings.

