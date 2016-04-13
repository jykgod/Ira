package model.db;

import model.Project;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by xlo on 16/4/13.
 * it's the testing code for project collection
 */
public class ProjectCollectionTest {

    @Test
    public void should_have_story_after_add_story() throws Exception {
        Project insertProject = new Project("1", "name", new ArrayList<>());
        ProjectCollection projectCollection = new ProjectCollection();
        projectCollection.addProject(insertProject);
        projectCollection.submit();

        ProjectCollection projectGetCollection = new ProjectCollection();
        Project resultStory = projectGetCollection.getProjectData("1");
        assertEquals("1", resultStory.getId());
        assertEquals("name", resultStory.getProjectName());
    }

}