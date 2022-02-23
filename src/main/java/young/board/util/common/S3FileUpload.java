package young.board.util.common;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3FileUpload {
    // 버킷 이름 동적 할당
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 버킷 주소 동적 할당
    @Value("${cloud.aws.s3.bucket.url}")
    private String defaultUrl;

    private final AmazonS3 amazonS3;

    public String upload(MultipartFile multipartFile) throws IOException{
        String origName = multipartFile.getOriginalFilename();
        String url;
        try{
            final String ext = origName.substring(origName.lastIndexOf('.'));
            final String saveFileName = UUID.randomUUID().toString()+ext;
            //파일 객체 생성
            ObjectMetadata objMeta = new ObjectMetadata();
            byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
            objMeta.setContentLength(bytes.length);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            amazonS3.putObject(new PutObjectRequest(bucket,saveFileName,byteArrayInputStream,objMeta)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            url = amazonS3.getUrl(bucket,saveFileName).toString();
        } catch(StringIndexOutOfBoundsException e){
            url = null;
        }
        return url;
    }
}
