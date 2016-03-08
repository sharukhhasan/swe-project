package models;

import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.event.ServerConfigStartup;


public class EbeanInitialize implements ServerConfigStartup {
    public void onStart(ServerConfig serverConfig) {
        serverConfig.setDatabaseSequenceBatchSize(1);
    }
}