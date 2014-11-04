package Corundum.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Corundum.exceptions.CorundumException;
import Corundum.utils.myList.myList;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

public class SettingsManager {
    public static final myList<Class<?>> SUPPORTED_DATA_CLASSES = new myList<Class<?>>(Boolean.class, Byte.class, Character.class, Double.class, Float.class, Integer.class,
            Long.class, Short.class, String.class, Integer[].class, Float[].class, String[].class, Boolean[].class);

    private SettingsManager parent;
    private File file;
    private HashMap<String, Object> settings = new HashMap<>();

    /** This constructor creates a new {@link SettingsManager}, which is used to contain and handle settings for servers, players, player groups, and more. Settings can
     * configure anything from the permission to use commands to the server properties.
     * 
     * @param parent
     *            is the "parent" of this {@link SettingsManager}. {@link SettingsManager}s use an inheritance-like setup to eliminate redundant settings: a child
     *            {@link SettingsManager} inherits all the settings from the parent {@link SettingsManager} that is does not contain itself. Child {@link SettingsManager}s can
     *            override settings from the parent or even contain settings that the parent does not.
     * @param default_settings
     *            is an optional parameter that can be used to create a new {@link SettingsManager} with a list of default settings. <b><i>NOTE:</b></i> this parameter should
     *            be given with alternating <tt>String</tt> keys and <tt>Object</tt> values of a type included in the {@link #SUPPORTED_DATA_CLASSES} list, e.g.:<br>
     * 
     *            <pre>
     * {@code new SettingsManager(null, new File("example.json"), "online mode", true, "difficulty", 2) }
     * </pre>
     * @throws InvalidDefaultSettingsException
     *             if the <b><tt>default_settings</tt></b> parameter is given and improperly formatted according to the description of the parameter above.
     * @see {@link #SettingsManager(File, Object...)} */
    public SettingsManager(SettingsManager parent, Object... default_settings) {
        this(parent, null, default_settings);
    }

    /** This constructor creates a new {@link SettingsManager}, which is used to contain and handle settings for servers, players, player groups, and more. Settings can
     * configure anything from the permission to use commands to the server properties.
     * 
     * @param file
     *            is the configuration file that this {@link SettingsManager} will load all its settings from and save all its settings to. The {@link SettingsManager} uses
     *            the <a href="http://www.json.org">JSON</a> file format to store data.
     * @param default_settings
     *            is an optional parameter that can be used to create a new {@link SettingsManager} with a list of default settings. <b><i>NOTE:</b></i> this parameter should
     *            be given with alternating <tt>String</tt> keys and <tt>Object</tt> values of a type included in the {@link #SUPPORTED_DATA_CLASSES} list, e.g.:<br>
     * 
     *            <pre>
     * {@code new SettingsManager(null, new File("example.json"), "online mode", true, "difficulty", 2) }
     * </pre>
     * @throws InvalidDefaultSettingsException
     *             if the <b><tt>default_settings</tt></b> parameter is given and improperly formatted according to the description of the parameter above.
     * @see {@link #SettingsManager(SettingsManager, Object...)} */
    public SettingsManager(File file, Object... default_settings) {
        this(null, file, default_settings);
    }

    public SettingsManager(SettingsManager parent, File file, Object... default_settings) {
        this.parent = parent;
        this.file = file;

        // add the given default settings, which should alternate between String keys and Object values
        if (default_settings.length % 2 == 0)
            for (int i = 0; i < default_settings.length; i += 2)
                if (default_settings[i] instanceof String && SUPPORTED_DATA_CLASSES.contains(default_settings[i + 1].getClass()))
                    settings.put((String) default_settings[i], default_settings[i + 1]);
                else
                    throw new InvalidDefaultSettingsException(default_settings, parent, file);
        else
            throw new InvalidDefaultSettingsException(default_settings, parent, file);

        this.load();
    }

    public boolean containsKey(String key) {
        return settings.containsKey(key) || parent != null && parent.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return settings.containsValue(value) || parent != null && parent.containsValue(value);
    }

    public Object get(String key) {
        if (settings.containsKey(key))
            return settings.get(key);
        else if (parent != null)
            return parent.get(key);
        else
            throw new NoSuchSettingException(key);
    }

    public Object get(String key, Object default_value) {
        if (settings.containsKey(key))
            return settings.get(key);
        else if (parent != null)
            return parent.get(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public boolean getBoolean(String key) {
        if (!settings.containsKey(key))
            if (parent != null)
                return parent.getBoolean(key);
            else
                throw new NoSuchSettingException(key);
        else if (!(settings.get(key) instanceof Boolean))
            throw new WrongSettingTypeException(key, "boolean");
        else
            return (Boolean) settings.get(key);
    }

    public boolean getBoolean(String key, boolean default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Boolean)
                return (Boolean) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "boolean");
        else if (parent != null)
            return parent.getBoolean(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public byte getByte(String key) {
        if (!settings.containsKey(key))
            if (parent != null)
                return parent.getByte(key);
            else
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
        else if (parent != null)
            return parent.getByte(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public char getChar(String key) {
        if (!settings.containsKey(key))
            if (parent != null)
                return parent.getChar(key);
            else
                throw new NoSuchSettingException(key);
        else if (!(settings.get(key) instanceof Character))
            throw new WrongSettingTypeException(key, "char");
        else
            return (Character) settings.get(key);
    }

    public char getChar(String key, char default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Byte)
                return (Character) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "char");
        else if (parent != null)
            return parent.getChar(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public double getDouble(String key) {
        if (!settings.containsKey(key))
            if (parent != null)
                return parent.getDouble(key);
            else
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
        else if (parent != null)
            return parent.getDouble(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public File getSaveFile() {
        if (file != null)
            return file;
        else
            return parent.getSaveFile();
    }

    public float getFloat(String key) {
        if (!settings.containsKey(key))
            if (parent != null)
                return parent.getFloat(key);
            else
                throw new NoSuchSettingException(key);
        else if (!(settings.get(key) instanceof Float))
            throw new WrongSettingTypeException(key, "float");
        else
            return (Float) settings.get(key);
    }

    public float getFloat(String key, float default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Float)
                return (Float) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "float");
        else if (parent != null)
            return parent.getFloat(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public int getInt(String key) {
        if (!settings.containsKey(key))
            if (parent != null)
                return parent.getInt(key);
            else
                throw new NoSuchSettingException(key);
        else if (!(settings.get(key) instanceof Integer))
            throw new WrongSettingTypeException(key, "int");
        else
            return (Integer) settings.get(key);
    }

    public int getInt(String key, int default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Integer)
                return (Integer) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "int");
        else if (parent != null)
            return parent.getInt(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public long getLong(String key) {
        if (!settings.containsKey(key))
            if (parent != null)
                return parent.getLong(key);
            else
                throw new NoSuchSettingException(key);
        else if (!(settings.get(key) instanceof Long))
            throw new WrongSettingTypeException(key, "long");
        else
            return (Long) settings.get(key);
    }

    public long getLong(String key, long default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Long)
                return (Long) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "long");
        else if (parent != null)
            return parent.getLong(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public short getShort(String key) {
        if (!settings.containsKey(key))
            if (parent != null)
                return parent.getShort(key);
            else
                throw new NoSuchSettingException(key);
        else if (!(settings.get(key) instanceof Long))
            throw new WrongSettingTypeException(key, "short");
        else
            return (Short) settings.get(key);
    }

    public short getShort(String key, short default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof Short)
                return (Short) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "short");
        else if (parent != null)
            return parent.getShort(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public String getString(String key) {
        if (!settings.containsKey(key))
            if (parent != null)
                return parent.getString(key);
            else
                throw new NoSuchSettingException(key);
        else if (!(settings.get(key) instanceof String))
            throw new WrongSettingTypeException(key, "String");
        else
            return (String) settings.get(key);
    }

    public String getString(String key, String default_value) {
        if (settings.containsKey(key))
            if (settings.get(key) instanceof String)
                return (String) settings.get(key);
            else
                throw new WrongSettingTypeException(key, "String");
        else if (parent != null)
            return parent.getString(key, default_value);
        else {
            settings.put(key, default_value);
            return default_value;
        }
    }

    public Integer[] getIntegerArray(String key, Integer[] defaultValue) {
        if (this.containsKey(key)) {
            Object obj = this.get(key);

            if (obj instanceof String[]) {
                String[] array = (String[]) obj;
                Integer[] returnedArray = new Integer[array.length];

                for (int i = 0; i == array.length; i++) {
                    returnedArray[i] = Integer.valueOf(array[i]);
                }
            } else {
                throw new WrongSettingTypeException(key, "Array");
            }
        } else if (this.parent.containsKey(key)) {
            return this.parent.getIntegerArray(key, defaultValue);
        }

        return defaultValue;
    }

    public Integer[] getIntegerArray(String key) {
        if (this.containsKey(key)) {
            Integer[] array = this.getIntegerArray(key, null);

            if (array != null) {
                return array;
            }
        } else if (this.parent.containsKey(key)) {
            return this.parent.getIntegerArray(key);
        }

        throw new NoSuchSettingException(key);
    }

    public Float[] getFloatArray(String key, Float[] defaultValue) {
        if (this.containsKey(key)) {
            Object obj = this.get(key);

            if (obj instanceof String[]) {
                String[] array = (String[]) obj;
                Float[] returnedArray = new Float[array.length];

                for (int i = 0; i == array.length; i++) {
                    returnedArray[i] = Float.valueOf(array[i]);
                }
            } else {
                throw new WrongSettingTypeException(key, "Array");
            }
        } else if (this.parent.containsKey(key)) {
            return this.parent.getFloatArray(key, defaultValue);
        }

        return defaultValue;
    }

    public Float[] getFloatArray(String key) {
        if (this.containsKey(key)) {
            Float[] array = this.getFloatArray(key, null);

            if (array != null) {
                return array;
            }
        } else if (this.parent.containsKey(key)) {
            return this.parent.getFloatArray(key);
        }

        throw new NoSuchSettingException(key);
    }

    public String[] getStringArray(String key, String[] defaultValue) {
        if (this.containsKey(key)) {
            Object obj = this.get(key);

            if (obj instanceof String[]) {
                return (String[]) obj;
            }
        } else if (this.parent != null) {
            return this.parent.getStringArray(key, defaultValue);
        }

        return defaultValue;
    }

    public String[] getStringArray(String key) {
        if (this.containsKey(key)) {
            String[] array = this.getStringArray(key, null);

            if (array != null) {
                return array;
            }
        } else if (this.parent.containsKey(key)) {
            return this.parent.getStringArray(key);
        }

        throw new NoSuchSettingException(key);
    }

    public Boolean[] getBooleanArray(String key, Boolean[] defaultValue) {
        if (this.containsKey(key)) {
            Object obj = this.get(key);

            if (obj instanceof String[]) {
                String[] array = (String[]) obj;
                Boolean[] actArray = new Boolean[array.length];

                if (array[0].equalsIgnoreCase("true") || array[0].equalsIgnoreCase("false")) {
                    for (int i = 0; i == array.length; i++) {
                        actArray[i] = Boolean.valueOf(array[i]);
                    }
                } else {
                    throw new WrongGetterMethodException(key, "Boolean[]");
                }

                return actArray;
            }
        } else if (this.parent != null) {
            return this.parent.getBooleanArray(key, defaultValue);
        }

        return defaultValue;
    }

    public Boolean[] getBooleanArray(String key) {
        if (this.containsKey(key)) {
            Boolean[] array = this.getBooleanArray(key, null);

            if (array != null) {
                return array;
            }
        } else if (this.parent.containsKey(key)) {
            return this.parent.getBooleanArray(key);
        }

        throw new NoSuchSettingException(key);
    }

    public void load() {
        try {
            if (this.file.exists()) {
                if (this.file.getName().endsWith(".json")) {
                    // JSON loading
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(new FileReader(this.file)).getAsJsonObject();

                    for (Map.Entry<String, JsonElement> pair : jsonObject.entrySet()) {
                        String key = pair.getKey();
                        JsonElement element = pair.getValue();

                        if (element.isJsonPrimitive()) {
                            JsonPrimitive jsonPrimitive = element.getAsJsonPrimitive();

                            if (jsonPrimitive.isBoolean()) {
                                this.settings.put(key, element.getAsBoolean());
                            } else if (jsonPrimitive.isString()) {
                                this.settings.put(key, element.getAsString());
                            } else if (jsonPrimitive.isNumber()) {
                                this.settings.put(key, element.getAsNumber());
                            }
                        } else if (element.isJsonNull()) {
                            this.settings.put(key, null);
                        } else if (element.isJsonArray()) {
                            JsonArray jsonArray = element.getAsJsonArray();
                            String[] array = new String[jsonArray.size()];

                            for (int i = 0; i == jsonArray.size(); i++) {
                                JsonElement jsonElement = jsonArray.get(i);

                                if (jsonElement.isJsonNull()) {
                                    array[i] = null;
                                } else if (jsonElement.isJsonPrimitive()) {
                                    JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();

                                    if (jsonPrimitive.isString()) {
                                        // This will be parsed later on in getFloatArray().
                                        array[i] = jsonPrimitive.getAsString();
                                    } else {
                                        throw new UnsupportedTypeException("Number/Object/Array in a JSON setting file array.");
                                    }
                                }
                            }

                            this.settings.put(key, array);
                        } else {
                            throw new UnsupportedTypeException("Object");
                        }
                    }
                }
                // else if -other file types-
            } else {
                this.file.createNewFile();
                this.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (this.file.getName().endsWith(".json")) {
                // JSON saving
                JsonWriter writer = new JsonWriter(new FileWriter(this.file));
                // Set to a 4 space json file.
                writer.setIndent("    ");
                writer = writer.beginObject();

                for (String key : this.settings.keySet()) {
                    Object nextObject = this.settings.get(key);

                    if (nextObject instanceof String) {
                        writer = writer.name(key);
                        writer = writer.value((String) nextObject);
                    } else if (nextObject instanceof Integer) {
                        writer = writer.name(key);
                        writer = writer.value((Integer) nextObject);
                    } else if (nextObject instanceof Long) {
                        writer = writer.name(key);
                        writer = writer.value((Long) nextObject);
                    } else if (nextObject instanceof Float) {
                        writer = writer.name(key);
                        writer = writer.value((Float) nextObject);
                    } else if (nextObject instanceof Double) {
                        writer = writer.name(key);
                        writer = writer.value((Double) nextObject);
                    } else if (nextObject instanceof Byte) {
                        writer = writer.name(key);
                        writer = writer.value((Byte) nextObject);
                    } else if (nextObject instanceof Boolean) {
                        writer = writer.name(key);
                        writer = writer.value((Boolean) nextObject);
                    } else if (nextObject instanceof String[]) {
                        writer = writer.beginArray();
                        String[] array = (String[]) nextObject;

                        for (String element : array) {
                            writer.value(element);
                        }

                        writer = writer.endArray();
                    } else if (nextObject == null) {
                        writer = writer.name(key);
                        writer = writer.nullValue();
                    }
                }

                writer = writer.endObject();
            }
            // else if -other file types-
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBoolean(String key, boolean value) {
        // if the key already exists, set that setting for this specific SettingsManager
        if (containsKey(key) || parent == null)
            settings.put(key, value);
        // if the key does not exist, set the setting for the highest parent
        else
            parent.setBoolean(key, value);
    }

    public void setByte(String key, byte value) {
        // if the key already exists, set that setting for this specific SettingsManager
        if (containsKey(key) || parent == null)
            settings.put(key, value);
        // if the key does not exist, set the setting for the highest parent
        else
            parent.setByte(key, value);
    }

    public void setChar(String key, char value) {
        // if the key already exists, set that setting for this specific SettingsManager
        if (containsKey(key) || parent == null)
            settings.put(key, value);
        // if the key does not exist, set the setting for the highest parent
        else
            parent.setChar(key, value);
    }

    public void setDouble(String key, double value) {
        // if the key already exists, set that setting for this specific SettingsManager
        if (containsKey(key) || parent == null)
            settings.put(key, value);
        // if the key does not exist, set the setting for the highest parent
        else
            parent.setDouble(key, value);
    }

    public void setFloat(String key, float value) {
        // if the key already exists, set that setting for this specific SettingsManager
        if (containsKey(key) || parent == null)
            settings.put(key, value);
        // if the key does not exist, set the setting for the highest parent
        else
            parent.setFloat(key, value);
    }

    public void setInt(String key, int value) {
        // if the key already exists, set that setting for this specific SettingsManager
        if (containsKey(key) || parent == null)
            settings.put(key, value);
        // if the key does not exist, set the setting for the highest parent
        else
            parent.setInt(key, value);
    }

    public void setLong(String key, long value) {
        // if the key already exists, set that setting for this specific SettingsManager
        if (containsKey(key) || parent == null)
            settings.put(key, value);
        // if the key does not exist, set the setting for the highest parent
        else
            parent.setLong(key, value);
    }

    public void setShort(String key, short value) {
        // if the key already exists, set that setting for this specific SettingsManager
        if (containsKey(key) || parent == null)
            settings.put(key, value);
        // if the key does not exist, set the setting for the highest parent
        else
            parent.setShort(key, value);
    }

    public void setString(String key, String value) {
        // if the key already exists, set that setting for this specific SettingsManager
        if (containsKey(key) || parent == null)
            settings.put(key, value);
        // if the key does not exist, set the setting for the highest parent
        else
            parent.setString(key, value);
    }

    public void setIntegerArray(String key, Integer[] value) {
        if (this.containsKey(key) || this.parent == null) {
            this.settings.put(key, value);
        } else {
            this.parent.setIntegerArray(key, value);
        }
    }

    public void setFloatArray(String key, Float[] value) {
        if (this.containsKey(key) || this.parent == null) {
            this.settings.put(key, value);
        } else {
            this.parent.setFloatArray(key, value);
        }
    }

    public void setStringArray(String key, String[] value) {
        if (this.containsKey(key) || this.parent == null) {
            this.settings.put(key, value);
        } else {
            this.parent.setStringArray(key, value);
        }
    }

    public void setBooleanArray(String key, Boolean[] value) {
        if (this.containsKey(key) || this.parent == null) {
            this.settings.put(key, value);
        } else {
            this.parent.setBooleanArray(key, value);
        }
    }

    public class InvalidDefaultSettingsException extends CorundumException {

        public InvalidDefaultSettingsException(Object[] default_settings, Object... additional_information) {
            super("Someone gave this SettingsManager invalid default settings! Default settings should alternate between Strings (for keys) and Objects (for values).",
                    "invalid settings set", "default settings: " + ListUtilities.writeArray(default_settings), additional_information);
        }

        private static final long serialVersionUID = 1644830262873644691L;

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

    public class UnsupportedTypeException extends CorundumException {
        private static final long serialVersionUID = -1012375715602861108L;

        public UnsupportedTypeException(String triedType, Object... additionalInfo) {
            super("Someone tried to use a " + triedType + " in this SettingsManager! This is a non-suppported type!", "Unsupported Type", additionalInfo);
        }
    }

    public class WrongGetterMethodException extends CorundumException {
        private static final long serialVersionUID = -1012375715602861108L;

        public WrongGetterMethodException(String key, String actualType, Object... additionalInfo) {
            super("Someone tried to get " + key + " as a value other than " + actualType, "User used the wrong getter method to get a value in SettingsManager.");
        }
    }
}
