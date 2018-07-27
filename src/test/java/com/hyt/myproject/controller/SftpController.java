package com.hyt.myproject.controller;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by pc on 2018/5/25.
 */
public class SftpController {

    /**
        * @param user 账号
        * @param password 密码
        * @param host 主机地址
        * @param port 端口
        * @Description:
        * @autohr: hyt
        * @datatime: 2018/5/25 12:27
        * @return
        */
    public static ChannelSftp getConnect(String user,String host,String password,int port){
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(user, host, port);
            Session sshSession = jsch.getSession(user, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sftp;
    }
    /**
        * @param directory 下载目录
        * @param downloadFile 下载的文件
        * @param saveFile 存在本地的路径
        * @param sftp sftp连接
        * @Description: 下载文件
        * @autohr: hyt
        * @datatime: 2018/5/25 12:31
        * @return
        */
    public static void download(String directory,String downloadFile,String saveFile,ChannelSftp sftp){
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile,new FileOutputStream(file));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void downloadTest(){
        ChannelSftp sftp = getConnect("root", "47.75.134.223", "iTest@)!*007serv^Atom", 29172);
        download("/opt/www/paymchv4.5/transfer","payfee.xls","E:/alidata/payfee.xls",sftp);
    }
}
