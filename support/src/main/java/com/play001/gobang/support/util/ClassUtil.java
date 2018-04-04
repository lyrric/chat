package com.play001.gobang.support.util;

import com.play001.gobang.support.annotation.MsgAnnotation;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 处理发射相关
 */
public class ClassUtil {

    private static Logger logger = Logger.getLogger(ClassUtil.class);

    //msgType->相应实体类的信息
    private static Map<Short, Class<?>> typeToMsgClassMap;
    //msgType->相应事件的executor类信息
    private static Map<Short, Class<?>> typeToExecutorClassMap;
    // class文件过滤器
    private static MyFilter myFilter = new MyFilter(true);
    // 根据类型得到对应的消息类的class对象
    public static Class<?> getMsgClassByType(short type) {
        return typeToMsgClassMap.get(type);
    }

    // 根据类型得到对应的业务逻辑执行器的class对象
    public static Class<?> getExecutorClassByType(short type) {
        return typeToExecutorClassMap.get(type);
    }


    /**
     * 初始化typeToMsgClassMap
     * 注意 没有扫描子包下的类
     * 扫描并加载指定包下的带msgAnnotation注解的类
     * @param basePackage 扫描路径
     */
    public static void initTypeToMsgClassMap(String basePackage) throws Exception {
        logger.info("开始初始化typeToMsgClassMap,包路径:"+basePackage);
        typeToMsgClassMap = new HashMap<>();
        Set<Class<?>> classSet = getClasses(basePackage);
        for(Class<?> clazz : classSet){
            //判断是否有msgAnnotation注解
            MsgAnnotation msgAnnotation = clazz.getAnnotation(MsgAnnotation.class);
            if(msgAnnotation != null){
                typeToMsgClassMap.put(msgAnnotation.msgType(), clazz);
            }
        }
        if(typeToMsgClassMap.size() <1){
            throw new Exception("在"+basePackage+"下,没有找到任何带MsgAnnotation注解的包");
        }

        logger.info("初始化typeToMsgClassMap完成, 共加载" + typeToMsgClassMap.size());
    }


    /**
     * 初始化typeToMsgClassMap
     * 扫描并加载指定包下的带msgAnnotation注解的类
     * @param basePackage 扫描路径
     */
    public static void initTypeToMsgExecClassMap(String basePackage) throws Exception {
        logger.info("开始初始化typeToExecutorClassMap, 包路径:"+basePackage);
        typeToExecutorClassMap = new HashMap<>();
        Set<Class<?>> classSet = getClasses(basePackage);
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        for(Class<?> clazz : classSet){
            //判断是否有msgAnnotation注解
            MsgAnnotation msgAnnotation = clazz.getAnnotation(MsgAnnotation.class);
            if(msgAnnotation != null){
                typeToExecutorClassMap.put(msgAnnotation.msgType(), clazz);
            }
        }
        if(typeToExecutorClassMap.size() <1){
            throw new Exception("在"+basePackage+"下,没有找到任何带MsgAnnotation注解的包");
        }

        logger.info("初始化typeToExecutorClassMap完成, 共加载" + typeToMsgClassMap.size());
    }


    /**
     * 从包package中获取所有的Class
     */
    private static Set<Class<?>> getClasses(String pack)
            throws ClassNotFoundException, IOException {
        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字，并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合，并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        dirs = Thread.currentThread().getContextClassLoader()
                .getResources(packageDirName);
        // 循环迭代下去
        while (dirs.hasMoreElements()) {
            // 获取下一个元素
            URL url = dirs.nextElement();
            // 得到协议的名称
            String protocol = url.getProtocol();
            // 如果是以文件的形式保存在文件系统上
            if ("file".equals(protocol)) {
                //System.err.println("file类型的扫描");
                // 获取包的物理路径
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                // 以文件的方式扫描整个包下的文件，并添加到集合中
                findAndAddClassesInPackageByFile(packageName, filePath,
                        recursive, classes);
            } else if ("jar".equals(protocol)) {
                // 如果是jar包文件
                // 定义一个JarFile
                //System.err.println("jar类型的扫描");
                JarFile jar;
                // 获取jar
                JarURLConnection jarURLConnection = (JarURLConnection) url
                        .openConnection();
                jar = jarURLConnection.getJarFile();
                // 从此jar包，得到一个枚举类
                Enumeration<JarEntry> entries = jar.entries();
                // 同样的进行循环迭代
                while (entries.hasMoreElements()) {
                    // 获取jar里的一个实体，可以是目录，和一些jar包里的其他文件，如META-INF等文件
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    // 如果是以/开头的
                    if (name.charAt(0) == '/') {
                        // 获取后面的字符串
                        name = name.substring(1);
                    }
                    // 如果前半部分和定义的包名相同
                    if (name.startsWith(packageDirName)) {
                        int idx = name.lastIndexOf('/');
                        // 如果以"/"结尾，是一个包
                        if (idx != -1) {
                            // 获取包名，把"/"替换成"."
                            packageName = name.substring(0, idx)
                                    .replace('/', '.');
                        }
                        // 如果可以迭代下去，并且是一个包
                        if ((idx != -1) || recursive) {
                            // 如果是一个.class文件，而且不是目录
                            if (name.endsWith(".class")
                                    && !entry.isDirectory()) {
                                // 去掉后面的".class"，获取真正的类名
                                String className = name.substring(
                                        packageName.length() + 1,
                                        name.length() - 6);
                                // 添加到classes
                                classes.add(Class.forName(packageName + '.'
                                        + className));
                            }
                        }
                    }
                }
            }
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     */
    private static void findAndAddClassesInPackageByFile(String packageName,
                                                         String packagePath, final boolean recursive, Set<Class<?>> classes)
            throws ClassNotFoundException {

        // 获取此包的目录，建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者，也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在，就获取包下的所有文件，包括目录
        File[] dirFiles = dir.listFiles(myFilter);
        if(dirFiles == null){
            throw new ClassNotFoundException("没有找到任何类,位于"+packagePath+"下");
        }
        // 循环所有文件
        for (File file : dirFiles) {
            // 如果是目录，则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(
                        packageName + "." + file.getName(),
                        file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是java类文件，去掉后面的.class，只留下类名
                String className = file.getName().substring(0,
                        file.getName().length() - 6);

                // 添加到集合中去
                classes.add(Thread.currentThread().getContextClassLoader()
                        .loadClass(packageName + '.' + className));
            }
        }
    }
}

/**
 * 过滤得到需要的class文件
 *
 */
class MyFilter implements FileFilter {

    private boolean recursive;

    MyFilter(boolean recursive) {
        this.recursive = recursive;
    }

    public boolean accept(File file) {
        return (recursive && file.isDirectory())
                || (file.getName().endsWith(".class")
                && !file.getName().contains("$"));
    }
}





    /*
     * 已遗弃, 因为开发时类是一个一个的文件
     * 而发布时,是一个jar,两者不通用
     * 获取指定包下的所有类名字
     * 注意:没有校验文件是否是java文件, 没有遍历子目录
     * @param basePackage 包名
     */
    /*
    private static List<String> getClassName(String basePackage) throws Exception {
        List<String> classNameList = new LinkedList<>();
        //获取路径名
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        String packagePath = basePackage.replace(".", "/");
        URL url = loader.getResource(packagePath);
        logger.info("扫描路径:" + url);
        if(url == null){
            throw new Exception("找不到文件");
        }
        String path= url.getPath();
        File dir=new File(path);
        //获取目录下所有的文件
        File[] files = dir.listFiles();
        if(files == null){
            throw new Exception("没有找到任何文件");
        }
        for(File f:files){
            if(!f.isDirectory()){
                String fileName = f.getName().split("\\.")[0];
                classNameList.add(fileName);
            }
        }
        return classNameList;
    }*/


