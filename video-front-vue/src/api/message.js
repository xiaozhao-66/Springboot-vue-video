import request from '../axios'
export default {
    getUserListByUserId(userId){
        return request({
            url: `video/front/message/getUserListByUserId/${userId}`,
            method: 'get',
        })
    },

    getAllMessage(fromUserId,toUserId){
        return request({
            url: `video/front/message/getAllMessage/${fromUserId}/${toUserId}`,
            method: 'get',
        })
    },

    save(message){
        return request({
            url: `video/front/message/save/`,
            method: 'post',
            data: message
        })
    },

    getUserNoReplyCount(userId){
        return request({
            url: `video/front/message/getUserNoReplyCount/${userId}`,
            method: 'get',
        })
    },

    addUserMessageCount(userMessageCount){
        return request({
            url: `video/front/message/addUserMessageCount/`,
            method: 'post',
            data: userMessageCount
        })
    }
}
