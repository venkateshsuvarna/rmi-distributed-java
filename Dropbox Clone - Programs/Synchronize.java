import java.io.*;
import java.text.*;
import java.lang.*;
import java.sql.Timestamp;
import java.util.*;

class SyncThread extends Thread{

    int count;
    int prevcount;
    File folder = new File("C:\\Sync");

    java.util.Date date = new java.util.Date();
    //SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    File logFile = new File("C:\\Sync-me\\see.txt");

    public void run() {
    while(true) {

                File list[] = folder.listFiles();
                count = list.length;
                //**********************detect new files**********************
                int count = list.length;
                String[] a = new String[count];

                List<String> listb = new ArrayList<String>();
                int bcount;

                //reading filenames existing in Sync folder
                for (int i = 0; i < count; i++) {
                    a[i] = list[i].getName();
                }

                //reading file names from file

                try {
                    Scanner sc = new Scanner(new File("C:\\Sync-me\\filenames.txt"));
                    int j = 0;
                    while (sc.hasNext()) {

                        listb.add(sc.nextLine());
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                int prevcount = listb.size();
                String[] b = new String[prevcount];
                for (int i = 0; i < listb.size(); i++) {
                    b[i] = listb.get(i);
                }

                //testing
        /*System.out.println("Files now present in Sync Folder:");
        for(int k=0;k<count;k++){
           System.out.println(a[k]);
        }
        System.out.println("\n\nFiles previously existed in Sync Folder:");
        for(int l=0;l<b.length;l++){
            System.out.println(b[l]);
        }*/

                //compare two lists
                for (int p = 0; p < count; p++) {
                    boolean found = false;
                    for (int q = 0; q < b.length; q++) {
                        if ((a[p].compareTo(b[q])) == 0) {
                            found = true;
                            break;

                        }
                    }
                    if (found) {

                    } else {
                        System.out.println("Found a new file --->" + a[p] + " : uploaded");
                        //connect to server
                        //upload the file a[p] to server
                    }
                }

                //rewrite filenames file with new files
                try {
                    FileWriter writer = new FileWriter("C:\\Sync-me\\filenames.txt", true);
                    //writer.flush();
                    FileWriter fileOut = new FileWriter("C:\\Sync-me\\filenames.txt");
                    fileOut.write("");
                    for (int i = 0; i < count; i++) {
                        writer.write(a[i]);
                        writer.write("\r\n");
                    }
                    writer.close();
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //*****************************detecting deleted files************************
                for (int q = 0; q < b.length; q++) {
                    boolean deleted = false;
                    for (int p = 0; p < count; p++) {
                        if ((b[q].compareTo(a[p])) == 0) {
                            deleted = true;
                            break;

                        }
                    }
                    if (deleted) {

                    } else {
                        System.out.println("A file hss been deleted --->" + b[q] + " : deleted");
                        //connect to server
                        //delete the file b[q] from server
                    }
                }
                //rewrite filenames file with new files
                try {
                    FileWriter writer = new FileWriter("C:\\Sync-me\\filenames.txt", true);
                    //writer.flush();
                    FileWriter fileOut = new FileWriter("C:\\Sync-me\\filenames.txt");
                    fileOut.write("");
                    for (int i = 0; i < count; i++) {
                        writer.write(a[i]);
                        writer.write("\r\n");
                    }
                    writer.close();
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //****************existing files update check.************************
                long timestamp = logFile.lastModified();
                for (int i = 0; i < list.length; i++) {
                    if (list[i].isFile()) {
                        //System.out.print("File "+list[i].getName());
                        // System.out.println("----->"+ sdf.format(list[i].lastModified()));
                        long localtimestamp = list[i].lastModified();
                        if (localtimestamp > timestamp) {
                            System.out.println("File modified-->" + list[i].getName() + " : Updated");
                            //connect to server.
                            //delete the file on server.
                            //upload the file.

                        }
                    } else if (list[i].isDirectory()) {
                        System.out.println("Directory " + list[i].getName());
                    }
                }
                try {
                    FileWriter writer = new FileWriter("C:\\Sync-me\\see.txt", true);
                    writer.write(".");
                    writer.write("\r\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }
}


class Synchronize{
    public static void main(String aargs[]){
        Thread t=new SyncThread();

            t.start();


    }
}