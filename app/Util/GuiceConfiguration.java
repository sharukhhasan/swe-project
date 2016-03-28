package Util;

import com.google.inject.AbstractModule;

public class GuiceConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(OnStartupService.class).asEagerSingleton();
    }
    
}