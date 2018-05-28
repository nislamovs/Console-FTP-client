package sftp;

import com.jcraft.jsch.*;
import java.util.Properties;

public class SFTPDownloader {

	public static final String GET = "get";
	public static final String PUT = "put";

	public static String target;
	public static String destination;
	public static String username;
	public static String password;
	public static String host;
	public static String operation;
	public static Integer port = 22;
	public static Integer paramCount = 12;

	public static void main(String args[]) {

		if(!checkParams(args)) {
			System.exit(0);
		}

		Properties config = new Properties();
		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;
		ChannelSftp sftpChannel = null;
		try {
			config.put("StrictHostKeyChecking", "no");
			session = jsch.getSession(username, host, port);
			session.setPassword(password.getBytes());
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("Connected");

			sftpChannel = (ChannelSftp) channel;
			if (GET.equalsIgnoreCase(operation)) {
				sftpChannel.put(target, destination);
			} else if (GET.equalsIgnoreCase(operation)) {
				sftpChannel.get(target, destination);
			} else {
				helpMessage();
			}

		} catch (JSchException e) {
			e.printStackTrace(System.out);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			sftpChannel.exit();
			session.disconnect();
			System.out.println("Disconnected");
		}
	}

	private static Boolean checkParams(String args[]) {

		if (args.length != paramCount) {
			if ((args.length > 0) && (args[0].equals("-h") || args[0].equals("--help"))) {
				helpMessage();
			} else {
				System.out.println("Please check params!");
			}
			return Boolean.FALSE;
		}

		for(int i = 0; i < args.length; i += 2) {
			switch (args[i]) {
				case "-t":
				case "--target":
					SFTPDownloader.target = args[i+1];
					break;
				case "-d":
				case "--destination":
					SFTPDownloader.destination = args[i+1];
					break;
				case "-u":
				case "--username":
					SFTPDownloader.username = args[i+1];
					break;
				case "-p":
				case "--password":
					SFTPDownloader.password = args[i+1];
					break;
				case "-h":
				case "--host":
					SFTPDownloader.host = args[i+1];
					break;
				case "-o":
				case "--operation":
					SFTPDownloader.operation = args[i+1];
					break;
				case "?":
				case "--help":
				default:
					helpMessage();
					System.exit(0);
					break;
			}
		}

		return Boolean.TRUE;
	}

	private static void helpMessage() {
		System.out.println("Usage : ");
		System.out.println("         -t, --target : Enter path to directory (path/to/dir*) or path to file (path/to/file/file.txt)\n");
		System.out.println("         -d, --destination : Enter destination, where to copy files: - for directory (path/to/dir*) or,\n" +
		                   "                             if path to file : (path/to/file/file.txt)\n");
		System.out.println("         -u, --username : Enter username here (login) \n");
		System.out.println("         -p, --password : Enter password here (password) \n");
		System.out.println("         -h, --host : Enter host IP here (111.222.333.444) \n");
		System.out.println("         -o, --operation : Enter operation here (get|put) \n");
		System.out.println("         ?, --help : This manual \n");
		System.out.println("It should be space between parameter identifier and value, like this: (-h 123.235.123.65) \n");
	}
}
