package Classes;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
 
public class JavaMail{
	
	public boolean validaEmail(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
	}
      public void enviarEmail(String destinatario, String assunto,String mensagem) throws EmailException{
    	  Persistencia pers= new Persistencia();
		  Central cdi = pers.recuperarCentral("dados.xml");
		  
          String username = cdi.getAdmin().getLogin();
          String password = cdi.getAdmin().getSenha();
           
           Properties props = new Properties();
           props.put("mail.smtp.host", "smtp.gmail.com");
           props.put("mail.smtp.socketFactory.port", "465");
           props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.port", "465");
       
           Session session = Session.getDefaultInstance(props,
                       new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() 
                            {
                                  return new PasswordAuthentication(username, password);
                            }
                       });
       
           /** Ativa Debug para sessão */
           session.setDebug(true);
       
           try {
       
                 Message message = new MimeMessage(session);
                 message.setFrom(new InternetAddress(username)); //Remetente
       
                 Address[] toUser = InternetAddress //Destinatário(s)
                            .parse(destinatario);  
       
                 message.setRecipients(Message.RecipientType.TO, toUser);
                 message.setSubject(assunto);//Assunto
                 message.setText(mensagem);
                 /**Método para enviar a mensagem criada*/
                 Transport.send(message);
       
            }catch (MessagingException e) {
                 throw new RuntimeException(e);
           }
          }
		
}


