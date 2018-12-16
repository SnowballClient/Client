package net.ilexiconn.llibrary;

import net.ilexiconn.llibrary.server.config.LLibraryConfig;
import net.ilexiconn.qubble.client.ClientProxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LLibrary {
    public static final String VERSION = "1.7.7";
    public static final Logger LOGGER = LogManager.getFormatterLogger("LLibrary");
    
    public static ClientProxy PROXY = new ClientProxy();

    
   // public static Capability<IEntityDataCapability> ENTITY_DATA_CAPABILITY;
    public static LLibraryConfig CONFIG = new LLibraryConfig();
   
    //public static SimpleNetworkWrapper NETWORK_WRAPPER;
    public static int QUBBLE_VERSION = 1;
    public static int QUBBLE_VANILLA_VERSION = 1;

   
}
