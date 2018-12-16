package net.ilexiconn.qubble;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import net.ilexiconn.qubble.client.ClientProxy;
import net.ilexiconn.qubble.server.config.QubbleConfig;


public class Qubble {
    public static final String MODID = "qubble";
    public static final String VERSION = "1.0.0";
    public static final String LLIBRARY_VERSION = "1.7.6";

    
    public static ClientProxy PROXY = new ClientProxy();
   
    public static QubbleConfig CONFIG = new QubbleConfig();

    public static DecimalFormat DEFAULT_FORMAT;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        DEFAULT_FORMAT = new DecimalFormat("#.##", symbols);
    }

  
    
}
