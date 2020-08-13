package finalZ.exceptions;

import finalZ.module.ModuleInfo;

public class PortNotExistsException extends Exception {

    String portName;
    ModuleInfo moduleInfo;

    public PortNotExistsException(String portName, ModuleInfo moduleInfo) {
        this.portName = portName;
        this.moduleInfo = moduleInfo;
    }

    @Override
    public String getMessage() {
        return portName + " port does not exists in module " + moduleInfo.getName();
    }
}
