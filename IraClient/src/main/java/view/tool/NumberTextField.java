package view.tool;

import javax.swing.*;
import java.text.NumberFormat;

/**
 * Created by xlo on 16-1-12.
 * it's the number text field
 */
public class NumberTextField extends JFormattedTextField {

    public NumberTextField() {
        super(NumberFormat.getInstance());
    }

    @Override
    public String getText() {
        String s = super.getText();
        String[] parts = s.split(",");
        String result = "";
        for (String now : parts) {
            result += now;
        }
        return result;
    }
}
