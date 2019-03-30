import java_cup.runtime.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

class Main {
    public static void main(String[] argv) throws Exception {
        String dirName = null;
        // try {
            // for (int i = 0; i < argv.length; i++) {
            //     if (argv[i].equals("-dir")) {
            //         i++;
            //         if (i >= argv.length)
            //             throw new Error("Missing directory name");
            //         dirName = argv[i];
            //     } else {
            //         throw new Error("Usage: java Main -dir directory");
            //     }
            // }
            // if (dirName == null)
            //     throw new Error("Directory not specified");
            // System.setErr(new PrintStream(new FileOutputStream(new File(dirName, "program.err"))));
            // System.setOut(new PrintStream(new FileOutputStream(new File(dirName, "program.out"))));
            // Parser p = new Parser(new Scanner(new InputStreamReader(new FileInputStream(new File(argv[1])))));
            // p.parse();
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            // BufferedReader br = new BufferedReader(inputStreamReader);
            // try {
            //     StringBuilder sb = new StringBuilder();
            //     String line = br.readLine();

            //     while (line != null) {
            //         sb.append(line);
            //         sb.append(System.lineSeparator());
            //         line = br.readLine();
            //     }
            //     String everything = sb.toString();
            //     System.out.println(everything);
            // } catch(IOException e) {
            // }
            // finally {
            //     br.close();
            // }
            // System.out.println(IOUtils.toString(System.in, StandardCharsets.UTF_8));
            Parser p = new Parser(new Scanner(inputStreamReader));
            String s = new String();
            p.parse();
            // p.debug_parse(); // For debugging
        // } catch (Exception e) {
        //     System.err.println("Exception at ");
        //     e.printStackTrace();
        // }

        // System.out.println("Please type your arithmethic expression:");
        // Parser p = new Parser(new Scanner(new InputStreamReader(System.in)));
        // p.parse();
    }
}
