package com.bootx.bootv.commen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CompileClassLoader extends ClassLoader {
    //读取一个文件的内容
    private byte[] getBytes(String fileName) throws IOException {
        File file = new File(fileName);
        long len = file.length();
        byte[] raw = new byte[(int) len];
        try (FileInputStream fs = new FileInputStream(file))
        {
            //一次读取class文件的全部二进制数据
            int r = fs.read(raw);
            if (r != len) {
                throw new IOException("无法读取全部文件：" + r + " in " + len);
            }
        }
        return raw;
    }
    //定义编译指定java文件的方法
    private boolean compile(String javaFile) throws IOException {
        System.out.println("正在编译" + javaFile + "...");
        //调用系统的javac命令
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        try {
            p.waitFor();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        //获取java线程的退出值
        int ret = p.exitValue();
        //编译是否成功
        return ret == 0;
    }
    //重写ClassLoader的findClass方法
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        //将包路径中的"."替换成"/"
        String fileStub = name.replace(".", "/");
        String javaFileName = fileStub + ".java";
        String classFileName = fileStub + ".class";
        File javaFile = new File(javaFileName);
        File classFile = new File(classFileName);
        //当指定java源文件存在，且class文件不存在，或者java源文件的修改时间比class文件的修改时间晚，重新编译
        if (javaFile.exists() && (!classFile.exists() || javaFile.lastModified() > classFile.lastModified())) {
            try {
                //如果编译失败，或者该class文件不存在
                if (!compile(javaFileName) || !classFile.exists()) {
                    throw new ClassNotFoundException("ClassNotFoundException：" + javaFileName);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //如果class文件存在，系统负责将class文件转换成class对象
        if (classFile.exists()) {
            try {
                //将classs文件的二进制数据读入数组
                byte[] raw = getBytes(classFileName);
                //调用ClassLoader的defineClass方法将二进制数据转换成class对象
                clazz = defineClass(name, raw, 0, raw.length);
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        //如果clazz为空，表明加载失败，抛出异常
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }
}
