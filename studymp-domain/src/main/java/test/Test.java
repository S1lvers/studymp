package test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import test.pojo.Request;
import test.pojo.Trans;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Created by qwerty on 05.04.2017.
 */
public class Test {
    public static void main(String[] args) {
        // определяем название файла, куда будем сохранять
        String fileName = "C:\\Users\\qwerty\\Desktop\\studymp\\studymp-domain\\src\\main\\resources\\request.xml";

        Trans trans = new Trans(1, "R112815479907", "R189153238157", 100, 3, "proizvolnayaStroka", "ProverkaXML", 1231241231289L, 1);
        Request request = new Request(12, "", "", trans, 1);


        String strURL = "https://simulator.expediaquickconnect.com/connect/ar";
        String strXMLFilename = "xmlfile.xml";
        File input = new File(strXMLFilename);
        PostMethod post = new PostMethod(strURL);
        try {
            post.setRequestEntity(new InputStreamRequestEntity(
                    new FileInputStream(input), input.length()));
            post.setRequestHeader("Content-type",
                    "text/xml; charset=ISO-8859-1");
            HttpClient httpclient = new HttpClient();

            int result = httpclient.executeMethod(post);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }

       /* // сохраняем объект в XML файл
        convertObjectToXml(request, fileName);

        // восстанавливаем объект из XML файла
        Request unmarshStudent = fromXmlToObject(fileName);
        if (unmarshStudent != null) {
            System.out.println(unmarshStudent.toString());
        }*/
    }

    private static void convertObjectToXml(Request request, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Request.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(request, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // восстанавливаем объект из XML файла
    private static Request fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Request) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
