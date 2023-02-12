<template>
    <!-- right side content area -->
    <div class="large-8 columns profile-inner">
        <!-- profile settings -->
        <section class="profile-settings">
            <div class="row secBg">
                <div class="large-12 columns">
                    <div class="container">
                        <div class="leftSide">
                            <!--search-->
                            <div class="search_chat">
                                <div>
                                    <input type="text" placeholder="搜索...">
                                </div>
                            </div>
                            <!--ChatList-->
                            <div class="chatlist" v-for="(user, index) in userList" :key="index">
                                <div :class="T == index ? 'block active' : 'block'"
                                    @click="selectUser(user.fromUserId, index)">
                                    <div class="imgbx">
                                        <img :src="user.avatar" class="cover">
                                    </div>
                                    <div class="details">
                                        <div class="listHead">
                                            <h4>{{ user.username }}</h4>
                                            <p class="time">{{ user.gmtModified }}</p>
                                        </div>
                                        <div class="message_p">
                                            <p>{{ user.content }}</p>
                                            <div class="red-point">{{ user.count }}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- <right-side ref="rightSide"></right-side> > -->

                        <div class="rightSide">
                            <div class="header">
                                <div class="imgText">
                                    <div class="userimg">
                                        <img :src="selectUserInfo.avatar" class="cover">
                                    </div>
                                    <h4>{{ selectUserInfo.username }}<br><span></span></h4>
                                </div>
                            </div>

                            <div class="chatBox" id="chatBox" ref=chatWindow>
                                <!-- <div :class="message.fromUserId==userInfo.id?'message my_message':'message frnd_message'" v-for="message in messageList">
                        <p>{{message.data}}<br><span>10:15</span> </p>
                    </div> -->
                            </div>


                            <div class="chatbox_input">
                                <div class="instrument">
                                    <i class="el-icon-s-grid"></i>
                                    <i class="el-icon-folder"></i>

                                </div>
                                <div>
                                    <textarea style="height: 150px;width: 100%;border-radius: 4px"
                                        v-model="content"></textarea>
                                    <el-button round style="float:right" @click="send()">发送</el-button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';'
import msg from '@/api/message'
import $ from 'jquery'
import { formatDate,getHMS } from '@/utils/util'
import user from '@/api/user'
import { ZIM } from 'zego-zim-web';

var appID = 1562974438;
// 静态同步方法，创建 zim 实例，传入 AppID。
// create 方法仅第一次调用时会创建 ZIM 实例，后续调用会返回 null。
ZIM.create({ appID });
// 通过 getInstance 获取单实例，避免热更新导致 create 多次创建返回 null。
var zim = ZIM.getInstance();

export default {
    //import 引入的组件需要注入到对象中才能使用
    components: {
    },
    props: {},
    data() {
        //这里存放数据
        return {
            messageList: [],
            userList: [],
            userInfo: {},
            message: {},
            content: '',
            T: -1,
            selectUserInfo: {},
        };
    },
    //计算属性类似于data 概念
    computed: {},
    //监控data 中的数据变化
    watch: {},
    //方法集合
    methods: {
        createws(){
        //得到token信息
        user.getZimToken(this.userInfo.id).then(response=>{
          var userInfo = { userID: this.userInfo.id, userName: this.userInfo.username };
          var token =  response.data.data
          zim.login(userInfo, token)
            .then(function () {
              // 登录成功
              console.log("登录成功")
            })
            .catch(function (err) {
              // 登录失败
            });
        })

        // 注册监听“运行时错误信息”的回调
        zim.on('error', function (zim, errorInfo) {
          console.log('error', errorInfo.code, errorInfo.message);
        });

        // 注册监听“网络连接状态变更”的回调
        zim.on('connectionStateChanged', function (zim, { state, event, extendedData }) {
          console.log('connectionStateChanged', state, event, extendedData);
        });

        // 注册监听“令牌即将过期”的回调
        zim.on('tokenWillExpire', function (zim, { second }) {
          console.log('tokenWillExpire', second);
          // 可以在这里调用 renewToken 接口来更新 token
          // 新 token 生成可以参考上文
          user.getZimToken(this.userInfo.id).then(response=> {
            var token = response.data.data
            zim.renewToken(token)
              .then(function({ token }){
                // 更新成功
                console.log("更新成功")
              })
              .catch(function(err){
                // 更新失败
              })
          })
        });
      },

        send() {
            if (!this.content) {
                this.$message({
                    message: '请输入聊天消息！',
                    type: 'warning'
                });
            } else {
              var toConversationID = this.message.toUserId; // 对方 userID
              var conversationType = 0; // 会话类型，取值为 单聊:0, 房间:1, 群组:2
              var config = {
                priority: 1, // 设置消息优先级，取值为 低：1（默认），中：2，高：3
              };

              var messageTextObj = {type: 1, message: this.content, extendedData: '1'};
              var notification = {
                onMessageAttached: function (message) {
                  // todo: Loading
                }
              }

              zim.sendMessage(messageTextObj, toConversationID, conversationType, config, notification)
                .then(function ({message}) {
                  // 发送成功
                  console.log('发送成功', message)
                })
                .catch(function (err) {
                  // 发送失败
                });
              this.message.data = this.content
              this.message.time = formatDate(new Date(), 'yyyy-MM-dd h:m:ss')
              msg.save(this.message)
              let str = `<div class="message my_message" >
                        <p>${this.content}<br><span>${this.message.time}</span> </p>
                        </div>`
              $('#chatBox').append(str)
              this.content = ''
            }
        },

        getUserListByUserId(userId) {
            //定时接受消息
            setInterval(e => {
                msg.getUserListByUserId(userId).then(response => {
                    this.userList = response.data.data
                })
            }, 500)
        },


        selectUser(userId, index) {

            user.getUserInfoByUserId(userId).then(response => {
                this.selectUserInfo = response.data.data
            })
            this.message.fromUserId= this.userInfo.id;
            this.message.toUserId = userId
            this.T = index
            $('#chatBox').html('')

            let chatData = ''

            // 注册监听“收到单聊消息”的回调
            zim.on('receivePeerMessage', function (zim, { messageList, fromConversationID }) {
              console.log("收到消息")
              console.log('receivePeerMessage', messageList, fromConversationID);
              if(fromConversationID==userId){
                for(var i=0;i<messageList.length;i++){
                  let str = `<div class="message frnd_message" >
                      <p>${messageList[i].message}<br><span>${getHMS(messageList[i].timestamp,'yyyy-MM-dd h:m:ss')}</span> </p>
                      </div>`
                  $('#chatBox').append(str)
                }
              }

            });
            //读取数据库信息
            msg.getAllMessage(this.userInfo.id, userId).then(response => {

                let messageDatas = response.data.data

                for (var i = 0; i < messageDatas.length; i++) {
                    let str
                    if (messageDatas[i].fromUserId == this.userInfo.id) {
                        str = `<div class="message my_message" >
                            <p>${messageDatas[i].data}<br><span>${messageDatas[i].dateTime}</span> </p>
                            </div>`
                    } else {
                        str = `<div class="message frnd_message" >
                            <p>${messageDatas[i].data}<br><span>${messageDatas[i].dateTime}</span> </p>
                            </div>`
                    }
                    chatData = chatData + str
                }

                if (chatData != null) {
                    $('#chatBox').html(chatData)
                }
            })
        },
    },
    //生命周期- 创建完成（可以访问当前this 实例）
    created() {
        if (this.$store.getters.getUser) {
            this.userInfo = JSON.parse(this.$store.getters.getUser)
            this.getUserListByUserId(this.userInfo.id)
            this.createws()
        }

    },
    //生命周期- 挂载完成（可以访问DOM 元素）
    mounted() {
        this.$nextTick(e=>{
                if (this.$route.query.toUserId) {
                //uid代表当前接受方的用户id
                var uid = this.$route.query.toUserId
                console.log(uid)
                msg.getUserListByUserId(this.userInfo.id).then(response => {
                    var dataList = response.data.data
                    console.log(dataList)
                    
                    //判断用户是否存在在列表中
                    
                    for (var i=0 ; i < dataList.length; i++) {
                        if (dataList[i].fromUserId == uid) {
                            this.selectUser(uid, i)
                            break;
                        }
                    }
                })
               
            }
            })
          
    },
    updated() {
        //消息滑动最低端
        this.$refs.chatWindow.scrollTop = this.$refs.chatWindow.scrollHeight;

    },
}
</script>
<style >
.container {
    position: relative;
    max-width: 100%;
    height: 700px;
    background: #fff;
    box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.06), 0 2px 5px 0 rgba(0, 0, 0, 0.06);
    display: flex;
}

.container .leftSide {
    position: relative;
    background: #fff;
    border-right: 1px solid rgba(0, 0, 0, 0.2);
}

.container .rightside {
    position: relative;

    background: #e5ddd5;
}

.container .rightside::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    opacity: 0.06;
}

.header {
    position: relative;
    width: 100%;
    height: 60px;
    background: #ededed;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 15px;
}

.userimg {
    position: relative;
    width: 40px;
    height: 40px;
    overflow: hidden;
    border-radius: 50%;
    cursor: pointer;
}

.cover {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.nav_icons {
    display: flex;
}

.nav_icons li {
    display: flex;
    list-style: none;
    cursor: pointer;
    color: #51585c;
    font-size: 1.5em;
    margin-left: 22px;
}

.search_chat {
    position: relative;
    width: 100%;
    height: 50px;
    background: #f6f6f6;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0px 15px;

}

.search_chat div {
    width: 100%;
}

.search_chat div input {
    width: 100%;
    outline: none;
    border: none;
    background: #fff;
    padding: 6px;
    height: 38px;
    border-radius: 30px;
    font-size: 14px;
    padding-left: 40px;
    margin-top: 10px;
}

.search_chat div input::placeholder {
    color: #bbb;
}

.search_chat div img {
    position: absolute;
    left: 30px;
    top: 14px;
    font-size: 1.5em;
}

.chatlist {
    position: relative;
    overflow-y: auto;
}

.chatlist .block {
    position: relative;
    width: 100%;
    display: flex;
    align-items: center;
    padding: 15px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.06);
}

.chatlist .block.active {
    background: #ebebeb;
}

.chatlist .block:hover {
    background: #f5f5f5;
}

.chatlist .block .imgbx {
    position: relative;
    min-width: 45px;
    height: 45px;
    overflow: hidden;
    border-radius: 50%;
    margin-right: 10px;
}

.chatlist .block .details {
    position: relative;
    width: 100%;
}

.chatlist .block .details .listHead {
    display: flex;
    justify-content: space-between;
    margin-bottom: 5px;
}

.chatlist .block .details .listHead h4 {
    font-size: 1.1em;
    font-weight: 600;
    color: #111;
}

.chatlist .block .details .listHead .time {
    font-size: 0.75em;
    color: #aaa;
}

.chatlist .block .details .listHead .time {
    color: #111;
}

.message_p {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.message_p p {
    color: #aaa;
    display: -webkit-box;
    -webkit-line-break: 1;
    font-size: 0.9em;
    overflow: hidden;
    text-overflow: ellipsis;
}

.message_p b {
    background-color: red;
    color: #fff;
    min-width: 20px;
    height: 20px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 0.75em;
}

.imgText {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
}

.imgText h4 {
    font-weight: 500;
    line-height: 1.2em;
    margin-left: 15px;
}

.imgText h4 span {
    font-size: 0.8em;
    color: #555;
}

.chatBox {
    position: relative;
    width: 650px;
    height: 390px;
    padding: 50px;
    overflow-y: auto;
}

.message {
    position: relative;
    display: flex;
    width: 100%;
    margin: 5px 0;
}

.message p {
    position: relative;
    right: 0;
    text-align: right;
    max-width: 65%;
    padding: 12px;
    background: #dcf8c6;
    border-radius: 10px;
    font-size: 0.9em;
}

.message p::before {
    content: '';
    position: absolute;
    top: 0;
    right: -12px;
    width: 20px;
    height: 20px;
    background: linear-gradient(135deg, #dcf8c6 0%, #dcf8c6 50%, transparent 50%, transparent);
}

.message p span {
    display: block;
    margin-top: 5px;
    font-size: 0.85em;
    opacity: 0.5;
}

.my_message {
    justify-content: flex-end;
}

.frnd_message {
    justify-content: flex-start;
}

.frnd_message p {
    background: #fff;
    text-align: left;
}

.message.frnd_message p::before {
    content: '';
    position: absolute;
    top: 0;
    left: -12px;
    width: 20px;
    height: 20px;
    background: linear-gradient(225deg, #fff 0%, #fff 50%, transparent 50%, transparent);
}

.chatbox_input {
    position: relative;
    width: 100%;
    height: 250px;
    background: #f0f0f0;
    padding: 15px;
    justify-content: space-between;
    align-items: center;
}

.chatbox_input input {
    position: relative;
    width: 90%;

    margin: 0 20px;
    padding: 10px 20px;
    border: none;
    outline: none;
    border-radius: 30px;
    font-size: 1em;
}

.red-point {
    display: inline-block;
    border-radius: 50%;
    min-height: 20px;
    min-width: 20px;
    background: red;
    line-height: 20px;
    font-size: 12px;
    color: #FFFFFF;
    text-align: center;
}
</style>
