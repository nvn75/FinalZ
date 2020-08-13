package game.modules;

import finalZ.annotation.Execute;
import finalZ.annotation.InstantiationPolicy;
import finalZ.annotation.Module;
import finalZ.annotation.Instantiate;

@Module({
        TestModule.DONE,
        TestModule.FAILED
})
@Instantiate(InstantiationPolicy.SINGLE)
public class TestModule {

    public static final String DONE = "DONE";
    public static final String FAILED = "FAILED";

    @Execute
    public String execute()
    {
        System.out.println("Doing task");
        return DONE;
    }
}
