package com.xz.video.modules.oss.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class VideoUtil {

    /**
     * 上传视频，获取视频时长，返回毫秒
     * @param multipartFile
     * @return
     */
    public static long getDurationBackMillis(MultipartFile multipartFile){
        if(multipartFile != null){
            try{
                // 根据上传的文件名字，构建初始化的文件对象（临时文件），这个文件是空的
                File file = new File(multipartFile.getOriginalFilename());
                // 通过工具类，将文件拷贝到空的文件对象中
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);

                // 将普通文件对象转换为媒体对象
                MultimediaObject multimediaObject = new MultimediaObject(file);
                // 获取媒体对象的信息对象
                MultimediaInfo info = multimediaObject.getInfo();
                // 从媒体信息对象中获取媒体的时长，单位是：ms
                long duration = info.getDuration();
                // 删除临时文件
                file.delete();

                return duration;
            } catch(Exception e){
                return 0L;
            }
        }
        return 0L;
    }

    /**
     * 上传视频，获取视频时长，返回时分秒字符串
     * @param multipartFile
     * @return
     */
    public static String getDurationBackString(MultipartFile multipartFile){
        // 获取视频时长，返回毫秒
        long duration = getDurationBackMillis(multipartFile);
        // 毫秒转时分秒的转换
        // 日期格式化对象，给时分秒格式
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        // 这里很重要，如果不设置时区的话，输出结果就会是几点钟，而不是毫秒值对应的时分秒数量了。
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // 毫秒转化为字符串
        return formatter.format(duration);
    }
}
