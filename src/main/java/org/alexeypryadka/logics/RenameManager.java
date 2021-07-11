package org.alexeypryadka.logics;

import java.io.File;
import java.util.Objects;

public class RenameManager {

    private  boolean isRename = true;

    private boolean isEmpty = false;

    public void renameFilesOfDirectory(String path , String word, String replacement){
        try {
            File filePath = new File(path + "\\");
            File[] files =  filePath.listFiles();

            if (files != null && files.length == 0){
               this.isEmpty = true;
            }

            for (int i = 0; i < Objects.requireNonNull(files).length; i++){
                String name = files[i].getName().replace(word,replacement);
                System.out.println(path + name);
               this.isRename = files[i].renameTo(new File(path  + name));
            }
        }catch (Exception e){
            isRename = false;
        }

    }
    public void  printFilesOfDirectory(String path){
        File filePath = new File(path);
        File[] files =  filePath.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++){
            System.out.println(files[i].getName());
        }

    }
    public boolean isRename() {
        return isRename;
    }
    public boolean isEmpty() {
        return isEmpty;
    }
}
