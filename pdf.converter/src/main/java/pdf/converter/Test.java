package pdf.converter;

public class Test {
    public static void main(String[] args) throws Exception {

        //String[] cmd = { "C:\\E.M. TVCC\\TVCC.exe", "-f E:\\TestVideo\\01.avi", "-o E:\\OutputFiles\\target.3gp" };
        String[] cmd = { "C:\\finacle\\pdftotext.exe", "-table", "-lineprinter",
        		"C:\\finacle\\Branch Settlement Report - Group by City.pdf", "", 
        		"C:\\finacle\\Y1.txt" };
        Process p = Runtime.getRuntime().exec(cmd);
        p.waitFor();
    }
}