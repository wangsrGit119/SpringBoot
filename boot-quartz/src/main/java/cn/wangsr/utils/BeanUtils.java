package cn.wangsr.utils;

import cn.wangsr.jobs.BaseJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/24 0024 16:38
 */
public class BeanUtils {

    private static  boolean exist=false;
    private static Logger logger= LoggerFactory.getLogger(BeanUtils.class);
    /**
     * @Description   String ClassName 转  clazz
     * @author wjl
     * @date 2019/9/25 0025
     * @param [classname]
     * @return cn.wangsr.jobs.BaseJob
     */
    public static BaseJob getClass(String classname)  {
        BaseJob baseJob=null;
        try {
            Class<?> class1 = Class.forName("cn.wangsr.jobs.detailsJobs."+classname);
            baseJob= (BaseJob) class1.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return baseJob;
    }

    /**
     * @Description  递归获取package中的className  本地运行 jar文件运行区分
     * @author wjl
     * @date 2019/9/25 0025
     * @param [packageName, isRecursion]
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getClassName(String packageName, boolean isRecursion)  {
        List classNames = new ArrayList();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace('.', '/');

        URL url = loader.getResource(packagePath);
        String protocol = url.getProtocol();
        logger.info("文件路径协议:   {}",protocol);
        if(protocol.equals("file")){
            if (url != null) {
                Set<String> allClassNames = getClassNameFromDir(url.getPath(), packageName, isRecursion);
                for (String className : allClassNames) {
                    classNames.add(className);
                }
            }
        }
        if(protocol.equals("jar")){
            try {
                JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                if (jarURLConnection != null) {
                    JarFile jarFile = jarURLConnection.getJarFile();
                    if (jarFile != null) {
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            if (jarEntryName.endsWith(".class")) {
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                if(className.startsWith(packageName)){
                                    classNames.add(className);
                                }
                            }
                        }
                    }
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return classNames;
    }

    /**
     * @Description  递归查询className
     * @author wjl
     * @date 2019/9/25 0025
     * @param [filePath, packageName, isRecursion]
     * @return java.util.Set<java.lang.String>
     */
    private static Set<String> getClassNameFromDir(String filePath, String packageName, boolean isRecursion) {
        Set<String> className = new HashSet<>();
        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files != null) {
            for (File childFile : files) {
                if (childFile.isDirectory()) {
                    if (isRecursion) {
                        className.addAll(getClassNameFromDir(childFile.getPath(), packageName + "." + childFile.getName(), isRecursion));
                    }
                } else {
                    String fileName = childFile.getName();
                    if (fileName.endsWith(".class") && !fileName.contains("$")) {
                        className.add(packageName + "." + fileName.replace(".class", ""));
                    }
                }
            }
        }
        return className;
    }


    /**
     * @Description   判断className 是否在指定包中
     * @author wjl
     * @date 2019/9/25 0025
     * @param [packageName, className]
     * @return boolean
     */
    public static boolean existCurrentPackage(String packageName,String className){
        getClassName(packageName,true).forEach(bean->{
            if(bean.contains(className)){
                exist=true;
            }
        });
        return exist;
    }


    public static void main(String[] args) {
          List re=  getClassName("cn.wangsr.jobs.detailsJobs",true);
            re.stream().forEach(x->{
                System.out.println(x);
            });


    }
}
