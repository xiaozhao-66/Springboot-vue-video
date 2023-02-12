import request from '@/utils/request'
export default {

    deleteVplayer(id) {
        return request({
            url:  `/video/vplayer/delete/${id}`,
            method: 'delete'
          })
    },


}