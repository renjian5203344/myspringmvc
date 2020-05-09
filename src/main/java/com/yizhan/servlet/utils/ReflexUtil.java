package com.yizhan.servlet.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//反射工具类
public class ReflexUtil {

    public static void main(String[] args) {
       Set<Class<?>> set = getClasses("com.yizhan.controller");
       for (Class c :set){
           System.out.println(c.getName());
       }
    }




    public static Set<Class<?>> getClasses(String packages) {
        Set<Class<?>> classes = new LinkedHashSet<>();
        String packageName = packages;
        String packageDir = packageName.replace(".","/"); //com.yizhan.controller-> com/yizhan/controller
         //获取包的名字并替换
        //定义一个枚举集合，并循环来处理这个目录下的文件
        Enumeration<URL> dirs;

        //是否循环迭代
        boolean recursive = true;


        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDir);
            while (dirs.hasMoreElements()){//获取文件夹里面所有元素
                URL url = dirs.nextElement();//获取下一个元素
                String protocal = url.getProtocol();//获取协议名称
                if("file".equals(protocal)){
                    System.out.println("file类型扫描");
                    //获取物理包路径
                    String filepath = URLDecoder.decode(url.getFile(),"UTF-8");//filepath为物理路径
                    findAndClassInpackageByFile(packageName,filepath,recursive,classes);

                }else if ("jar".equals(protocal)){
                    System.out.println("jar包扫描");
                    JarFile jar;
                    jar = ((JarURLConnection)url.openConnection()).getJarFile();
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()){
                        //jar包实体
                      JarEntry entry = entries.nextElement(); //获取每个元素对应的实体
                        String name = entry.getName();
                        if (name.charAt(0)=='/'){
                            name = name.substring(1);


                        }
                        if (name.startsWith(packageName)){
                            int idx = name.lastIndexOf("/");
                            if (idx != -1){
                                packageName = name.substring(0,idx).replace('/','.');
                            }
                            if ((idx != -1) || recursive){
                                if (name.endsWith(".class") && !entry.isDirectory()){
                                    String className = name.substring(packageName.length()+1,name.length()-6);
                                    try {
                                        classes.add(Class.forName(packageName + "." + className));
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }

                                }

                            }
                        }
                    }



                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return classes;
    }

    private static void findAndClassInpackageByFile(String packageName, String filepath, boolean recursive, Set<Class<?>> classes) {
    //获取此包的目录
        File dir = new File(filepath);
        if (!dir.exists() || !dir.isDirectory()){
            return;

        }
        //获取所有的文件.class和目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) ||(file.getName().endsWith(".class"));
            }
        });

        for (File file: dirfiles){
            if (file.isDirectory()){
                findAndClassInpackageByFile(packageName, filepath, recursive, classes);
            }else {//不是目录
                String className = file.getName().substring(0,file.getName().length()-6);//后去class名字
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+className));//往classes里面添加类
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
