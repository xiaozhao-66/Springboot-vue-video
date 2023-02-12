import request from '../axios'
export default {
    getListPage(current,limit,videoFrontQuery) {
        return request({
            url: `/video/front/videoInfo/pageFrontCondition/${current}/${limit}`,
            method: 'post',
            data: videoFrontQuery
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

   deletePlayerUrl(videoId) {
      return request({
          url:  `/video/videoInfo/deletePlayerUrl/${videoId}`,
          method: 'get'
        })
   },

  //删除阿里云资源
  deleteAliyunFile(fileName){
    return request({
      url:  `/thirdPart/fileoss/deleteFile?fileName=${fileName}`,
      method: 'get'
    })
  },

  getPublish(videoInfo){
      return request({
          url: `/video/front/videoInfo/publish/`,
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

  getVplayer(id){
      return request({
          url: `/video/vplayer/getOne/${id}`,
          method: 'get',
        })
  },

  getPopularVideos(){
      return request({
          url: `/video/front/videoInfo/getPopularVideos`,
          method: 'get',
        })
  },

  getAllVideosWithUser(page,limit,userId){
    return request({
      url: `/video/front/videoInfo/getAllVideosWithUser/${page}/${limit}?userId=${userId}`,
      method: 'get',
    })
  },

  getRelatedVideos(videoId){
    return request({
      url: `/video/front/videoInfo/getRelatedVideos/${videoId}`,
      method: 'get',
    })
  },
}
