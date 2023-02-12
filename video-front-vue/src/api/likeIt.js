import request from '../axios'
export default {
  support(likeItEntity){
    return request({
      url: `video/front/likeIt/support`,
      method: 'post',
      data: likeItEntity
    })
  },

  cancelSupport(likeItEntity){
    return request({
      url: `video/front/likeIt/cancelSupport`,
      method: 'post',
      data: likeItEntity
    })
  },

  getRecord(userId,videoId){
    return request({
      url: `video/front/likeIt/getRecord/${userId}/${videoId}`,
      method: 'get',
    })
  }
}
