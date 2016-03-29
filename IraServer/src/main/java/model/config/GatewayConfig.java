package model.config;

import org.dom4j.Element;

/**
 * Created by xlo on 16/3/29.
 * it's the config for gateway
 */
public class GatewayConfig implements ConfigInterface {
    private String gatewayIp;

    public static GatewayConfig getConfig() {
        return (GatewayConfig) ConfigManager.getConfigManager().getConfig(GatewayConfig.class);
    }

    @Override
    public void init() throws Exception {
        Element root = ConfigInterface.getRootElement(ConfigManager.configPathConfig.getConfigFilePath(this.getClass()));
        this.gatewayIp = root.getText();
    }

    @Override
    public void reload() throws Exception {

    }

    public String getGatewayIp() {
        return gatewayIp;
    }
}
