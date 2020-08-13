package finalZ;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import finalZ.annotation.Execute;
import finalZ.annotation.Module;
import finalZ.exceptions.ExecuteException;
import finalZ.exceptions.LoadModuleException;
import finalZ.exceptions.ModuleNotExistsException;
import finalZ.flow.AFlow;
import finalZ.module.ModuleInfo;
import game.flows.TapToPlayFow;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

//TODO Module ID unique in forest (hash from path)
//TODO Trace path
//TODO Flow Env
//TODO Bug draw tree (CheckCRC -> FAILED first) done
//TODO Module require input
//TODO Load json to create tree
//TODO json handle duplicate module (tag)
//-> GetChild by order in json file. Solved: make json file simple (10). Problem: not friendly for human (-3).
//-> Use tag. Solved: friendly for human (3). Problem: managing tags is complicated (-5).
//TODO Check signature of executer
//TODO Call another flow
public class FinalZ {

	private static FinalZ instance;

	public static FinalZ Instance()
	{
		if (instance == null) instance = new FinalZ();
		return instance;
	}

	private static HashMap<String, ModuleInfo> moduleInfos;

	public static void main(String[] agrs)
	{
		FinalZ.Instance().Initialize();
		try {
			ExecuteFlow(new TapToPlayFow());
		} catch (ExecuteException e) {
			e.printStackTrace();
		}
	}

	public void Initialize()
	{
        Reflections reflections = new Reflections(new MethodAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner());
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Module.class);
        moduleInfos = new HashMap<>();
		Iterator<Class<?>> it = annotated.iterator();
        while (it.hasNext()) {
			Class<?> cls = it.next();
			String clsName = cls.getName();
			try {
				moduleInfos.put(clsName, new ModuleInfo(clsName, cls));
			} catch (LoadModuleException e) {
				e.printStackTrace();
			}
		}
	}

	public ModuleInfo getModuleInfo(String moduleName) throws ModuleNotExistsException
	{
		if (moduleInfos.containsKey(moduleName))
			return moduleInfos.get(moduleName);
		throw new ModuleNotExistsException(moduleName);
	}

	private Object getInstace(String moduleName) throws ModuleNotExistsException {
		return getModuleInfo(moduleName).getInstance();
	}
	
	public static ArrayList<String> ExecuteFlow(AFlow flow) throws ExecuteException
	{
		return new FlowEnv().ExecuteFlow(flow);
	}
}

