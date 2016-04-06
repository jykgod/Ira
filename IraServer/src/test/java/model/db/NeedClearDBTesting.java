package model.db;

import model.config.IraDBConfig;

import java.util.Map;

/**
 * Created by xlo on 2015/11/26.
 * it's the interface clear db
 */
public interface NeedClearDBTesting {

    default void clearDB() throws Exception {
        for (Map.Entry<String, String> name : IraDBConfig.getConfig().getTables().entrySet()) {
            IraCollection walletCollection = (IraCollection) Class.forName(name.getKey()).newInstance();
            walletCollection.clearDB();
        }
    }
}
