package Email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmailSender {
	public static final String MAIL_REGISTRATION_SITE_LINK = "http://localhost:8085/workspace103/workspace103/ConfirmEmail";

	public static void send_uninsubria_email(String usr, String pwd, String to, String subject, String body,
			String hash) throws SendFailedException, MessagingException {
		String password = pwd;
		String username = usr;

		// String hash = null;
		String host = "smtp.office365.com";
		String from = username;

		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", 587);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		String link = MAIL_REGISTRATION_SITE_LINK + "?scope=activation&userId=" + to + "&hash=" + hash;

		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<div>").append("  Caro Utente<br/><br/>").append("  Grazie per la Registrazione. <br/>")
				.append("  Per favore, clicca <a href=\"" + link + "\">qui</a> per validare la tua email <br/>")
				.append("  <br/>").append("  Grazie,<br/>").append("</div>");
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setSubject("Registrazione Email");
		msg.setContent(bodyText.toString(), "text/html; charset=utf-8");

		Transport.send(msg, username, password);
		System.out.println("\nMail was sent successfully.");

	}

	public static void main(String[] argv) {
		try {
			String password = "";
			String username = "";
			String subject = "";
			String to = "";
			String body = "";
			String hash = "";

			final JTextField uf = new JTextField("name@studenti.uninsubria.it");
			final JPasswordField pf = new JPasswordField();
			final JTextField tf = new JTextField();
			final JTextField sf = new JTextField("email subject");
			final JTextArea bf = new JTextArea(null, "textual content of the email", 10, 20);
			// final JTextField em =new JTextField("ciao");
			final JTextField ha = new JTextField("ConfirmEmail");

			Object[] message = { "Username / From:", uf, "Password:", pf, "To:", tf, "Subject:", sf, "Body:", bf,
					// "Email:", em,
					"Hash:", ha,

			};

			int option = JOptionPane.showOptionDialog(null, message, "Send email", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, new String[] { "Send", "Cancel" }, "Send");
			if (option == JOptionPane.YES_OPTION) {
				password = new String(pf.getPassword());
				username = new String(uf.getText());
				to = new String(tf.getText());
				subject = new String(sf.getText());
				body = new String(bf.getText());
				// email=new String(em.getText());
				hash = new String(ha.getText());
				send_uninsubria_email(username, password, to, subject, body, hash);
			}

		} catch (MessagingException e) {
			System.err.println("SMTP SEND FAILED:");
			System.err.println(e.getMessage());

		}
	}

}
