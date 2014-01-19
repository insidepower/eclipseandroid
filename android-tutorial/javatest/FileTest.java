import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileTest {
    InputStream instream;
    InputStreamReader inputreader;
    BufferedReader buffreader;

    public void FileInit(String mystr)
    {
        instream = openFileInput(mystr);
        inputreader = new InputStreamReader(instream);
        buffreader = new BufferedReader(inputreader);
    }


    public void readText()
    {
        /*
        try {

            String line;
            //info = buffreader.readLine();
            if (instream!=null) {
                int i = 1;
                while (current_txt_index > i++) {
                    //Log.i(TAG, "inside while "+current_txt_index+" :i="+i);
                    buffreader.readLine();
                    buffreader.mark(200);
                }
                current_txt_index++;
                //str_jingsiyu = buffreader.readLine();
                line = buffreader.readLine();
                /*
                   int dotPosition = line.indexOf("$");
                //Log.i(TAG, line+" ; line.length()-1="+(line.length()-1));
                Log.i(TAG, "dotPosition="+dotPosition+"; line.length="+line.length());
                if (dotPosition>0 && dotPosition<(line.length()-1)){
                str_jingsiyu = line.substring(0, dotPosition+1)+"\n"
                +line.substring(dotPosition+1);

                StringTokenizer st = new StringTokenizer(line, "$" );
                if (st.hasMoreTokens()) {
                    str_jingsiyu = st.nextToken();
                    while(st.hasMoreTokens()){
                        str_jingsiyu += "\n"+st.nextToken();
                    }
                }else {
                    str_jingsiyu = line;
                }
                //Log.i(TAG, str_jingsiyu);
                }

            // close the file again
            instream.close();
            } catch (java.io.FileNotFoundException e) {	
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    */
    }

    public static void main (String args[])
    {
        FileInit( );
    }
}
