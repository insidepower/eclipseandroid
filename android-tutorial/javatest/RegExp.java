import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
public static void main (String args[]) {
String procVersionStr = "Linux version 2.6.28-nlev_r0.4.0 (uidc1325@uidc1325-laptop) (gcc version 4.1.2 20071124 (Red Hat 4.1.2-41))";

            final String PROC_VERSION_REGEX =
                "\\w+\\s+" + /* ignore: Linux */
                "\\w+\\s+" + /* ignore: version */
                "([^\\s]+)\\s+" + /* group 1: 2.6.22-omap1 */
                "\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+" + /* group 2: (xxxxxx@xxxxx.constant) */
                //"\\(.*?\\)\\s*" ; /* ignore: (gcc ..) */
				"\\(.*?(?:\\(.*?\\)).*?\\)\\s*" ; /* ignore: (gcc ..) */
                //"([^\\s]+)\\s+" + /* group 3: #26 */
                //"(?:PREEMPT\\s+)?" + /* ignore: PREEMPT (optional) */
                //"(.+)"; /* group 4: date */
            Pattern p = Pattern.compile(PROC_VERSION_REGEX);
            Matcher m = p.matcher(procVersionStr);

if (!m.matches()) {
System.out.println("Regex did not match on /proc/version: " + procVersionStr);
//return "Unavailable";
} else if (m.matches()) {
	int i = m.groupCount();
System.out.println("match = " + m.group());
System.out.println("matchCount = " + m.groupCount());
while(i-->0){
	System.out.println("group "+i+": "+m.group(i));
}
}
else if (m.groupCount() < 4) {
System.out.println("Regex match on /proc/version only returned " + m.groupCount());
//return "Unavailable";
}
//   return (new StringBuilder(m.group(1)).append("\n").append(
//           m.group(2)).append(" ").append(m.group(3)).append("\n")
//           .append(m.group(4))).toString();
}
}
