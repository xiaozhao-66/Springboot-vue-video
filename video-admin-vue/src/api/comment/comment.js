import request from '@/utils/request'
export default {
    getListPage(page,limit,CommentQueryParams) {
        return request({
            url: `/video/comment/getAllComment/${page}/${limit}`,
            method: 'post',
            data: CommentQueryParams
          })
    },
    removeAll(commentId){
        return request({
            url: `/video/comment/removeAll/${commentId}`,
            method: 'get',
          })
    },
}