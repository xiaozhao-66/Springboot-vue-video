import request from '@/utils/request'
export default {
    getAllTags(page,limit,tagQueryParams) {
        return request({
            url: `/video/tag/getAllTags/${page}/${limit}`,
            method: 'post',
            data: tagQueryParams
        })
    },
    addTag(tag){
        return request({
            url: `/video/tag/addTag`,
            method: 'post',
            data: tag
        })
    },
    getTagInfo(tagId){
        return request({
            url: `/video/tag/getTagInfo/${tagId}`,
            method: 'get',

        })
    },
    updateTag(tag){
        return request({
            url: `/video/tag/updateTag`,
            method: 'post',
            data: tag
        })
    },
    removeAll(tagId){
        return request({
            url: `/video/tag/removeAll/${tagId}`,
            method: 'get',
        })
    },
    getTagList(){
        return request({
          url: `video/front/tag/getTagList`,
          method: 'get',
        })
      },
}