package model.config;

import org.dom4j.Element;

/**
 * Created by xlo on 16/4/1.
 * it's the connection config
 */
public class ConnectionConfig implements ConfigInterface {

    private String ip;
    private int port;
    private boolean needEncrypt;

    public static ConnectionConfig getConnectionConfig() {
        return (ConnectionConfig) ConfigManager.getConfigManager().getConfig(ConnectionConfig.class);
    }

    @Override
    public void init() throws Exception {
        Element root = ConfigInterface.getRootElement(ConfigManager.configPathConfig.getConfigFilePath(this.getClass()));
        for (Object object : root.elements()) {
            Element now = (Element) object;
            if (now.getName().equals("ip")) {
                this.setIp(now.getText());
            } else if (now.getName().equals("port")) {
                this.setPort(Integer.parseInt(now.getText()));
            } else if (now.getName().equals("encrypt")) {
                this.setNeedEncrypt(now.getText().equals("true"));
            }
        }
    }

    @Override
    public void reload() throws Exception {
        init();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isNeedEncrypt() {
        return needEncrypt;
    }

    public void setNeedEncrypt(boolean needEncrypt) {
        this.needEncrypt = needEncrypt;
    }
}
