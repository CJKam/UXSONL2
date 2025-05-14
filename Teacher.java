import java.util.ArrayList;
public class Teacher {
    // Max sections & name() mus both be run and given values
    String name;
    int maxSections;
    int minSections;
    int experience;
    ArrayList<Course> currentCourses;
    ArrayList<Integer> courseConfidence;

    public Teacher(String trim) {
        //TODO Auto-generated constructor stub
    }

    public void Init(String name, int maxSections, int minSections, int experience){
        currentCourses = new ArrayList<>();
        courseConfidence = new ArrayList<>();
        this.name = name;
        this.maxSections = maxSections;
        this.minSections = minSections;
        this.experience = experience;
    }

    //Simple methods to add and remove courses, and to return the current courses and confidence
    public void addCourse(Course course){currentCourses.add(course);}
    public void removeCourse(Course course){currentCourses.remove(course);}
    public ArrayList<Course> getCurrentCourses(){return currentCourses;}
    public ArrayList<Integer> getCourseConfidence(){return courseConfidence;}
    public int getCapacity(){return maxSections - currentCourses.size();}
    public int getExperience(){return experience;};

    public void setAllConfidence(ArrayList<Integer> newCourseConfidence){
        courseConfidence = newCourseConfidence;
    }

    public String getName(){return name;};
    
    //More complex Methods
    //Set all course Confidence to 0.
    public void resetCourseConfidence(ArrayList<Course> courseList){
        //Nothing
    }
    
}
