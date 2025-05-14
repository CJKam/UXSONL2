import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        //Create list methods that are new public classes that have operations to change and add to these arrays
        ArrayList<Teacher> teacherList = new ArrayList<>(); //The list of teacher objects
        ArrayList<Course> courseList = new ArrayList<>(); //The list of course objects
        TeacherCourseOrganizer organizer = new TeacherCourseOrganizer(); //In Charge of all Teacher - Course operations

        // Initialize organizer
        organizer.Init(teacherList, courseList);



        // Gui that allows users to add, remove, and manage teachers and courses
        // And perform operations on them

        // To add a teacher, you need to create the teacher object, initiate the teacher and then add it using 
        // organizer addTeacher method
        // This proccess is simaliar for courses
        /*
        //Example:
        organizer.addTeacher("John Doe", 5, 3, 17);
        organizer.addTeacher("Daddy", 4, 1, 10);
        organizer.addTeacher("Snoopy", 2, 1, 3);
        organizer.addTeacher("Bella", 1, 1, 1);
        organizer.addTeacher("Rex", 3, 1, 15);
        organizer.addTeacher("Max", 2, 1, 5);
        organizer.addTeacher("Sally", 2, 1, 7);

        Course course1 = new Course();
        course1.Init("IED", 3);
        organizer.addCourse(course1);
        Course course2 = new Course();
        course2.Init("POE", 2);
        organizer.addCourse(course2);
        Course course3 = new Course();
        course3.Init("Comp Sci 150", 3);
        organizer.addCourse(course3);
        Course course4 = new Course();
        course4.Init("Comp Sci 250", 2);
        organizer.addCourse(course4);
        Course course5 = new Course();
        course5.Init("DISCO", 2);
        organizer.addCourse(course5);
        Course course6 = new Course();
        course6.Init("Algerbra 1", 3);
        organizer.addCourse(course6);
        Course course7 = new Course();
        course7.Init("Algerbra 2", 2);
        organizer.addCourse(course7);

        // Generate random course confidence for teachers for testing purposes
        Random random = new Random();
        for (Teacher teacher : teacherList) {
            for (Course course : courseList) {
                organizer.setConfidence(teacher, course, random.nextInt(4)-3);
            }
        }
            */
        // Add teachers (all have 5 years experience and 6 sections each)
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

        // This will generate 1000 schedules and print the top 3 with the highest fitness scores
        ArrayList<ArrayList<Teacher>> topThreeSchedules = Scheduler.generateSchedules(teacherList, courseList, organizer, 100000); 
        for (ArrayList<Teacher> schedule : topThreeSchedules) {
            Scheduler.printSchedule(schedule);
            System.out.println("Fitness Score: " + Scheduler.getFitnessScore(schedule, courseList));
            System.out.println("------");
        }
    }
}