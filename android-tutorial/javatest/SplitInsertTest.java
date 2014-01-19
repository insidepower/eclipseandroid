import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

import java.io.InputStream;         //InputStream
import java.io.InputStreamReader;
import java.io.FileInputStream;     //FileInputStream
//import java.io.RandomAccessFile;    //for getFilePointer
import java.nio.channels.FileChannel;


import java.io.*;

public class SplitInsertTest {
	//static String mystr = "不要把能說話的嘴巴，用在搬弄是非上。 要批評別人時，先想想自己是否無完美無缺。";
	static String mystr = "脾氣嘴巴不好，心地再好也不能算好人。當人誇讚時，不覺得高興，只認為是應該做的；而受人辱罵時，也不會生氣。如此不起心動念、不動不靜，就是『平常心』。";
    public static final int LINE_MAX_LEN = 25;

	public static void mysplit(){
		String [] temp;
		temp = mystr.split("。");
		System.out.println("split: ");
		for (String str: temp )
			System.out.println(str);
	}

	public static void mytoken(){
		StringTokenizer st = new StringTokenizer(mystr, "。" );
		System.out.println("\n\ntoken: ");
		while(st.hasMoreTokens()){
			System.out.print(st.nextToken()+"\n");
		}
	}

	public static void insertNewLine(){
		int dotPosition = mystr.indexOf("。");
		System.out.println("\n\ndotPosition = "+ dotPosition);
		// str.length()-1    ## as indexOf start from 0
		if (dotPosition >0 && dotPosition < (mystr.length()-1)){
			String newstr = mystr.substring(0, dotPosition+1)+"\n"
				+mystr.substring(dotPosition+2);
			System.out.println("insertNewLine: ");
			System.out.println(newstr);
		}
	}

    public static void foldLine(int len){
        int first_index = 0;
        int last_index = LINE_MAX_LEN-1;

        System.out.println("\n\nfoldLine: ");
        while (last_index < len )
        {
            String substr = mystr.substring(first_index, last_index);
            System.out.println(substr.trim());
            first_index = first_index + LINE_MAX_LEN;
            last_index = last_index + LINE_MAX_LEN;
        }

        if (last_index != (len-1))
        {
            /// print the remaining of text
            String substr = mystr.substring(first_index);
            System.out.println(substr.trim());
        }
    }

    public static void foldLine(String str){
        int first_index = 0;
        int last_index = LINE_MAX_LEN-1;
        int len = str.length();

        System.out.println("foldLine: ");
        while (last_index < len )
        {
            String substr = str.substring(first_index, last_index);
            System.out.println(substr.trim());
            first_index = first_index + LINE_MAX_LEN;
            last_index = last_index + LINE_MAX_LEN;
        }

        if (last_index != (len-1))
        {
            /// print the remaining of text
            String substr = str.substring(first_index);
            System.out.println(substr.trim());
        }

        System.out.println();
    }

    public static void readFromFile() {
        try {
            /// open the file
            InputStream ins = new FileInputStream("jingsiyu.txt");
            InputStreamReader inputreader = new InputStreamReader(ins);
            BufferedReader bufread = new BufferedReader(inputreader);
            FileChannel fc = ((FileInputStream)ins).getChannel();
            String line = null;

            /// read line by line
            //System.out.println("Initial read position: "+fc.getFilePointer());
            line = bufread.readLine();
            while (line!=null){
                //System.out.println(line);
                /// [ comment ] the position return undesired result, 
                /// need further understanding on it
                System.out.println("Bytes used: "+line.getBytes().length);
                System.out.println(fc.position()+": "+"(len="+line.length()+"):"+line);
                foldLine(line);
                line = bufread.readLine();
            }

            /// close the file
            ins.close();

        }catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void seekFile() {
        try{
            InputStream ins = new FileInputStream("jingsiyu.txt");
            InputStreamReader inputreader = new InputStreamReader(ins);
            BufferedReader bufread = new BufferedReader(inputreader);
            FileChannel fc = ((FileInputStream)ins).getChannel();
            String line = null;

            System.out.print("Input the desired file position: ");
            while(true) {
                Scanner input = new Scanner(System.in);
                int choice = input.nextInt();
                if (choice==0){
                    System.out.println("Ended");
                    break;
                }

                fc.position(choice);
                bufread = new BufferedReader(new InputStreamReader(ins));
                System.out.println("fc position: "+fc.position());
                line = bufread.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromRandomFile() {
        try {
            /// open the file
            RandomAccessFile randomfile = new RandomAccessFile("jingsiyu.txt", "r");

            /// read line after line
            String line = randomfile.readLine();
            while (line!=null){
                //System.out.println(line);
                /// [ comment ] the position return undesired result, 
                /// need further understanding on it
                System.out.println(randomfile.getFilePointer()+": "+line);
                foldLine(line);
                //line = randomfile.readUTF();
                line = randomfile.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main (String args[]) {
		//mysplit();
		//mytoken();
		//insertNewLine();
        //if (mystr.length() > LINE_MAX_LEN)
        //{
        //    foldLine(mystr.length());
        //}

        //mystr = "對於無常的人生，能不執著來去、生死，就能回歸天真本性，生活才能安然自在無煩惱。";
        //if (mystr.length() > LINE_MAX_LEN)
        //{
        //    String str = "對於無常的人生，能不執著來去、生死，就能回歸天真本性，生活才能安然自在無煩惱。";
        //    foldLine(str);
        //}

        System.out.println("\n\n\nRead from file");
        readFromFile();
        seekFile();
        //readFromRandomFile();

		System.out.println("num="+(5-1));
	}
}
