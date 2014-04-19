package util;

import org.apache.commons.lang3.StringUtils;

public class PropertyAccessor {
    public String getValue(String property, Object o) {
        try {
            String methodName = String.format("get%1$s", StringUtils.capitalize(property));

            return o.getClass().getMethod(methodName).invoke(o).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setValue(String property, Object o, String value) {
        try {
            String methodName = String.format("set%1$s", StringUtils.capitalize(property));
            o.getClass().getMethod(methodName).invoke(o, Integer.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
