package com.xiaoai.common;

import javax.swing.*;
import java.io.*;
import java.util.logging.Logger;

/**
 * @Author xiaoaiying
 * @Date 2021-01-20 23:59
 */
public class FileOptionUtil {

    private String catalogPackageIconPrefix = "[";
    private String catalogPackageIconSuffix = "]";
    private Integer initDirHierarchy = 0;

    private Logger logger = Logger.getGlobal();

    /**
     * 文件转为字节数数组
     *  file to byteArray
     *  file--program
     *  program byteArray
     * @param filePath 文件路径
     * @return 文件封装后的字节数组
     */
    public byte[] fileToByteArray(String filePath) {
        //1-源与目的路径
        File src = new File(filePath);
        byte[] dest = null;
        //2-选择流
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(src);
            baos = new ByteArrayOutputStream();
            //3-操作
            byte[] flush = new byte[1024*10];
            int len = -1;
            while((len = is.read(flush)) != -1) {
                baos.write(flush,0,flush.length);//写出到字节数组
            }
            baos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (is!=null) {
                    is.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
    /*
     * 字节数组转文件
     * byteArray to file
     * byteArray - program
     * program -file
     * @param src 字节数组
     * @param filePath 存储文件路径
     */
    public void byteArrayToFile(byte[] src,String filePath) {
        File srcout = new File(filePath);
        OutputStream os = null;
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(src);
            //方法1-通过bytearray实现分段写入文件
            os = new FileOutputStream(srcout);
            byte[] flush = new byte[5];//缓冲容器
            int len = -1;//接收标识
            while((len=is.read(flush))!=-1) {
                os.write(flush,0,flush.length);
            }
            //方法2-直接写入文件
            //os.write(src);
            os.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (os!=null) {
                    os.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 文件复制
     * @param inPath 待复制文件的路径
     * @param outPath 复制文件目的路径
     * @throws Exception
     */
    public void fileCopy(String inPath,String outPath) throws Exception  {
        File inFile = new File(inPath);//input path file
        File outFile = new File(outPath);//output path file
        if (inFile.exists()) {
            throw new Exception("the filePath is exists , pleace check the inPath or outPath");
        }
        if (outFile.exists()){
            outFile.createNewFile();
        }
        InputStream inStream = null;
        OutputStream outStream =null;
        try {
            inStream = new FileInputStream(inFile);
            outStream = new FileOutputStream(outFile);
            byte[] flush = new byte[1024];
            int len = -1;
            while((len=inStream.read(flush))!=-1) {
                outStream.write(flush, 0, len);
                outStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (outStream!=null) {
                outStream.close();
            }else if (inStream!=null) {
                inStream.close();
            }
            logger.info("The file copy is finish!");
        }
    }

    public void dirErgodic(String filePath){
        dirErgodic(new File(filePath),initDirHierarchy,
                catalogPackageIconPrefix,
                catalogPackageIconSuffix);
    }
    public void dirErgodic(File file){
        dirErgodic(file,initDirHierarchy,
                catalogPackageIconPrefix,
                catalogPackageIconSuffix);
    }
    public void dirErgodic(File file,int initDirHierarchy){
        dirErgodic(file,initDirHierarchy,
                catalogPackageIconPrefix,
                catalogPackageIconSuffix);
    }
    /**
     * 遍历目录结构
     * @param file 文件对象
     * @param initDirHierarchy 初始目录层数
     */
    public void dirErgodic(File file,int initDirHierarchy,
                           String catalogPackageIconPrefix,
                           String catalogPackageIconSuffix){
        File[] files = file.listFiles();
        for (File f:files){
            if (f.isDirectory()){//目录则继续进入
                for (int j=0;j<initDirHierarchy;j++){ //打印分隔
                    System.out.print("| ");
                }
                if (f.list().length > 0){
                    System.out.print("-");
                }
                System.out.println(catalogPackageIconPrefix + f.getName()+catalogPackageIconSuffix);
                dirErgodic(f,initDirHierarchy+1,
                        catalogPackageIconPrefix,
                        catalogPackageIconSuffix);
            }else{
                //文件--打印名称
                for (int j=0;j<initDirHierarchy;j++){ //打印分隔
                    System.out.print("| ");
                }
                System.out.println(f.getName());
            }
        }
    }

    /**
     * 遍历某目录下的所有目录
     * @param file
     * @param initDirHierarchy
     */
    public void printDir(File file,int initDirHierarchy){
        File[] files = file.listFiles();
        for (File f:files){
            if (f.isDirectory()){//目录则继续进入
                for (int j=0;j<initDirHierarchy;j++){ //打印分隔
                    System.out.print("| ");
                }
                if (f.list().length > 0){
                    System.out.print("+");
                }
                System.out.println(catalogPackageIconPrefix + f.getName()+catalogPackageIconSuffix);
                printDir(f,initDirHierarchy+1);
            }
        }
    }

    /**
     * 弹出框选择文件或路径获取选择的路径
     * @param currentDirPath
     * @return
     */
    public String chooseFilePath(String currentDirPath){
        String filePath = null; // targe目录
        JFileChooser fileChooser = new JFileChooser(currentDirPath);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showOpenDialog(fileChooser);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            filePath= fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的
        }
       return filePath;
    }


//    public static bufstreamToBuffer(String path){
//
//
//    }


    /**
     * 通过文件路径直接修改文件名
     *
     * @param filePath    需要修改的文件的完整路径
     * @param newFileName 需要修改的文件的名称
     * @return
     */
    private String fixFileName(String filePath, String newFileName) {
        File f = new File(filePath);
        if (!f.exists()) { // 判断原文件是否存在（防止文件名冲突）
            return null;
        }
        newFileName = newFileName.trim();
        if ("".equals(newFileName) || newFileName == null) // 文件名不能为空
            return null;
        String newFilePath = null;
        if (f.isDirectory()) { // 判断是否为文件夹
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName;
        } else {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName
                    + filePath.substring(filePath.lastIndexOf("."));
        }
        File nf = new File(newFilePath);
        try {
            f.renameTo(nf); // 修改文件名
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return newFilePath;
    }
}
