package model.db;

import model.Project;
import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by xlo on 16/4/13.
 * it's the project collection
 */
public class ProjectCollection extends IraCollection {

    public void addProject(Project project) {
        this.lockCollection();
        this.insert(new Document(project.getObject()));
    }

    public Project getProject(String id) {
        this.lockCollection();
        List<Map<String, Object>> iterator = this.collection.find(new Document().append("_id", id));
        if (iterator.size() == 0) {
            return null;
        }
        return new Project(this.addDocumentToUsing(iterator.get(0)));
    }

    public Project getProjectData(String id) {
        List<Map<String, Object>> iterator = this.collection.find(new Document().append("_id", id));
        if (iterator.size() == 0) {
            return null;
        }
        return new Project(this.getDocumentNotUsing(iterator.get(0)));
    }

}
