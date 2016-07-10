package pdf.converter;
 
import java.io.IOException;
 
//iText imports
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
 
public class iTextReadDemo {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
             
            PdfReader reader = new PdfReader("C:\\Users\\Anoop\\Desktop\\Branch Settlement Report - Group by city.pdf");
            System.out.println("This PDF has "+reader.getNumberOfPages()+" pages.");
            String page = PdfTextExtractor.getTextFromPage(reader, 1);
            System.out.println("Page Content:\n\n"+page+"\n\n");
            
 
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
 
}