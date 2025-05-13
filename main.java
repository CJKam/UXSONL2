import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        //Create list methods that are new public classes that have operations to change and add to these arrays
        ArrayList<Teacher> teacherList = new ArrayList<>(); //The list of teacher objects
        ArrayList<Course> courseList = new ArrayList<>(); //The list of course objects
        TeacherCourseOrganizer organizer = new TeacherCourseOrganizer(); //In Charge of all Teacher - Course operations

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

        // This will generate 1000 schedules and print the top 3 with the highest fitness scores
        ArrayList<ArrayList<Teacher>> topThreeSchedules = Scheduler.generateSchedules(teacherList, courseList, organizer, 100000); 
        for (ArrayList<Teacher> schedule : topThreeSchedules) {
            Scheduler.printSchedule(schedule);
            System.out.println("Fitness Score: " + Scheduler.getFitnessScore(schedule, courseList));
            System.out.println("------");
        }
    }
}