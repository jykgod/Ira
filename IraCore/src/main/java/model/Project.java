package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlo on 16/3/21.
 * it's the project
 */
public class Project {

    private String projectName;
    private List<Iteration> iterations;

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
        this.iterations = new ArrayList<>();
    }

    public Project(String projectName, List<Iteration> iterations) {
        this.projectName = projectName;
        this.iterations = iterations;
    }

    public List<Iteration> getIterations() {
        return new ArrayList<>(iterations);
    }

    public void addIteration(Iteration iteration) {
        this.iterations.add(iteration);
    }

    public void removeIteration(Iteration iteration) {
        this.iterations.remove(iteration);
    }

    public String getProjectName() {
        return projectName;
    }
}
