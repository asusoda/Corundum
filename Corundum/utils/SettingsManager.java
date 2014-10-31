package Corundum.utils;

import java.util.HashMap;

import Corundum.exceptions.CorundumException;

public class SettingsManager {
    public HashMap<String, Object> settings = new HashMap<String, Object>();

    public boolean containsKey(String key) {
        return settings.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return settings.containsValue(value);
    }

    public Object get(String key) {
        if (settings.containsKey(key))
            return settings.get(key);
        else
            throw new NoSuchSettingException(key);
    }

    public Object get(String key, Object default_value) {
        if (settings.containsKey(key))
            return settings.get(key);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public boolean getBoolean(String key, boolean default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Boolean)
                return (Boolean) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "boolean");
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public byte getByte(String key) {
        if (!settings.containsKey(key))
            throw new NoSuchSettingException(key);
        else if (!(settings.get(key) instanceof Byte))
            throw new WrongSettingTypeException(key, "byte");
        else
            return (Byte) settings.get(key);
    }

    public byte getByte(String key, byte default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Byte)
                return (Byte) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "byte");
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public double getDouble(String key) {
        if (!settings.containsKey(key))
            throw new NoSuchSettingException(key);
        else if (!(settings.get(key) instanceof Double))
            throw new WrongSettingTypeException(key, "double");
        else
            return (Double) settings.get(key);
    }

    public double getDouble(String key, double default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Double)
                return (Double) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "double");
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public float getFloat(String key, float default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Float)
                return (Float) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "float");
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public String getString(String key, String default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof String)
                return (String) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "String");
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public class NoSuchSettingException extends CorundumException {
        private static final long serialVersionUID = -1012375715602861108L;

        public NoSuchSettingException(String key, Object... additional_information) {
            super("Someone tried to retrieve the setting \"" + key + "\" that doesn't exist without giving a default value!", "unknown setting retrieval",
                    additional_information);
        }

    }

    public class WrongSettingTypeException extends CorundumException {
        private static final long serialVersionUID = -1012375715602861108L;

        public WrongSettingTypeException(String key, String wrong_type, Object... additional_information) {
            super("Someone tried to retrieve the setting \"" + key + "\" as " + StringUtilities.aOrAn(wrong_type), "wrong-typed setting retrieval", additional_information);
        }

    }
}
