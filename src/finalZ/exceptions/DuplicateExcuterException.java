package finalZ.exceptions;

import finalZ.module.ModuleInfo;

public class DuplicateExcuterException extends LoadModuleException {

    public DuplicateExcuterException(ModuleInfo moduleInfo) {
        super(moduleInfo);
    }

    @Override
    public String getMessage() {
        return "Module can only have one executer" + super.getMessage();
    }
}
