package service.clojure;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlo on 16/1/29.
 * it's the script manager
 */
public class ScriptManager {

    private static ScriptManager scriptManager;

    public static ScriptManager getScriptManager() {
        if (scriptManager == null) {
            synchronized (ScriptManager.class) {
                if (scriptManager == null) {
                    scriptManager = new ScriptManager();
                }
            }
        }
        return scriptManager;
    }

    private Map<String, Object> scriptObjects;
    private Map<String, Method> scriptMethods;

    private ScriptManager() {
        this.scriptObjects = new HashMap<>();
        this.scriptMethods = new HashMap<>();
    }

    public void registerMethod(String name, Object object) throws NoSuchMethodException {
        scriptObjects.put(name, object);
        Class<?>[] objects = new Class[3];
        for (int i = 0; i < 3; i++) {
            objects[i] = Object.class;
        }
        Method method = object.getClass().getMethod("invoke", objects);
        method.setAccessible(true);
        scriptMethods.put(name, method);
    }

    public Boolean invoke(String name, Object... objects) throws Exception {
        Method method;
        Object object;
        method = scriptMethods.get(name);
        object = scriptObjects.get(name);
        return (Boolean) method.invoke(object, objects);
    }
}
