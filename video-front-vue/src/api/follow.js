import request from '../axios'
export default {
  getAllFollow(page,limit,userId){
    return request({
      url: `video/front/follow/getAllFollow/${page}/${limit}?userId=${userId}`,
      method: 'get',
    })
  },

  addFollow(followEntity){
      return request({
          url: `video/front/follow/addFollow`,
          method: 'post',
          data: followEntity
      })
  },

  cancelFollow(followEntity){
      return request({
          url: `video/front/follow/cancelFollow`,
          method: 'post',
          data: followEntity
      })
  },

  checkFollow(followEntity){
      return request({
          url: `video/front/follow/checkFollow`,
          method: 'post',
          data: followEntity
      })
  }
}
