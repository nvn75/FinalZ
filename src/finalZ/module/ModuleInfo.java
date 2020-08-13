package finalZ.module;

import finalZ.FlowEnv;
import finalZ.Log;
import finalZ.ModuleIF;
import finalZ.Port;
import finalZ.annotation.Execute;
import finalZ.annotation.Instantiate;
import finalZ.annotation.InstantiationPolicy;
import finalZ.annotation.Module;
import finalZ.exceptions.*;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class ModuleInfo {

    public static final String DEFAULT_PORT = "DONE";

    private String name;
    private Class<?> cls;
    private ArrayList<String> ports;
    private Method executor;
    private InstantiationPolicy policy;
    private Object instance;

    private ModuleIF moduleIF;

    public ModuleInfo(String name, Class<?> cls) throws LoadModuleException {
        this.name = name;
        this.cls = cls;
        ports = new ArrayList<String>(Arrays.asList(cls.getAnnotation(Module.class).value()));
        if (ports.isEmpty())
            ports.add(DEFAULT_PORT);
        Instantiate anno = cls.getAnnotation(Instantiate.class);
        if (anno == null) policy = InstantiationPolicy.SINGLE;
        else policy = anno.value();
        Set<Method> executors = ReflectionUtils.getAllMethods(cls, ReflectionUtils.withAnnotation(Execute.class));
        executor = CheckExecutor(executors);
        moduleIF = new ModuleIF(ports);

        Log.Debug("Module: " + getName() + ports + " {" + policy + "} " + executor.getName() + "()");
    }

    @Override
    public String toString() {
        return getName();
    }

    private Method CheckExecutor(Set<Method> executors) throws LoadModuleException {
        if (executors.size() == 0) throw new ExecutorMissingException(this);
        if (executors.size() >= 2) throw new DuplicateExcuterException(this);
        Method executor = executors.iterator().next();
        Class<?> returnType = executor.getReturnType();
        if (!String.class.isAssignableFrom(returnType)) throw new ExecutorHasWrongReturnType(this);
        return executor;
    }

    public Port getPort(int id) {
        return new Port(id, ports.get(id));
    }

    public int getPortId(String portName) throws PortNotExistsException
    {
        for (int i = 0; i < ports.size(); i++) {
            if (ports.get(i).equals(portName)) return i;
        }
        throw new PortNotExistsException(portName, this);
    }

    public ModuleIF getIF() {
        return moduleIF;
    }

    public String Execute(FlowEnv env) throws InvocationTargetException, IllegalAccessException {

        return (String)executor.invoke(getInstance(), env);
    }

    public String getName() {
        return name;
    }

    public InstantiationPolicy getPolicy() {
        return policy;
    }

    public Object getInstance() {
        try {
            switch (policy) {
                case SINGLE:
                    if (instance == null)
                        instance = cls.newInstance();
                    break;
                case MULTIPLE:
                    instance = cls.newInstance();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return instance;
    }
}


