package main;

import model.entity.Iteration;
import model.exception.JsonClassNotMatchException;

import java.util.Date;

/**
 * Created by xlo on 16/3/21.
 * it's the main class
 */
public class Main {

    public static void main(String[] args) throws JsonClassNotMatchException {
        Iteration iteration = new Iteration();
        iteration.setFrom(new Date());
        iteration.setTo(new Date());
        iteration.addStory("a");
        iteration.addStory("b");
        iteration.updateValueFromJson(iteration.toJsonString());
        System.out.println(iteration.getStories().get(0));
    }
}
