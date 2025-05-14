import java.util.ArrayList;
import java.util.Random;

public class Scheduler {
    public static Random random = new Random();
    public static ArrayList<Teacher> randomSort(ArrayList<Teacher> teachersList, ArrayList<Course> courseList, TeacherCourseOrganizer organizer){
        teachersList = copyTeacherList(teachersList);
        organizer.resetAllCourseSections(teachersList, courseList);
        for (Course course : courseList) {
            for (int i = 0; i < course.numOfSections; i++) {
                int randomIndex = random.nextInt(teachersList.size()-1);
                organizer.AdTeacherToSec(teachersList.get(randomIndex), course);
                System.out.println("Added teacher " + teachersList.get(randomIndex).getName() + " to section " + (i+1) + " of course " + course.courseName);
            }
        }
        return teachersList;
    }


    public static int getFitnessScore(ArrayList<Teacher> teacherList, ArrayList<Course> courseList){
        teacherList = copyTeacherList(teacherList);
        // Calculate fitness score for the inputed schedule
        // This is a crucial part in this generative scheudling process
        int fitnessScore = 0;
        // Logic for a fitness score calculation is that the confidence values of teachers coures they are currently teaching is additive to the fitness score
        for (Teacher teacher : teacherList) {
            for (Course course : teacher.currentCourses) {
                fitnessScore += teacher.getCourseConfidence().get(courseList.indexOf(course));
            }
            //Add to the fitness score depending on how close the teacher is to have the number of sections
            //Get average of fitness scores rather than adding them all together
            fitnessScore /= teacherList.size();
            fitnessScore -= Math.abs(teacher.maxSections - teacher.currentCourses.size());
        }
        //Get average of fitness scores rather than adding them all together

        //System.out.println(fitnessScore);
        return fitnessScore;
    }


    // After being given a multi dimensional array of teachers, this method returns the fitness score of each given sub multi dimensional array and returns the top 3 schedules with the highest fitness scores
    public static ArrayList<ArrayList<Teacher>> returnTopThreeSchedules(ArrayList<ArrayList<Teacher>> teacherScheduleArray, ArrayList<Course> courseList){
        //Returns the 3 best schedules with the highest fitness scores
        ArrayList<ArrayList<Teacher>> topSchedules = new ArrayList<>();
        ArrayList<Integer> fitnessScores = new ArrayList<>();
        // Calculate fitness scores for each schedule and add them to the fitnessScores ArrayList
        for (ArrayList<Teacher> teacherSchedule : teacherScheduleArray) {
            fitnessScores.add(getFitnessScore(teacherSchedule, courseList));
        }
        // Just take the top scheule in the array and add to top schedule
        for (int i = 0; i < 10; i++) {
            /*
            int maxIndex = fitnessScores.indexOf(Collections.max(fitnessScores)); //I didnt feel like trying to sort stuff so used collections ;)
            topSchedules.add(sortBasedOnFitnessScore(teacherScheduleArray.get(maxIndex), fitnessScores));
            fitnessScores.remove(maxIndex);
            teacherScheduleArray.remove(maxIndex);
            */
            topSchedules.add(sortBasedOnFitnessScore(teacherScheduleArray.get(i), fitnessScores));
            fitnessScores.remove(i);
            teacherScheduleArray.remove(i);
        }
        return topSchedules;
    }

    // Swap two random courses that teachers are teaching in the teacherList. 
    //REVISED: Instead of swapping two courses you simply add one course to a random other teacher and remove from that teacher
    public static void swapCourses(ArrayList<Teacher> teacherList, TeacherCourseOrganizer organizer){
        int randomIndex2 = random.nextInt(teacherList.size());
        boolean flag = true;
        while(flag){
            int randomIndex1 = random.nextInt(teacherList.size());
            if(!teacherList.get(randomIndex1).currentCourses.isEmpty()){
                flag = false;
                Course tempCourse = teacherList.get(randomIndex1).currentCourses.get(random.nextInt(teacherList.get(randomIndex1).currentCourses.size()));
                organizer.RemTeacherFromSec(teacherList.get(randomIndex1), tempCourse);
                organizer.AdTeacherToSec(teacherList.get(randomIndex2), tempCourse);
            }
        }
    }

    
    public static ArrayList<ArrayList<Teacher>> generateSchedules(ArrayList<Teacher> teacherList, ArrayList<Course> courseList, TeacherCourseOrganizer organizer, int numSchedules){
        //Meow
        //Generate 10 random schedules
        ArrayList<ArrayList<Teacher>> topSchedules = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topSchedules.add(randomSort(teacherList, courseList, organizer));
        }
        // For debugging I want to print out these three orginal schedules
        for (ArrayList<Teacher> teacherSchedule : topSchedules) {
            printSchedule(teacherSchedule);
            System.out.println("---------");
        }

        // Evolve the schedules
        for (int i = 0; i < numSchedules; i++) {
            // Make the topSchedules include 10 copies of each schedule with each copy having one swap applied to it
            ArrayList<ArrayList<Teacher>> newSchedules = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                ArrayList<Teacher> modfiedSchedule = new ArrayList<>(topSchedules.get(j % 10));
                swapCourses(modfiedSchedule, organizer);
                newSchedules.add(modfiedSchedule);
            }
            // Get the top 3 schedules with the highest fitness scores and set them as the new topSchedules
            topSchedules = returnTopThreeSchedules(newSchedules, courseList);
            /*
            for (ArrayList<Teacher> teacherSchedule : topSchedules) {
                printSchedule(teacherSchedule);
                System.out.println("---------");
            }
                */
            //System.out.println(topSchedules);
        }
        return topSchedules;
    }


    // Need to create a copy of the teacherList so that we don't modify the original list while generating schedules
    public static ArrayList<Teacher> copyTeacherList(ArrayList<Teacher> original) {
        ArrayList<Teacher> copy = new ArrayList<>();
        for (Teacher t : original) {
            Teacher clone = new Teacher(); 
            clone.Init(t.getName(), t.minSections, t.maxSections, t.experience);
            clone.setAllConfidence(new ArrayList<>(t.getCourseConfidence()));
            clone.currentCourses = new ArrayList<>(t.currentCourses); // shallow copy of courses
            copy.add(clone);
        }
        return copy;
    }


    public static ArrayList<Teacher> sortBasedOnFitnessScore(ArrayList<Teacher> teacherList, ArrayList<Integer> fitnessScores){
        // Sort the teacherList based on their fitnessScores
        for (int i = 0; i < teacherList.size(); i++) {
            for (int j = i + 1; j < teacherList.size(); j++) {
                if(fitnessScores.get(i) < fitnessScores.get(j)){
                    int temp = fitnessScores.get(i);
                    fitnessScores.set(i, fitnessScores.get(j));
                    fitnessScores.set(j, temp);

                    Teacher tempTeacher = teacherList.get(i);
                    teacherList.set(i, teacherList.get(j));
                    teacherList.set(j, tempTeacher);
                }
            }
        }
        return teacherList;
    }

    public static void printSchedule(ArrayList<Teacher> teacherList){
        //Print the schedule here
        for (Teacher teacher : teacherList) {
            System.out.print("Teacher: " + teacher.name + ", Schedule: ");
            for (Course course : teacher.currentCourses) {
                System.out.print(course.courseName + ", ");
            } System.out.println();
    
        }
    }
}