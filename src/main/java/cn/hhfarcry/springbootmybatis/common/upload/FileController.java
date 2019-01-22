package cn.hhfarcry.springbootmybatis.common.upload;

import cn.hhfarcry.springbootmybatis.common.entity.BaseEntity;
import cn.hhfarcry.springbootmybatis.common.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.utils.UUIDUtils;
import cn.hhfarcry.springbootmybatis.common.vo.ResponseVO;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: springbootmybatis
 * @description: 文件管理
 * @author: huanghong
 * @date: 2019-01-21 14:51
 */
@Controller
@RequestMapping("file")
public class FileController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${filePath}")
    private String filePath;

    @Autowired
    private IFileService fileService;

    /**
     * 创建文件夹（尚未完善）
     * @param request
     * @param fileEntity
     * @return
     */
    @RequestMapping(value="/createFolder", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseVO createFolder(HttpServletRequest request, @Validated(FolderAddGroup.class)@RequestBody FileEntity fileEntity){
        try {
            return new ResponseVO(fileService.insertFile(fileEntity));
        } catch (Exception e) {
            log.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 单文件上传
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value="uploadSingle", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseVO uploadSingle(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        try{
            if(ParamUtils.isBlank(file)){
                return new ResponseVO(ResponseVO.CODE_FAILED,ResponseVO.MESSAGE_LAKE_PARAMETER,0L,"无文件上传");
            }
            String customPath = "test";
            String filename = file.getOriginalFilename();
            String fileType = filename.substring(filename.lastIndexOf("."));

            String path = filePath+ "/" +customPath;
            File filepath = new File(path, filename);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            String newFileName = UUIDUtils.newUUID() + fileType;

            //原图
            File newFile = new File(path + "/" + newFileName);
            file.transferTo(newFile);
            String urlPath = customPath+ "/" +newFileName;

            //缩略图
            String thumbnailName = newFileName.substring(0,newFileName.lastIndexOf("."))+"-thumbnail"+fileType;
            Thumbnails.of(path + "/" + newFileName)
                    .scale(1f)
                    .outputQuality(0.5f)
                    .toFile(path + "/" + thumbnailName);
            String thumbnailPath = customPath+ "/" +thumbnailName;


            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(newFileName);
            fileEntity.setIsFile(0);
            fileEntity.setFileSuffix(fileType);
            fileEntity.setFilePrefix(newFileName.substring(0,newFileName.lastIndexOf(".")));
            fileEntity.setFileUrl(urlPath);
            fileEntity.setThumbnailUrl(thumbnailPath);
            String result = fileService.insertFile(fileEntity);
            if("ok".equals(result)){
                return new ResponseVO(ResponseVO.CODE_OK,ResponseVO.MESSAGE_OK,0L,urlPath);
            }else{
                return new ResponseVO(ResponseVO.CODE_FAILED,ResponseVO.MESSAGE_FAILED,0L,"上传失败");
            }

        } catch (Exception e) {
            log.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 多文件上传
     * @param request
     * @return
     */
    @RequestMapping(value="uploadMultiple", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseVO uploadMultiple(HttpServletRequest request){
        try{
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            if(ParamUtils.isBlank(files)){
                return new ResponseVO(ResponseVO.CODE_FAILED,ResponseVO.MESSAGE_LAKE_PARAMETER,0L,"无文件上传");
            }
            List<FileEntity>fileEntities = new ArrayList<>();
            List<String> urlPaths = new ArrayList<>();
            List<String> thumbnailPaths = new ArrayList<>();
            String customPath = "test";
            String path = filePath+ "/" +customPath;
            for (MultipartFile file : files) {
                String filename = file.getOriginalFilename();
                String fileType = filename.substring(filename.lastIndexOf("."));
                File filepath = new File(path, filename);
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                String newFileName = UUIDUtils.newUUID() + fileType;
                File newFile = new File(path + "/" + newFileName);
                file.transferTo(newFile);
                String urlPath = customPath+ "/" +newFileName;
                urlPaths.add(urlPath);

                //缩略图
                String thumbnailName = newFileName.substring(0,newFileName.lastIndexOf("."))+"-thumbnail"+fileType;
                Thumbnails.of(path + "/" + newFileName)
                        .scale(1f)
                        .outputQuality(0.5f)
                        .toFile(path + "/" + thumbnailName);
                String thumbnailPath = customPath+ "/" +thumbnailName;
                thumbnailPaths.add(thumbnailPath);

                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileName(newFileName);
                fileEntity.setIsFile(0);
                fileEntity.setFileSuffix(fileType);
                fileEntity.setFilePrefix(newFileName.substring(0,newFileName.lastIndexOf(".")));
                fileEntity.setFileUrl(urlPath);
                fileEntity.setThumbnailUrl(thumbnailPath);
                fileEntities.add(fileEntity);
            }
            String result = fileService.insertFiles(fileEntities);
            if("ok".equals(result)){
                return new ResponseVO(ResponseVO.CODE_OK,ResponseVO.MESSAGE_OK,Long.valueOf(urlPaths.size()),urlPaths);
            }else{
                return new ResponseVO(ResponseVO.CODE_FAILED,ResponseVO.MESSAGE_FAILED,0L,"上传失败");
            }

        } catch (Exception e) {
            log.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    @RequestMapping(value="getpage", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseVO getpage(HttpServletRequest request,@RequestBody FileEntity fileEntity){
        try {
            return new ResponseVO(fileService.getPage(fileEntity));
        } catch (Exception e) {
            log.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

}
