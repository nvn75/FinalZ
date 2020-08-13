package finalZ.exceptions;

import finalZ.module.ModuleInfo;

public class ModuleNotExistsException extends Exception {

    String moduleName;

    public ModuleNotExistsException(String moduleName)
    {
        this.moduleName = moduleName;
    }

    @Override
    public String getMessage() {
        return "Module does not exist: " + moduleName;
    }
}
