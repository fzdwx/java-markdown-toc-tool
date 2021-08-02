package org.atomicoke.mdtoc;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import org.atomicoke.mdtoc.handler.MdTocToolArgHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Description: md toc tool cli 参数处理程序调用 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-08-02 17:02:14
 */
public class MdTocToolArgHandlerInvocation {

    private final static List<MdTocToolArgHandler> handlers = new ArrayList<>();

    static {
        // load
        loadHandler();
    }

    /**
     * 调用方法
     *
     * @param mdTocToolCliArg md toc 命令行参数
     */
    public static void invoke(MdTocToolCliArg mdTocToolCliArg) {
        handlers.forEach(handler -> {
            if (handler.test(mdTocToolCliArg)) {
                handler.handler(mdTocToolCliArg);
            }
        });
    }

    // private methods

    /**
     * 加载 handler
     */
    static void loadHandler() {
        try {
            MdTocToolArgHandlerInvocation.getAllAssignedClass(MdTocToolArgHandler.class)
                                         .forEach(clazz -> {
                                             handlers.add(ReflectUtil.newInstance(clazz));
                                         });
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // copy method

    /**
     * 获取同一路径下所有子类或接口实现类
     *
     * @param clazz
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static <Type> List<Class<Type>> getAllAssignedClass(Class<Type> clazz) throws IOException,
            ClassNotFoundException {
        List<Class<Type>> classes = new ArrayList<>();
        for (Class<Type> c : MdTocToolArgHandlerInvocation.getClasses(clazz)) {
            if (clazz.isAssignableFrom(c) && !clazz.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }

    /**
     * 取得当前类路径下的所有类
     *
     * @param clazz
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static <Type> List<Class<Type>> getClasses(Class<Type> clazz) throws IOException,
            ClassNotFoundException {
        String pk = clazz.getPackage().getName();
        String path = pk.replace('.', '/');
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(path);
        return getClasses(new File(url.getFile()), pk);
    }

    /**
     * 迭代查找类
     *
     * @param dir
     * @param pk
     * @return
     * @throws ClassNotFoundException
     */
    static <Type> List<Class<Type>> getClasses(File dir, String pk) throws ClassNotFoundException {
        List<Class<Type>> classes = new ArrayList<>();
        if (!dir.exists()) {
            return classes;
        }
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                classes.addAll(MdTocToolArgHandlerInvocation.getClasses(f, pk + "." + f.getName()));
            }
            String name = f.getName();
            if (name.endsWith(".class")) {
                classes.add(ClassUtil.loadClass(pk + "." + name.substring(0, name.length() - 6)));
            }
        }
        return classes;
    }
}
