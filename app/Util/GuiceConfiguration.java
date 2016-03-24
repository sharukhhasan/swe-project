import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class GuiceConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(OnStartupService.class).asEagerSingleton();
    }
    
}