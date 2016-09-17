package com.acronym.ore.common.reference;

import javax.script.ScriptEngine;
import java.io.File;

/**
 * Created by Jared & Ewy
 */
public class Reference {

    public static final class ModInfo {
        public static final String MODID = "ore";
        public static final String NAME = "O.R.E";
        public static final String VERSION_MAJOR = "1";
        public static final String VERSION_MINOR = "0";
        public static final String VERSION_PATCH = "0";
        public static final String MINECRAFT_VERSION = "1.10.2";
        public static final String BUILD_VERSION = VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_PATCH + "-" + MINECRAFT_VERSION;
    }

    public static final class Paths {
        public static final String CLIENT_PROXY = "com.acronym.ore.proxy.ClientProxy";
        public static final String COMMON_PROXY = "com.acronym.ore.proxy.CommonProxy";
    }

    public static final class ConfigCategories {
        public static final String TOGGABLES = "Toggables";
        public static final String VALUES = "Values";
        public static final String DEBUG = "Debug";
    }

    public static final class Directories {
        public static File CONFIG_DIR;
        public static File SCRIPT_DIR;
        public static ScriptEngine ENGINE_JAVASCRIPT;
    }
}
