package com.learning.ftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class TestSftpClient {

	public static void main(String  [] args) {
		upload();
		download();
	}
	
	private static void upload() {
		JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("root", "172.29.75.201", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("root123");
            session.connect();
            
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.put("D:/Tmp/test.log", "/root/test_upload.log");  
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
	}

	private static void download() {
		JSch jsch = new JSch();
        Session session = null;
        try {
           session = jsch.getSession("root", "172.29.75.201", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("root123");
            session.connect();
            
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get("/root/test_upload.log", "D:/Tmp/test_download.log");  
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
	}
	
}
