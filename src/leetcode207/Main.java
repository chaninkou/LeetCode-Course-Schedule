package leetcode207;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int numCourses = 3;
		
		int[][] prerequisites = {{1,0},{2,1}};
		
		System.out.println("Pre: " + Arrays.deepToString(prerequisites));
		
		System.out.println("Number of course could take: " + numCourses);
		
		CheckIfAbleToTakeCourseFunction solution = new CheckIfAbleToTakeCourseFunction();
		
		System.out.println("Solution: " + solution.canFinish(numCourses, prerequisites));
	}
}
