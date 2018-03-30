package com.play001.gobang.support.util;

import com.play001.gobang.support.annotation.MsgAnnotation;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 处理发射相关
 */
public class ClassUtil {

    private static Logger logger = Logger.getLogger(ClassUtil.class);

    //msgType->相应实体类的信息
    private static Map<Short, Class<?>> typeToMsgClassMap;
    //msgType->相应事件的executor类信息
    private static Map<Short, Class<?>> typeToExecutorClassMap;

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
        List<String> classNameList = getClassName(basePackage);
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        for(String className : classNameList){
            Class<?> c = loader.loadClass(basePackage+"."+className);
            //判断是否有msgAnnotation注解
            MsgAnnotation msgAnnotation = c.getAnnotation(MsgAnnotation.class);
            if(msgAnnotation != null){
                typeToMsgClassMap.put(msgAnnotation.msgType(), c);
            }
        }
        if(typeToMsgClassMap.size() <1){
            throw new Exception("在"+basePackage+"下,没有找到任何带MsgAnnotation注解的包");
        }

        logger.info("初始化typeToMsgClassMap完成, 共加载" + typeToMsgClassMap.size());
    }

    /**
     * 初始化typeToMsgClassMap
     * 注意:没有扫描子包下的类
     * 扫描并加载指定包下的带msgAnnotation注解的类
     * @param basePackage 扫描路径
     */
    public static void initTypeToMsgExecClassMap(String basePackage) throws Exception {
        logger.info("开始初始化typeToExecutorClassMap, 包路径:"+basePackage);
        typeToExecutorClassMap = new HashMap<>();
        List<String> classNameList = getClassName(basePackage);
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        for(String className : classNameList){
            Class<?> c = loader.loadClass(basePackage+"."+className);
            //判断是否有msgAnnotation注解
            MsgAnnotation msgAnnotation = c.getAnnotation(MsgAnnotation.class);
            if(msgAnnotation != null){
                typeToExecutorClassMap.put(msgAnnotation.msgType(), c);
            }
        }
        if(typeToExecutorClassMap.size() <1){
            throw new Exception("在"+basePackage+"下,没有找到任何带MsgAnnotation注解的包");
        }

        logger.info("初始化typeToExecutorClassMap完成, 共加载" + typeToMsgClassMap.size());
    }

    /**
     * 获取指定包下的所有类名字
     * 注意:没有校验文件是否是java文件, 没有遍历子目录
     * @param basePackage 包名
     */
    private static List<String> getClassName(String basePackage) throws Exception {
        List<String> classNameList = new LinkedList<>();
        //获取路径名
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        String packagePath = basePackage.replace(".", "/");
        URL url = loader.getResource(packagePath);
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
    }

}
