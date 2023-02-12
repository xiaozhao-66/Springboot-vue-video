import request from '../axios'
export default {
  payStatus(userId, videoId){
    return request({
      url: `video/front/order/payStatus/${userId}/${videoId}`,
      method: 'get',
    })
  },
}
