import java.util.ArrayList;
public class Course {
    String courseName;
    int numOfSections;
    ArrayList<Teacher> currentTeachers;
    public void Init(String courseName, int numOfSections){
        currentTeachers = new ArrayList<>();
        this.courseName = courseName;
        this.numOfSections = numOfSections;
        // Fill the currentTeachers list with Teacher objects
        // and initialize the courseName and numOfSections fields
        // This method should be called when a new Course object is created
    }

    //Basic functions here
    public void addTeacher(Teacher teacher){currentTeachers.add(teacher);}
    public void removeTeacher(Teacher teacher){currentTeachers.remove(teacher);}
    public void AdjustSectionSize(int newMaxSections){numOfSections = newMaxSections;}
    public ArrayList<Teacher> currentTeachers(){return currentTeachers;}
    public int capacity(){return numOfSections - currentTeachers.size();}
    
}
