import java.io.*;
import java.util.*;

public class FileListFilter {
	/// class variable
	ArrayList<File> files = new ArrayList<File>();
	final boolean RESULT_OK = true;
	boolean enableSubDirScan = false;

	public int listFiles(String path,
			ArrayList<File> file_list, ArrayList<File> directory_list) {
		final ArrayList<File> results = file_list;
		final ArrayList<File> my_directory_list = directory_list;
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File myfile){
				if(myfile.isDirectory()){
					my_directory_list.add(myfile);
					return false;
				}else if (myfile.getName().endsWith("apk")){
					results.add(myfile);
					return true;
				}else {
					return false;
				}
			}
		};

		try {
			File directory = new File(path);
			directory.listFiles(filter);
		} catch (NullPointerException e) {
			System.err.println("NullPointerException: " + e.getMessage());
		}

		return directory_list.size();
	}

	public boolean traverseDir(String path){
		boolean more_dir = true;
		int subDirectoryCount = 0;

		ArrayList<File> directory_list = new ArrayList<File>();
		subDirectoryCount = listFiles(path, files, directory_list);
		if (subDirectoryCount>0 && enableSubDirScan){
			for ( File dir : directory_list){
				traverseDir(dir.getPath());
			}
		}else{
		}

		return RESULT_OK;
	}

	public void printFiles(){
		for ( File myfile : files ){
			System.out.println(myfile.getName());
		}
	}

	public static void main (String args[]) {
		//ArrayList<File> directory_list = new ArrayList<File>();
		//ArrayList<File> file_list = new ArrayList<File>();
		FileListFilter fl = new FileListFilter();
		String path = "/media/knxy/Software/android_apk";
		fl.traverseDir(path);
		fl.printFiles();


		//System.out.println("Directory: ");
		//for ( File mydir : directory_list ){
		//	System.out.println(mydir);
		//}
	}
}
