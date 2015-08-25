package com.riccardonoviello.nullutils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author laptop
 */
public class NullSafeUtils {

    private static final Logger logger = Logger.getLogger(NullSafeUtils.class.getName());

    /**
     * 
     * @param object
     * @param members
     * @return 
     */
    public static boolean isAccessible(Object object, String... members) {
        return isAccessible(members, object, 0, members.length);
    }

    /**
     * 
     * @param members
     * @param object
     * @param found
     * @param originalSize
     * @return 
     */
    private static boolean isAccessible(String[] members, Object object, int found, final int originalSize) {
                
        if (members.length != 0) {
            try {
                Object o = PropertyUtils.getProperty(object, members[0]);
                if (o != null) {
                    return isAccessible(tailOfArray(members), o, found+1, originalSize);
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                logger.log(Level.SEVERE, "Error accessing Property.",ex);
            }
        }
        return (found == originalSize);
    }

    /**
     * 
     * @param <T>
     * @param object
     * @param members
     * @return 
     */
    public static <T>T get(Object object, String... members) {
        return get(members, object, 0, members.length);
    }

    /**
     * 
     * @param <T>
     * @param members
     * @param object
     * @param found
     * @param originalSize
     * @return 
     */
    private static <T> T get(String[] members, Object object, int found, final int originalSize) {
        Object o = null;
        if (members.length != 0) {
            try {
                 o = PropertyUtils.getProperty(object, members[0]);                 
                if (o != null) {
                    return get(tailOfArray(members), o, found+1, originalSize);
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                logger.log(Level.SEVERE, "Error accessing Property.",ex);
            }
        }
        return (T) ((found == originalSize) ? object : o);
    }
    
    
    /**
     * 
     * @param object
     * @param members
     * @return 
     */
    public static Alternable getOr(Object object, String... members) {
        Object result = get(members, object, 0, members.length);
        return new Alternable(result);        
    }    
    
    /**
     * Returns the tail array of arguments
     *
     * @param args
     * @return
     */
    private static String[] tailOfArray(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            list.add(args[i]);
        }
        return list.toArray(new String[list.size()]);
    }

}
