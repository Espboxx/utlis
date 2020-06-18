package com.jb.flieUtlis;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class OSSFileImp {


        // Endpoint以杭州为例，其它Region请按实际情况填写。
        private String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        private String accessKeyId = "LTAI4GLCVmDucJGQQtrknpqE";
        private String accessKeySecret = "7sLxkO7S5XZjYWx0ZntRU0JNo08inj";
        private String bucketName = "xjj-img";

        // 创建OSSClient实例。
        private OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


    /**
     *
     * @param FileUrl 网络文件Url
     * @param FileName 上传之后的文件名
     * @throws IOException
     */
    public String ossUrlFileUpload(String FileUrl, String FileName) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传网络流。
        URLConnection urlConnection = new URL(FileUrl).openConnection();
        urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36");
        InputStream inputStream = urlConnection.getInputStream();

        PutObjectResult putObjectResult = ossClient.putObject(bucketName, FileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://"+bucketName+"."+endpoint+"/"+FileName;
    }


    /**
     *
     * @param content 上传的字符串
     * @param FileName 上传之后的文件名
     * @return
     */
    public String ossStringFileUpload(String content,String FileName){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,FileName, new ByteArrayInputStream(content.getBytes()));
        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);
        // 上传字符串。
        ossClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://"+bucketName+"."+endpoint+"/"+FileName;
    }


    public String ossByteUpload(byte[] content,String FileName){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);
        // 上传Byte数组。
        ossClient.putObject(bucketName, FileName, new ByteArrayInputStream(content));
        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://"+bucketName+"."+endpoint+"/"+FileName;
    }


}
