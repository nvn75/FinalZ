package finalZ.exceptions;

import finalZ.module.ModuleInfo;

public class ExecutorMissingException extends LoadModuleException {

    public ExecutorMissingException(ModuleInfo moduleInfo) {
        super(moduleInfo);
    }

    @Override
    public String getMessage() {
        return "Executer not found" + super.getMessage();
    }
}
