package model.db;

import model.Iteration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by xlo on 16/4/13.
 * it's the testing code for iteration collection
 */
public class IterationCollectionTest {

    @Test
    public void should_have_story_after_add_story() throws Exception {
        Iteration insertIteration = new Iteration("1", new Date(), new Date(), new ArrayList<>());
        IterationCollection iterationCollection = new IterationCollection();
        iterationCollection.addIteration(insertIteration);
        iterationCollection.submit();

        IterationCollection iterationGetCollection = new IterationCollection();
        Iteration resultStory = iterationGetCollection.getStoryData("1");
        assertEquals("1", resultStory.getId());
    }

}