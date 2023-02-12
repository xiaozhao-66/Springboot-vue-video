import request from '@/utils/request'
export default {
    getListPage(current,limit,videoQuery) {
        return request({
            url: `/video/videoInfo/pageCondition/${current}/${limit}`,
            method: 'post',
            data: videoQuery
          })
    },
    addVideoInfo(video) {
        return request({
            url: `/video/videoInfo/addVideoInfo`,
            method: 'post',
            data: video
          })
    },
    getVideoInfo(id){
        return request({
            url: `/video/videoInfo/getVideoInfo/${id}`,
            method: 'get',
          })
    },
    updateVideoInfo(video){
        return request({
            url: `/video/videoInfo/updateVideoInfo`,
            method: 'post',
            data: video
          })
    },
     //删除视频
     deleteAliyunvod(id) {
        return request({
            url:  `/thirdPart/videoPlayer/removeAlyVideo/${id}`,
            method: 'delete'
          })
    },

    getPublish(videoInfo){
        return request({
            url: `/video/videoInfo/publish/`,
            method: 'post',
            data: videoInfo
          })
    },
    removeAll(id){
        return request({
            url: `/video/videoInfo/removeAll/${id}`,
            method: 'get',
          })
    },
    updateStatus(id,status){
        return request({
            url: `/video/videoInfo/updateStatus/${id}/${status}`,
            method: 'get',
          })
    },
}