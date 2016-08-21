package com.acronym.ore.proxy;

import com.acronym.ore.reference.Reference;

import javax.script.ScriptEngineManager;

/**
 * Created by Jared on 8/21/2016.
 */
public class CommonProxy {

    public void initEngines(){
        Reference.ENGINE_JAVASCRIPT = new ScriptEngineManager().getEngineByName("JavaScript");//getEngineByExtension("JavaScript");
    }

}
