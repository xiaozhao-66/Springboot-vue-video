import request from '@/utils/request'
export default {
    getAllCategory() {
        return request({
            url: `/video/category/getAllCategory`,
            method: 'get',
        })
    },
    getOneCategory(categoryId){
        return request({
            url: `/video/category/getOneCategory/${categoryId}`,
            method: 'get',
        })
    },  
    addCategory(category) {
        return request({
            url: `/video/category/addCategory`,
            method: 'post',
            data: category
        })
    },
    updateCategory(category){
        return request({
            url: `/video/category/updateCategory`,
            method: 'post',
            data: category
        })
    },
    deleteCategory(category){
        return request({
            url: `/video/category/deleteCategory/`,
            method: 'post',
            data: category
        })
    }
}
