import request from '@/utils/request'
export default {
    getListPage(current,limit,UserQueryParams) {
        return request({
            url: `/video/user/getAllUser/${current}/${limit}`,
            method: 'post',
            data: UserQueryParams
          })
    },
    removeAll(id){
        return request({
            url: `/video/user/removeAll/${id}`,
            method: 'get',
          })
    },
    updateStatus(id,status){
        return request({
            url: `/video/user/updateStatus/${id}/${status}`,
            method: 'get',
          })
    },
}