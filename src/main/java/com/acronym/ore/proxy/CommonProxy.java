package com.acronym.ore.proxy;

import com.acronym.ore.common.helpers.Logger;

import javax.script.ScriptEngineManager;

import static com.acronym.ore.common.reference.Reference.Directories.ENGINE_JAVASCRIPT;

/**
 * Created by Jared on 8/21/2016.
 */
public class CommonProxy {

    public void registerKeybindings() {}

    public void initEngines(){
        ScriptEngineManager manager = new ScriptEngineManager();
        ENGINE_JAVASCRIPT = manager.getEngineByExtension("js");
        Logger.info(">>> " + ENGINE_JAVASCRIPT);
    }
}
