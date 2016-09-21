package com.acronym.ore.common.reference;

import java.io.File;

public class Reference {

    public static final class ModInfo {
        public static final String MODID = "ore";
        public static final String NAME = "O.R.E";
        public static final String UNACRONYMIZED_NAME = "ORE";
        static final String VERSION_MAJOR = "1";
        static final String VERSION_MINOR = "0";
        static final String VERSION_PATCH = "3";
        static final String MINECRAFT_VERSION = "1.10.2";
        public static final String BUILD_VERSION = VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_PATCH + "-" + MINECRAFT_VERSION;
    }

    public static final class ConfigCategories {
        public static final String FLATBEDROCK = "FLAT BEDROCK";
        public static final String DEBUG = "DEBUG";
        public static final String DISABLE_WORLDGEN = "DISABLE SPECIFIC ORE-GENERATION";
        public static final String DISABLE_ALL_WORLDGEN = "DISABLE ALL ORE-GENERATION";
    }

    public static final class Directories {
        public static File CONFIG_DIR;
        public static File SCRIPT_DIR;
    }
}
