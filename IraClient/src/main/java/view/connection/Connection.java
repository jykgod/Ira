package view.connection;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import model.config.ConnectionConfig;
import model.data.ConnectionData;
import net.server.Client;
import net.tool.connectionSolver.ConnectionMessageImpl;
import service.net.PackageSolver;
import view.PageManager;
import view.UIPage;
import view.tool.NumberTextField;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by xlo on 16/3/30.
 * it's the connection page
 */
public class Connection implements UIPage {
    private JPanel connectionPanel;
    private JTextField IPTextField;
    private JFormattedTextField portFormattedTextField;
    private JCheckBox useEncryptionCheckBox;
    private JButton connectButton;

    public Connection() {
        $$$setupUI$$$();
        connectButton.addActionListener(e -> {
            try {
                PageManager.getPageManager().setCurrentPageEnable(false);
                startClient();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        });
    }

    private void loadConnectionConfig() {
        this.IPTextField.setText(ConnectionConfig.getConnectionConfig().getIp());
        this.portFormattedTextField.setText(ConnectionConfig.getConnectionConfig().getPort() + "");
        this.useEncryptionCheckBox.setSelected(ConnectionConfig.getConnectionConfig().isNeedEncrypt());
    }

    private void startClient() throws IOException {
        Client client = Client.getNewClient("client");
        Client.getInstance("client", 2);
        PackageSolver packageSolver = new PackageSolver(new ConnectionMessageImpl());
        ConnectionData.getConnectionData().setPackageSolver(packageSolver);
        client.connect(this.IPTextField.getText(), Integer.parseInt(this.portFormattedTextField.getText()),
                packageSolver);
    }

    private void createUIComponents() {
        this.portFormattedTextField = new NumberTextField();
    }

    @Override
    public void setEnable(boolean enabled) {
        this.IPTextField.setEnabled(enabled);
        this.portFormattedTextField.setEnabled(enabled);
        this.useEncryptionCheckBox.setEnabled(enabled);
        this.connectButton.setEnabled(enabled);
    }

    @Override
    public void create() {
        loadConnectionConfig();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void sendMessage() {

    }

    @Override
    public JPanel getPanel() {
        return this.connectionPanel;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        connectionPanel = new JPanel();
        connectionPanel.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("IP");
        label1.setDisplayedMnemonic('I');
        label1.setDisplayedMnemonicIndex(0);
        connectionPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        IPTextField = new JTextField();
        connectionPanel.add(IPTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("port");
        label2.setDisplayedMnemonic('P');
        label2.setDisplayedMnemonicIndex(0);
        connectionPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        connectionPanel.add(portFormattedTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("encryption");
        label3.setDisplayedMnemonic('E');
        label3.setDisplayedMnemonicIndex(0);
        connectionPanel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        useEncryptionCheckBox = new JCheckBox();
        useEncryptionCheckBox.setText("use encryption");
        useEncryptionCheckBox.setMnemonic('U');
        useEncryptionCheckBox.setDisplayedMnemonicIndex(0);
        connectionPanel.add(useEncryptionCheckBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        connectButton = new JButton();
        connectButton.setText("connect");
        connectButton.setMnemonic('C');
        connectButton.setDisplayedMnemonicIndex(0);
        connectionPanel.add(connectButton, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        connectionPanel.add(spacer1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        label1.setLabelFor(IPTextField);
        label2.setLabelFor(portFormattedTextField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return connectionPanel;
    }
}
