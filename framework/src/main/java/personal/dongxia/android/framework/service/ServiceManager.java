package personal.dongxia.android.framework.service;

import androidx.annotation.MainThread;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

// todo 单例
public class ServiceManager {
    private Map<Class<?>, Object> globalServices = new Hashtable<>();

    private Set<ServiceCallback> callbacks = new HashSet<>();

    public <T> T getService(Class<T> clazz) {
        return getService(clazz, true);
    }

    public <T> T getService(Class<T> clazz, boolean toRoot) {
        if (clazz == null) {
            return null;
        }
        Object o = globalServices.get(clazz);
        if (o != null) {
            return (T) o;
        }
        if (toRoot) {
            Class[] clazzes = getSuperClasses(clazz);
            for (Class superClass : clazzes) {
                o = globalServices.get(superClass);
                if (o != null) {
                    break;
                }
            }
        }
        return o != null ? (T) o : null;
    }


    @MainThread
    public <T> void registerGlobalService(Class<?> clazz, T service) {
        registerGlobalService(clazz, service, true);
    }

    @MainThread
    public <T> void registerGlobalService(Class<?> clazz, T service, boolean toRoot) {
        if (clazz == null || service == null) {
            return;
        }

        registerGlobalService(getSuperClasses(clazz), service);
    }

    @MainThread
    public <T> void registerGlobalService(Class<?>[] clazzes, T service) {
        if (clazzes == null || clazzes.length == 0 || service == null) {
            return;
        }

        for (Class<?> clazz : clazzes) {
            if (clazz.isInstance(service)) {
                Object old = globalServices.put(clazz, service);
                if (old != service) {
                    if (old != null) {
                        notifyUnregister(clazz, old);
                    }
                    notifyRegister(clazz, service);
                }
            }
        }
    }

    private void notifyRegister(Class<?> clazz, Object service) {
        for (ServiceCallback callback : callbacks) {
            callback.onServiceRegister(clazz, service);
        }
    }

    @MainThread
    public void unregisterGlobalService(Class<?> clazz) {
        unregisterGlobalService(clazz, true);
    }

    @MainThread
    public void unregisterGlobalService(Class<?> clazz, boolean toRoot) {
        if (clazz == null) {
            return;
        }
        unregisterGlobalService(getSuperClasses(clazz));
    }

    @MainThread
    public void unregisterGlobalService(Class<?>[] clazzes) {
        if (clazzes == null || clazzes.length == 0) {
            return;
        }

        for (Class<?> clazz : clazzes) {
            Object old = globalServices.remove(clazz);
            if (old != null) {
                notifyUnregister(clazz, old);
            }
        }
    }

    private void notifyUnregister(Class<?> clazz, Object service) {
        for (ServiceCallback callback : callbacks) {
            callback.onServiceUnregister(clazz, service);
        }
    }

    private Class[] getSuperClasses(Class clazz) {
        HashSet<Class> classes = new HashSet<>();
        classes.add(clazz);
        dfsSuperClasses(clazz, classes);
        return classes.toArray(new Class[]{});
    }

    /**
     * 深度优先遍历获取所有的父类
     * @param clazz
     * @param visited
     */
    private void dfsSuperClasses(Class clazz, HashSet<Class> visited) {
        Class[] classes = clazz.getInterfaces();
        for (Class superClass : classes) {
            if (!visited.contains(superClass)) {
                visited.add(superClass);
                dfsSuperClasses(superClass, visited);
            }
        }
    }
}
