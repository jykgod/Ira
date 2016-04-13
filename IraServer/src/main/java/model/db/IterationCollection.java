package model.db;

import model.Iteration;
import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by xlo on 16/4/13.
 * it's the iteration collection
 */
public class IterationCollection extends IraCollection {

    public void addIteration(Iteration iteration) {
        this.lockCollection();
        this.insert(new Document(iteration.getObject()));
    }

    public Iteration getIteration(String id) {
        this.lockCollection();
        List<Map<String, Object>> iterator = this.collection.find(new Document().append("_id", id));
        if (iterator.size() == 0) {
            return null;
        }
        return new Iteration(this.addDocumentToUsing(iterator.get(0)));
    }

    public Iteration getStoryData(String id) {
        List<Map<String, Object>> iterator = this.collection.find(new Document().append("_id", id));
        if (iterator.size() == 0) {
            return null;
        }
        return new Iteration(this.getDocumentNotUsing(iterator.get(0)));
    }

}
