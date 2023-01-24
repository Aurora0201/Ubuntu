package HomeWork;

import java.io.*;

public class DirectoryCopy {
    public static void main(String[] args) {
        String in = "/home/binjunkai/Desktop/Java";
        String out = "/home/binjunkai/Desktop/JavaCopy/";
        copyDirectory(in,out);
    }

    /**
     * Copy a file to another place
     * @param inputFile FatherDirectory path
     * @param outPath The path you want to place the directory
     */
    public static void copyDirectory(String inputFile, String outPath) {
        //A File object point inputFile
        File in = new File(inputFile);
        //Get every file in the inputFile
        File[] files = in.listFiles();
        //Iterate the files
        for (File file : files) {
            //Judge whether it is a directory
            if (file.isDirectory()) {
                //Create the directory
                new File(outPath + file.getName()).mkdir();
                //Recursive call
                copyDirectory(file.getAbsolutePath(), outPath + file.getName() + "/");
            } else {
                //Write the file to new directory
                FileInputStream fis = null;
                FileOutputStream fos = null;
                try {
                    fis = new FileInputStream(file.getAbsolutePath());
                    fos = new FileOutputStream(outPath + file.getName());
                    byte[] bytes = new byte[1 << 20];
                    int readCot;
                    while ((readCot = fis.read(bytes)) != -1) {
                        fos.write(bytes,0,readCot);
                    }
                    fos.flush();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        }
    }
}


