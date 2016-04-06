package model.config;

import model.db.DBClient;
import model.db.VirtualDBConnection;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by xlo on 15-8-23.
 * it's the db config
 */
public class IraDBConfig implements ConfigInterface, DBConfig {
    private String host = "127.0.0.1";
    private int port = 27017;
    private Map<String, String> collections, collectionNames, dbOfCollection, dbType;
    private Set<String> dbs;
    private Class<? extends VirtualDBConnection> connectionClass;

    protected IraDBConfig() {
        this.collections = new HashMap<>();
        this.collectionNames = new HashMap<>();
        this.dbOfCollection = new HashMap<>();
        this.dbType = new HashMap<>();
        this.dbs = new HashSet<>();
    }

    public static IraDBConfig getConfig() {
        return (IraDBConfig) ConfigManager.getConfigManager().getConfig(IraDBConfig.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init() throws Exception {
        Element root = ConfigInterface.getRootElement(ConfigManager.configPathConfig.getConfigFilePath(this.getClass()));
        for (Object now : root.elements()) {
            Element element = (Element) now;
            if (element.getName().equals("host")) {
                this.host = element.getText();
            }
            else if (element.getName().equals("port")) {
                this.port = Integer.valueOf(element.getText());
            }
            else if (element.getName().equals("connection")) {
                this.connectionClass = (Class<? extends VirtualDBConnection>) Class.forName(element.getText());
            } else {
                String dbName = element.attributeValue("name");
                dbs.add(dbName);
                dbType.put(dbName, element.attributeValue("type"));
                for (Object collection : element.elements()) {
                    initCollection((Element) collection, dbName);
                }
            }
        }
    }

    private void initCollection(Element element, String dbName) {
        String collectionType = element.attributeValue("type");
        String collectionName = element.getText();
        this.dbOfCollection.put(collectionName, dbName);
        this.collections.put(collectionType, collectionName);
        this.collectionNames.put(collectionName, collectionType);
    }

    @Override
    public void reload() throws Exception {
        this.init();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String getTableName(Class<? extends DBClient> aClass) {
        return this.getCollectionName(aClass);
    }

    @Override
    public String getDBofTable(String s) {
        return this.getDBofCollection(s);
    }

    public String getCollectionName(Class<? extends DBClient> type) {
        return this.collections.get(type.getName());
    }

    public String getDBofCollection(String collectionName) {
        return this.dbOfCollection.get(collectionName);
    }

    public String getCollectionClassName(String name) {
        return this.collectionNames.get(name);
    }

    @Override
    public String getDBType(String name) {
        return this.dbType.get(name);
    }

    @Override
    public Set<String> getDbs() {
        return new HashSet<>(this.dbs);
    }

    @Override
    public HashMap<String, String> getTables() {
        return new HashMap<>(this.collections);
    }

    @Override
    public VirtualDBConnection buildConnection() {
        try {
            return this.connectionClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
