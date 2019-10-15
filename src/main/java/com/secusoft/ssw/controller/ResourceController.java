package com.secusoft.ssw.controller;

import com.secusoft.ssw.common.GlobalApiResult;
import com.secusoft.ssw.common.exception.ServiceException;
import com.secusoft.ssw.service.HomePageService;
import com.secusoft.ssw.util.WordUtils;
import com.secusoft.ssw.util.test;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName ResourceController
 * @Author jcyao
 * @Description
 * @Date 2018/9/19 11:01
 * @Version 1.0
 */
@RestController
@RequestMapping("/resource")
@Api(value = "Resource-Controller", description = "资源相关接口")
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private HomePageService homePageService;
    @Value("${image.path}")
    private String imagePath;
    @Value("${perimeter.image.path}")
    private String perimeterImagePath;

    @Value("${vehicle.image.path}")
    private String vehicleImagePath;

    @Value("${mvcvehicle.image.path}")
    private String vmcVehicleImagePath;


    @Value("${person.image.path}")
    private String uploadPersonImage;

    @Value("${shield.image.path}")
    private String shieldImagePath;

    @Value("${homepage.image.path}")
    private String homePageImagePath;

    @GetMapping("/downLoadVideoPlayer")
    @ApiOperation(value="下载VideoPlayer插件")
    @ResponseBody
    public GlobalApiResult<String> getFile(HttpServletResponse response) {
        try {
            String separator = System.getProperty("file.separator");
            String filePath = System.getProperty("user.dir")+separator+"config"+separator+"VideoPlayerSetup.exe";
            InputStream inStream = new FileInputStream(filePath);
            response.addHeader("Content-Disposition", "attachment; filename=VideoPlayerSetup.exe");
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
            return GlobalApiResult.success("下载成功");
        } catch (Exception e) {
            return GlobalApiResult.failure("下载异常");
        }
    }

    /**
     * 上传周界图片
     * @param image
     * @return
     */
    @ApiOperation(value = "上传图片")
    @PostMapping("/uploadPerimeterImage")
    public GlobalApiResult<String> uploadPerimeterImage(@RequestParam(value = "image",required = false) MultipartFile image){
        return GlobalApiResult.success(saveFile(perimeterImagePath,image,"/resource/getPerimeterImage/"));
    }

    @ApiOperation(value = "上传图片")
    @PostMapping("/uploadImage")
    public GlobalApiResult<String> uploadImage(@RequestParam(value = "image",required = false) MultipartFile image){
        return GlobalApiResult.success(saveFile(imagePath,image,"/resource/getImage/"));
    }

    public static Map saveFiles(String path, MultipartFile file, String returnPath) throws Throwable {
        String separator = System.getProperty("file.separator");
        String fileName = file.getOriginalFilename();
        System.out.println("名字："+fileName);

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        UUID uuid = UUID.randomUUID();
        String newFilename = uuid+"."+suffix;
        System.out.println("uuid=============:"+uuid);
        String dateNowStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String dynamicPath = separator+dateNowStr+separator;
        String imgSavePath = path+dynamicPath;
        System.out.println("======================="+imgSavePath);
        try {
            if(!Files.exists(Paths.get(imgSavePath))){//如果目录不存在就创建
                Files.createDirectories(Paths.get(imgSavePath));
            }
            if(Files.exists(Paths.get(imgSavePath+newFilename))){//如果文件存在 删除
                Files.delete(Paths.get(imgSavePath+newFilename));
            }
            Files.createFile(Paths.get(imgSavePath+newFilename));
            Files.copy(file.getInputStream(), Paths.get(imgSavePath, newFilename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            throw new ServiceException("上传失败，请稍后重试");
        }
        logger.info("上传图片成功：{{}}",returnPath+dateNowStr+"/"+newFilename);
        WordUtils.wordToHtml(imgSavePath,newFilename,uuid+"");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fileurl",returnPath+dateNowStr+"/"+uuid+".html");
        map.put("filename",fileName);
        return map;
    }

    public static String saveFile(String path, MultipartFile image, String returnPath) {
        String separator = System.getProperty("file.separator");
        String newFilename = UUID.randomUUID()+".png";
        String dateNowStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String dynamicPath = separator+dateNowStr+separator;
        String imgSavePath = path+dynamicPath;
        try {
            if(!Files.exists(Paths.get(imgSavePath))){//如果目录不存在就创建
                Files.createDirectories(Paths.get(imgSavePath));
            }
            if(Files.exists(Paths.get(imgSavePath+newFilename))){//如果文件存在 删除
                Files.delete(Paths.get(imgSavePath+newFilename));
            }
            Files.createFile(Paths.get(imgSavePath+newFilename));
            Files.copy(image.getInputStream(), Paths.get(imgSavePath, newFilename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            throw new ServiceException("上传失败，请稍后重试");
        }
        logger.info("上传图片成功：{{}}",returnPath+dateNowStr+"/"+newFilename);
        return returnPath+dateNowStr+"/"+newFilename;
    }

    @ApiOperation(value = "获取人脸识别图片")
    @GetMapping("/getImage/{date}/{filename:.+}")
    public ResponseEntity<?> getImage(@PathVariable String date,@PathVariable String filename) {
        return getImageFile(date,filename,imagePath);
    }

    @ApiOperation(value = "获取周界管理图片")
    @GetMapping("/getPerimeterImage/{date}/{filename:.+}")
    public ResponseEntity<?> getPerimeterImage(@PathVariable String date,@PathVariable String filename) {
        return getImageFile(date,filename,perimeterImagePath);
    }

    private ResponseEntity<?> getImageFile(String date, String filename, String path) {
        String separator = System.getProperty("file.separator");
        String dynamicPath = separator+date+separator;
        String imgAbsolutePath = path+dynamicPath;
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(imgAbsolutePath, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


//    @ApiOperation(value = "web上传车辆图片")
//    @PostMapping("/uploadVehicleImage")
//    public GlobalApiResult<String> uploadVehicleImage(@RequestParam(value = "image",required = false) MultipartFile image,@RequestParam(value = "outletid",required = false) String outletid){
//        return GlobalApiResult.success(saveFile(vehicleImagePath+"/"+outletid,image,"/resource/getUploadVehicleImage/"+outletid+"/"));
//    }

    @ApiOperation(value = "VMC上传车辆图片")
    @PostMapping("/vmcUploadVehicleImage")
    public GlobalApiResult<String> vmcUploadVehicleImage(@RequestParam(value = "image",required = false) MultipartFile image,@RequestParam(value = "outletid",required = false) String outletid){
        return GlobalApiResult.success(saveFile(vmcVehicleImagePath+"/"+outletid,image,"/resource/getVmcUploadVehicleImage/"+outletid+"/"));
    }


    @ApiOperation(value = "web获取车辆图片")
    @GetMapping("/getUploadVehicleImage/{outletid}/{date}/{filename:.+}")
    public ResponseEntity<?> getUploadVehicleImage(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename) {
        return getImageFile(date,filename,vehicleImagePath+"/"+outletid);
    }



    @ApiOperation(value = "MVC获取车辆图片")
    @GetMapping("/getVmcUploadVehicleImage/{outletid}/{date}/{filename:.+}")
    public ResponseEntity<?> getVmcUploadVehicleImage(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename) {
        return getImageFile(date,filename,vmcVehicleImagePath+"/"+outletid);
    }



    @ApiOperation(value = "删除车辆图片")
    @GetMapping("/deleteVehicleImage")
    public GlobalApiResult<Object> deleteVehicleImage(@RequestParam(value = "fileType",required = true) String fileType,@RequestParam(value = "outletid",required = true) String outletid,@RequestParam(value = "date",required = true) String date,@RequestParam(value = "filename",required = true) String filename) {
        String separator = System.getProperty("file.separator");
        if(fileType.equals("getUploadVehicleImage")){
            fileType=vehicleImagePath+separator+outletid;
        }else{
            fileType=vmcVehicleImagePath+separator+outletid;
        }
        String imgAbsolutePath =fileType+separator+date+separator+filename;
        File file=new File(imgAbsolutePath);
        if(file.exists()){
            file.delete();
        }
        String msg="删除成功";
        return GlobalApiResult.success(msg);
    }




    @ApiOperation(value = "web获取人员身份证图片")
    @GetMapping("/getUploadPersonImage/{outletid}/{date}/{filename:.+}")
    public ResponseEntity<?> getUploadPersonImage(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename) {
        return getImageFile(date,filename,uploadPersonImage+"/"+outletid);
    }




    @ApiOperation(value = "删除人员图片")
    @GetMapping("/deletePersonImage")
    public GlobalApiResult<Object> deletePersonImage(@RequestParam(value = "fileType",required = true) String fileType,@RequestParam(value = "outletid",required = true) String outletid,@RequestParam(value = "date",required = true) String date,@RequestParam(value = "filename",required = true) String filename) {
        String separator = System.getProperty("file.separator");
        if(fileType.equals("getUploadPersonImage")){
            fileType=uploadPersonImage+separator+outletid;
        }
        String imgAbsolutePath =fileType+separator+date+separator+filename;
        File file=new File(imgAbsolutePath);
        if(file.exists()){
            file.delete();
        }
        String msg="删除成功";
        return GlobalApiResult.success(msg);
    }


    @Value("${settle.image.path}")
    private String settleImagePath;

    @ApiOperation(value = "web获取盾构图片")
    @GetMapping("/getUploadShieldImage/{outletid}/{date}/{filename:.+}")
    public ResponseEntity<?> getUploadShieldImage(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename) {
        return getImageFile(date,filename,shieldImagePath+"/"+outletid);
    }


    @ApiOperation(value = "web获取测绘测量图片")
    @GetMapping("/getUploadSettleImage/{outletid}/{date}/{filename:.+}")
    public ResponseEntity<?> getUploadSettleImage(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename){
        return getImageFile(date,filename,settleImagePath+"/"+outletid);
    }


    @ApiOperation(value = "上传施工图片")
    @PostMapping("/vmcUploadHomePageImage")
    public GlobalApiResult<String> vmcUploadHomePageImage(@RequestParam(value = "image",required = false) MultipartFile image,@RequestParam(value = "outletid",required = false) String outletid){
        String url = saveFile(homePageImagePath + "/" + outletid, image, "/resource/getploadHomePageImage/" + outletid + "/");
        return GlobalApiResult.success(url);
    }
    @ApiOperation(value = "获取施工图片")
    @GetMapping("/getploadHomePageImage/{outletid}/{date}/{filename:.+}")
    public ResponseEntity<?> getploadHomePageImage(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename){
        return getImageFile(date,filename,homePageImagePath+"/"+outletid);
    }
    @ApiOperation(value = "上传盾构图片")
    @PostMapping("/vmcUploadDgImage")
    public GlobalApiResult<String> vmcUploadDgImage(@RequestParam(value = "image",required = false) MultipartFile image,@RequestParam(value = "outletid",required = false) String outletid){
        String url = saveFile(homePageImagePath + "/" + outletid, image, "/resource/getploadDgImage/" + outletid + "/");
        return GlobalApiResult.success(url);
    }

    @ApiOperation(value = "获取盾构图片")
    @GetMapping("/getploadDgImage/{outletid}/{date}/{filename:.+}")
    public ResponseEntity<?> getploadDgImage(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename){
        return getImageFile(date,filename,homePageImagePath+"/"+outletid);
    }

    @Value("${device.image.path}")
    private String uploadDeviceImage;



    @ApiOperation(value = "获取一机一档图片")
    @GetMapping("/getDeviceImage/{outletid}/{date}/{filename:.+}")
    public ResponseEntity<?> getDeviceImage(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename){
        return getImageFile(date,filename,uploadDeviceImage+"/"+outletid);
    }


    @ApiOperation(value = "删除一机一档图片")
    @GetMapping("/deleteDeviceImage")
    public GlobalApiResult<Object> deleteDeviceImage(@RequestParam(value = "fileType",required = true) String fileType,@RequestParam(value = "outletid",required = true) String outletid,@RequestParam(value = "date",required = true) String date,@RequestParam(value = "filename",required = true) String filename) {
        String separator = System.getProperty("file.separator");
        if(fileType.equals("getDeviceImage")){
            fileType=uploadDeviceImage+separator+outletid;
        }
        String imgAbsolutePath =fileType+separator+date+separator+filename;
        File file=new File(imgAbsolutePath);
        if(file.exists()){
            file.delete();
        }
        String msg="删除成功";
        return GlobalApiResult.success(msg);
    }
//
//    @Value("${video.image.path}")
//    private String videopath;
//
//    @ApiOperation(value = "获取视频")
//    @GetMapping("/getVideo/{outletid}/{date}/{filename:.+}")
//    public ResponseEntity<?> getVideo(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename){
//        return getImageFile(date,filename,videopath+"/"+outletid);
//    }
        @ApiOperation(value = "一人一档获取文档")
        @GetMapping("/getUploadPersonFile/{outletid}/{date}/{filename:.+}")
        public ResponseEntity<?> getUploadPersonFile(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename){
        return getImageFile(date,filename,uploadPersonImage+"/"+outletid);
}


    @ApiOperation(value = "删除一人一档文档")
    @GetMapping("/deletePersonFile")
    public GlobalApiResult<Object> deletePersonFile(@RequestParam(value = "fileType",required = true) String fileType,@RequestParam(value = "outletid",required = true) String outletid,@RequestParam(value = "date",required = true) String date,@RequestParam(value = "filename",required = true) String filename) {
        String separator = System.getProperty("file.separator");
        if(fileType.equals("getUploadPersonFile")){
            fileType=uploadPersonImage+separator+outletid;
        }
        String imgAbsolutePath =fileType+separator+date+separator+filename;
        File file=new File(imgAbsolutePath);
        if(file.exists()){
            file.delete();
        }
        String msg="删除成功";
        return GlobalApiResult.success(msg);
    }


    @ApiOperation(value = "一机一档获取文档")
    @GetMapping("/getDeviceFile/{outletid}/{date}/{filename:.+}")
    public ResponseEntity<?> getDeviceImageFile(@PathVariable String outletid,@PathVariable String date,@PathVariable String filename){
        return getImageFile(date,filename,uploadDeviceImage+"/"+outletid);
    }


    @ApiOperation(value = "删除一机一档文档")
    @GetMapping("/deleteDeviceFile")
    public GlobalApiResult<Object> deleteDeviceFile(@RequestParam(value = "fileType",required = true) String fileType,@RequestParam(value = "outletid",required = true) String outletid,@RequestParam(value = "date",required = true) String date,@RequestParam(value = "filename",required = true) String filename) {
        String separator = System.getProperty("file.separator");
        if(fileType.equals("getDeviceFile")){
            fileType=uploadDeviceImage+separator+outletid;
        }
        String imgAbsolutePath =fileType+separator+date+separator+filename;
        File file=new File(imgAbsolutePath);
        if(file.exists()){
            file.delete();
        }
        String msg="删除成功";
        return GlobalApiResult.success(msg);
    }


}
