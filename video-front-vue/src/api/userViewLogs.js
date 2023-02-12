import request from '../axios'
export default {
    addUserViewLogs(userViewLogsInfo){
        return request({
            url: `video/front/userViewLogs/addUserViewLogs`,
            method: 'post',
            data: userViewLogsInfo
        })
    },

    getAllUserViewLogs(page,limit,userId){
      return request({
        url: `video/front/userViewLogs/getAllUserViewLogs/${page}/${limit}?userId=${userId}`,
        method: 'get',
      })
    },

    delOneRecord(userId,videoId){
      return request({
        url: `video/front/userViewLogs/delOneRecord/${userId}/${videoId}`,
        method: 'get',
      })
  }
}
