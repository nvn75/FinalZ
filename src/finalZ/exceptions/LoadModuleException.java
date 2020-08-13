package finalZ.exceptions;

import finalZ.module.ModuleInfo;

public class LoadModuleException extends Exception {

    ModuleInfo moduleInfo;

    public LoadModuleException(ModuleInfo moduleInfo)
    {
        this.moduleInfo = moduleInfo;
    }

    @Override
    public String getMessage() {
        return " at module: " + moduleInfo.getName();
    }
}
