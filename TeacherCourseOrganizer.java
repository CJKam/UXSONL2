import java.util.ArrayList;
public class TeacherCourseOrganizer {
    //This is the safe way to store the teachers and courses
    // This should be whats run to ensure the safety of the data
    ArrayList<Teacher> teacherList;
    ArrayList<Course> courseList;
    public void Init(ArrayList<Teacher> teacher, ArrayList<Course> course){
        teacherList = teacher;
        courseList = course;
    }

    //Methods that add new teachers, remove teachers and from there respective courses
    //Methods that add new courses, remove courses and from there respective teachers
    //Methods that adjsut confidence levels and the number of values inside of courseConfidence array
    
    //name, maxSection, minSection, experience

    public void addTeacher(String name, int maxSection, int minSection, int experience){
        Teacher teacher = new Teacher();
        teacher.Init(name, maxSection, minSection, experience);
        teacherList.add(teacher);
        //Need a new teacher thats added to have a number of confidence values equal to the number of courses
        System.out.printf("Teacher %s added\n", name);
    }
    
    public void removeTeacher(Teacher teacher){
        teacherList.remove(teacher);
        for (Course course : courseList){
            if(course.currentTeachers().contains(teacher)){
                course.removeTeacher(teacher);
            }
        }
    }

    public void addCourse(Course course){
        //Add the course to the list of courses
        courseList.add(course);
        //Add the course to the courseConfidence array of all teachers
        for (Teacher teacher : teacherList){
            teacher.getCourseConfidence().add(0);
        }
    }
    public void removeCourse(Course course){
        //Remove the course from the courseConfidence array of all teachers
        for (Teacher teacher : teacherList){
            try{
                teacher.getCourseConfidence().remove(courseList.indexOf(course));
            } catch (IndexOutOfBoundsException e) {
                //Pass
            }
        }
        //Remove the course from the list of courses
        courseList.remove(course);
        //Remove the course from the list of teachers that teach it  (if any)
        for (Teacher teacher : teacherList){
            if(teacher.getCurrentCourses().contains(course)){
                teacher.removeCourse(course);
            }
        }

    }

    public void AdTeacherToSec(Teacher teacher, Course course){
        course.addTeacher(teacher);
        teacher.addCourse(course);
    }

    public void RemTeacherFromSec(Teacher teacher, Course course){
        course.removeTeacher(teacher);
        teacher.removeCourse(course);
    }

    public void setConfidence(Teacher teacher, Course course, int confidence){
        int index = courseList.indexOf(course);
        if(index >= 0 && index < teacher.getCourseConfidence().size()){
            teacher.getCourseConfidence().set(index, confidence);
        } else {
            System.out.println("Invalid course or teacher.");
        }
    }

    public void setAllConfidenceZero(Teacher teacher, ArrayList<Course> courseList){
        for (int i = 0; i < courseList.size()-1;i++){
            teacher.courseConfidence.add(0);
        }
    }

    public void resetAllCourseSections(ArrayList<Teacher> teacherList, ArrayList<Course> courseList){
        for (Course course : courseList) {
            for (Teacher teacher : teacherList) {
                teacher.removeCourse(course);
                course.removeTeacher(teacher);
            }
        }
    }

    public Teacher getTeacherByName(String name){
        for (Teacher teacher : teacherList) {
            if(teacher.getName().equals(name)){
                return teacher;
            }
        }
        return null;
    }

}
