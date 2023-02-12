import request from '../axios'
export default {
  getTagList(){
    return request({
      url: `video/front/tag/getTagList`,
      method: 'get',
    })
  },
}
