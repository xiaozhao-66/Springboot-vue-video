/**
 * 邮箱
 * @param {*} s
 */
export function isEmail(s) {
    return /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(s)
}

/**
 * 手机号码
 * @param {*} s
 */
export function isMobile(s) {
    return /^1[0-9]{10}$/.test(s)
}

/**
 * 电话号码
 * @param {*} s
 */
export function isPhone(s) {
    return /^([0-9]{3,4}-)?[0-9]{7,8}$/.test(s)
}

/**
 * URL地址
 * @param {*} s
 */
export function isURL(s) {
    return /^http[s]?:\/\/.*/.test(s)
}

/**
 *  上传视频校验
 */
export function videoInfoAlidate(videoInfo){
  var errorInfo={}
  if(videoInfo.title==''||videoInfo.title==null){
    console.log(111)
    errorInfo.data='视频标题不能为空'
    errorInfo.flag = false
    return errorInfo
  }
  else if(videoInfo.description==''||videoInfo.title==null){
    errorInfo.data='视频描述不能为空'
    errorInfo.flag = false
    return errorInfo
  }
  else if(videoInfo.categoryId==null){
    errorInfo.data='视频分类不能为空'
    errorInfo.flag = false
    return errorInfo
  }
  else if(videoInfo.cover==''||videoInfo.cover==null){
    errorInfo.data='请上传一张封面'
    errorInfo.flag = false
    return errorInfo
  }
  else if(videoInfo.playerUrl==''||videoInfo.playerUrl==null){
    errorInfo.data='请上传一个视频文件'
    errorInfo.flag = false
    return errorInfo
  }
  else if(videoInfo.selectTags.length<=0){
    errorInfo.data='请选择标签分类'
    errorInfo.flag = false
    return errorInfo
  }else{
    errorInfo.flag = true
    return errorInfo
  }
}


