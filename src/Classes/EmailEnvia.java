package Classes;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.itextpdf.text.log.Level;

public class EmailEnvia {
	public static boolean validaEmail(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return false;
        } else {
            return true;
        }
    }
	
		
		
		public void enviaEmailSimples() throws EmailException {
			
		}
        
	public static void main(String[] args) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setTLS(true);
		email.setSSL(false);
		email.setAuthentication("aleixoferragens1@gmail.com", "lor123456789");
		email.setFrom("aleixoferragens1@gmail.com", "lor123456789");
		email.setSubject("fndvnlkdmkladmkl");
		email.setMsg("vkmnsdknlkdsnklvkdnvk");
		email.addTo("luanprof30@gmail.com");
		email.send();
		
	}
}


