package leetcode207;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CheckIfAbleToTakeCourseFunction {
	// BFS approach, using queue, fast solution since we use array of arraylist to keep track of adjacent vertex from each course
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] visited = new int[numCourses];
		
		List<Integer>[] adj = new ArrayList[numCourses];

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}

		// For every prereq course, store any course that could be taken
		for (int[] edge : prerequisites) {
			adj[edge[1]].add(edge[0]);

			// Mark the course as need to take a prereq first
			visited[edge[0]]++;
		}

		Queue<Integer> queue = new LinkedList<>();

		// offer any course that could be taken without any prereq
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == 0) {
				queue.offer(i);
			}
		}

		// Instead of zero, just imagine we took the course already
		int courseDone = queue.size();

		while (!queue.isEmpty()) {
			int prereq = queue.poll();

			// Only look at what other course you could take with current prereq, instead of o(n) every given prerequisites
			for (int course : adj[prereq]) {
				visited[course]--;

				if (visited[course] == 0) {
					queue.offer(course);
					courseDone++;
				}
			}
		}

		return courseDone == numCourses;
	}
	
	// DFS solution, slow since we are checking prerequisites everytime
	public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[] visited = new int[numCourses];
        
        int courseTaken = 0;
        
        // Increase the count of every vertex that have edges
        for(int i = 0; i < prerequisites.length; i++){
            visited[prerequisites[i][0]]++;
        }
        
        Stack<Integer> stack = new Stack<>();
        
        // Put all the course without prereq into the stack
        for(int j = 0; j < visited.length; j++){
            if(visited[j] == 0){
                stack.push(j);
            }
        }
        
        while(!stack.isEmpty()){
            int current = stack.pop();
            courseTaken++;
            
            // Check all the preque, this is pretty slow O(n) for every vertex popped from stack
            for(int i = 0; i < prerequisites.length; i++){
            	// If a course could be taken from current prereq, update
                if(prerequisites[i][1] == current){
                    visited[prerequisites[i][0]]--;
                    
                    if(visited[prerequisites[i][0]] == 0){
                        stack.push(prerequisites[i][0]);
                    }
                }
            }
        }
        
        return courseTaken == numCourses;
    }
}
