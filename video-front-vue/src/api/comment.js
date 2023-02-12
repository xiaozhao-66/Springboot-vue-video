import request from '../axios'
export default {
  getAllCommentByVideo(videoId,page,limit,page2,limit2){
    return request({
      url: `video/front/comment/getAllCommentByVideo/${page}/${limit}/${page2}/${limit2}?videoId=${videoId}`,
      method: 'get',
    })
  },

  getOneComment(commentId){
        return request({
            url: `video/front/comment/getOneComment/${commentId}`,
            method: 'get',
        })
    },

  addComment(comment){
      return request({
          url: `video/front/comment/addComment/`,
          method: 'post',
          data: comment
      })
  },

  getAllNoReplyComment(page,limit,userId){
      return request({
          url: `video/front/comment/getAllNoReplyComment/${page}/${limit}?userId=${userId}`,
          method: 'get',
      })
  }
}
