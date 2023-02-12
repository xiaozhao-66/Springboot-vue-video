import request from '../axios'
export default {
    login(userInfo){
        return request({
            url: `video/front/user/login`,
            method: 'post',
            data: userInfo
        })
    },

    getCode(phone){
        return request({
            url: `thirdPart/msm/sendMsm/${phone}`,
            method: 'get',
        })
    },

    register(userInfo){
        return request({
            url: `video/front/user/register`,
            method: 'post',
            data: userInfo
        })
    },

    loginByGitee(){
        return request({
            url: `video/front/user/render/gitee`,
            method: 'get',
        })
    },

    getUserInfoByToken(token){
        return request({
            url: `video/front/user/getUserInfoByToken?token=${token}`,
            method: 'get',
        })
    },

    setting(userInfo){
        return request({
            url: `video/front/user/setting`,
            method: 'post',
            data: userInfo
        })
    },

    getUserInfoByUserId(userId){
        return request({
            url: `video/front/user/getUserInfoByUserId/${userId}`,
            method: 'get',
        })
    },

    getZimToken(userId){
      return request({
        url: `video/front/user/getZimToken/${userId}`,
        method: 'get',
      })
    },
}
