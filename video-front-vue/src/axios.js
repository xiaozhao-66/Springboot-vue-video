import axios from 'axios'
import cookie from 'js-cookie'

const service = axios.create({
  baseURL: 'http://43.248.97.192:9956/api', // api的base_url
  timeout: 20000 // 请求超时时间
})
// 前置拦截
service.interceptors.request.use(
  config=>{
  //   //判断cookie里面是否有值
    if(cookie.get('Jwt_token')){
      //把获取cookie值放入到header里面
      config.headers['Jwt_token']=cookie.get('Jwt_token');
    }
   return config
  },
    err=>{
      return Promise.reject(err);
})

service.interceptors.response.use(
  response=>{
    // if(response.data.code==28004){
    //   console.log("response.data.code==28004")
    //   //错误
    //   window.location.href="/login"
    //   return
    // }else{
    //   if(response.data.code !=2000){
    //     //2500:订单支付中，不做任何提示
    //     // if(response.data.code !=2500){
    //     //   Message({
    //     //     message: response.data.message || 'error',
    //     //     type: 'error',
    //     //     duration: 5*1000
    //     //   })
    //     // }
    //   }else{
    //     return response;
    //   }
    // }
    return response;
  }
)
export default service
