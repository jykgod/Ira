package model.config;

import model.db.UserLicenseCollection;
import org.dom4j.Element;

import java.util.*;

/**
 * Created by xlo on 2015/11/4.
 * it's the user access config
 */
public class UserAccessConfig implements ConfigInterface {

    private Map<String, Set<String>> access;

    public static UserAccessConfig getConfig() {
        return (UserAccessConfig) ConfigManager.getConfigManager().getConfig(UserAccessConfig.class);
    }

    protected UserAccessConfig() {
        this.access = new HashMap<>();
    }

    @Override
    public void init() throws Exception {
        Element root = ConfigInterface.getRootElement(ConfigManager.configPathConfig.getConfigFilePath(this.getClass()));
        List node = root.elements();
        for (Object now : node) {
            Element element = (Element) now;
            this.access.put(element.attributeValue("command"), new HashSet<>());
            List needs = element.elements();
            for (Object object : needs) {
                Element need = (Element) object;
                this.access.get(element.attributeValue("command")).add(need.getText());
            }
        }
    }

    @Override
    public void reload() throws Exception {
        this.access.clear();
        init();
    }

    public Set<String> getNeedAccess(String command) {
        if (this.access.containsKey(command)) {
            return this.access.get(command);
        } else {
            return new HashSet<>();
        }
    }

    public boolean haveLicense(String username, String command) {
        UserLicenseCollection userLicenseCollection = new UserLicenseCollection();
        Set<String> need = this.getNeedAccess(command);
        for (String now : need) {
            if (!userLicenseCollection.haveLicense(username, now)) {
                return false;
            }
        }
        return true;
    }
}
