package finalZ.exceptions;

import finalZ.module.ModuleInfo;

public class ExecutorHasWrongReturnType extends LoadModuleException {
    public ExecutorHasWrongReturnType(ModuleInfo moduleInfo) {
        super(moduleInfo);
    }

    @Override
    public String getMessage() {
        return "Executer musts return java.lang.String" + super.getMessage();
    }
}
