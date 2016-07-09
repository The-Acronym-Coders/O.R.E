package com.ewyboy.ore.common.utility;

/** Created by EwyBoy **/

/**
 * Reference is a class storing strings in subclasses for easy access across the mod.
 **/
public class Reference {

    /**
     * ModInfo: Used to store general info about mod and its builds.*
     **/
    public static final class ModInfo {
        public static final String MOD_ID = "ore";
        public static final String MOD_NAME = "O.R.E";

        static final String VERSION_MAJOR = "1";
        static final String VERSION_MINOR = "0";
        static final String VERSION_PATCH = "0";
        public static final String MINECRAFT_VERSION = "1.9.4";
        public static final String BUILD_VERSION = VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_PATCH + "-" + MINECRAFT_VERSION;
    }

    /**
     * Path: Used to store paths.*
     **/
    public static final class Path {
        public static final String CLIENT_PROXY_PATH = "com.ewyboy.ore.proxy.ClientProxy";
        public static final String COMMON_PROXY_PATH = "com.ewyboy.ore.proxy.CommonProxy";
    }

}
