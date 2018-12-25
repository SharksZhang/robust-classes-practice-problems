package model;

import exceptions.CourseFullException;
import exceptions.GPATooLowException;
import exceptions.MissingPrereqException;
import exceptions.NoCoursesTakenException;

import java.util.ArrayList;
import java.util.List;

public class    Transcript {

    private String name;
    private int year;
    private int id;
    private double gpa;
    private List<Course> currentCourses;
    private List<Course> pastCourses;

    private double percentToGPA(double pcnt) {
        return (pcnt / 20) - 1;
    }

    public Transcript(String studentName, int academicYear, int studentID) {
        this.name = studentName;
        this.year = academicYear;
        this.id = studentID;
        this.currentCourses = new ArrayList<Course>();
        this.pastCourses = new ArrayList<Course>();
    }

    // getters
    public String getName() {
        return this.name;
    }

    public int getAcademicYear() {
        return this.year;
    }

    public int getId() {
        return this.id;
    }

    public List<Course> getCurrentCourses() {
        return currentCourses;
    }

    public List<Course> getPastCourses() {
        // TODO: complete the implementation of this method
        // HINT: you may need to consider what kind of information a completed
        // course on a transcript needs to have that the Course class
        // doesn't already contains

        return this.pastCourses;
    }

    // EFFECTS: computes cGPA
    //          if the list of past courses is empty, throws NoCoursesTakenException
    public double computeGPA() throws NoCoursesTakenException {
        //          HINTS:
        //          use the following formula to convert into a GPA
        //          GPA (4.0 scale) = (total percentage/20) - 1
        //          **Do you need a helper method?**
        
        // TODO: complete the implementation of this method
        if (this.pastCourses.isEmpty()) {
            throw new NoCoursesTakenException();
        }
        double totalPercentage = 0.0;
        for (Course course : pastCourses){
            totalPercentage += course.getGrade();
        }


        return percentToGPA(totalPercentage / pastCourses.size()) ;
    }

    // EFFECTS: promotes the student represented by the transcript
    //          to the next academic year if GPA is over 2.6 on a 4.0 scale, and return true
    //          else return false with no change to the year field
    //          if GPA is not over 2.6 on a 4.0 scale, throws GPATooLowException
    //          if no courses have been taken, throws NoCoursesTakenException
    public boolean promoteStudent() throws GPATooLowException, NoCoursesTakenException {
        
        // TODO: complete the implementation of this method
        // TODO: when return false
        try {
            double gpa = computeGPA();
            if (gpa < 2.6){
                throw new GPATooLowException();
            }else {
                year ++;
                return true;
            }

        }catch (NoCoursesTakenException e){
            return false;
        }

    }


    // MODIFIES: this
    // EFFECTS: adds the given course to the list of past courses and returns true,
    //          unless pastCourses contains given course, then does not add and returns false
    public boolean addToPastCourses(Course c) {
        if (!pastCourses.contains(c)){
            pastCourses.add(c);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: adds a course (c) into the record
    //          if the transcript is missing prerequisites, throws a MissingPrereqException
    //          if the course has no space for more students to register, throws a CourseFullException
    public boolean addCourse(Course course) throws MissingPrereqException, CourseFullException {
        // TODO: implement this method.
        // You have to realize that there are a number of cases that your code needs to consider. What if the course
        // you wish to add has no prerequisites? What if the course you want to add is already full?
        // Careful consideration of these cases will lead to code that is correct
        if(course.isCourseFull()){
            throw new CourseFullException();
        }

        List<Course> prereqCourses = course.getPrereq();
        for (Course preCourse : prereqCourses){
            if (!pastCourses.contains(preCourse)){
                throw new MissingPrereqException();
            }
        }
        currentCourses.add(course);
        course.addStudent();
        return true;
    }


}
