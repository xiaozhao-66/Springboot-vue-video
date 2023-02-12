import request from '../axios'
export default {
  getAllFan(page,limit,userId){
    return request({
      url: `video/front/fan/getAllFan/${page}/${limit}?userId=${userId}`,
      method: 'get',
    })
  },
}
