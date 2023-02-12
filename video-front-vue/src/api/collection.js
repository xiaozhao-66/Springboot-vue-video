import request from '../axios'
export default {

    addUserCollectionLog(collectionLogsEntity){
        return request({
            url: `video/front/userCollectionLogs/addUserCollectionLog`,
            method: 'post',
            data: collectionLogsEntity
        })
    },

  getAllUserCollectionLogs(page,limit,userId){
    return request({
      url: `video/front/userCollectionLogs/getAllUserCollectionLogs/${page}/${limit}?userId=${userId}`,
      method: 'get',
    })
  },

  delUserCollectionLog(collectionLogsEntity){
      return request({
          url: `video/front/userCollectionLogs/delUserCollectionLog`,
          method: 'post',
          data: collectionLogsEntity
      })
  },

  checkCollection(collectionLogsEntity){
      return request({
          url: `video/front/userCollectionLogs/checkCollection`,
          method: 'post',
          data: collectionLogsEntity
      })
  },
}
