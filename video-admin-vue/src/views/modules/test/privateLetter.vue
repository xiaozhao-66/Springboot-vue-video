<<template>
    <div class="container">
            <div class="leftSide">
                <!--search-->
                <div class="search_chat">
                    <div>
                        <input type="text" placeholder="搜索...">
                        <img src="搜索.png">
                    </div>
                </div>
                <!--ChatList-->
                <div class="chatlist" v-for="(userInfo,index) in userList" ::key="index">
                    <div class="block active">
                        <div class="imgbx">
                            <img :src="userInfo.avatar" class="cover">
                        </div>
                        <div class="details">
                            <div class="listHead">
                                <h4>{{userInfo.username}}</h4>
                                <p class="time">14:25</p>
                            </div>
                            <div class="message_p">
                                <p>什么时候出去玩？</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
 
 
            <div class="rightSide">
                <div class="header">
                    <div class="imgText">
                        <div class="userimg">
                            <img src="img1.jpg" class="cover">
                        </div>
                        <h4>开心果<br><span>在线</span></h4>
                    </div>
                    <ul class="nav_icons">
                        <li><img src="消息.png"></li>
                        <li><img src="更多.png"></li>
                    </ul>
                </div>
                <!--chatbox-->
                <div class="chatBox">
                    <div class="message my_message">
                        <p>你在干嘛呢？<br><span>10:15</span> </p>
                    </div>
                    <div class="message frnd_message">
                        <el-avatar :size="40" :src="circleUrl"></el-avatar>
                        <p>在写作业<br><span>10:15</span> </p>
                    </div>
                    <div class="message frnd_message">
                        <el-avatar :size="40" :src="circleUrl"></el-avatar>
                        <p>天气太热了天气太热了天气太热了天气太热了天气太热了天气太热了天气太热了天气太热了天气太热了天气太热了天气太热了天气太热了<br><span>10:15 </span> </p>
                    </div>
                    <div class="message my_message">
                        <p>对啊 也不下雨<br><span>10:15</span> </p>
                    </div>
                    <div class="message my_message">
                        <p>希望以后凉快点<br><span>10:15</span> </p>
                    </div>  
                </div>
 
                <!--chat input-->
            <div class="chatbox_input">
                <div class="instrument">
                    <img src="表情.png" >
                    <img src="语音.png">
                </div>
                <div>
                    <textarea style="height: 150px;width: 100%;border-radius: 4px"></textarea>
                    <el-button round style="float:right">发送</el-button>
                </div>
            </div>
            </div>
            
        </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》from '《组件路径》';

export default {
//import 引入的组件需要注入到对象中才能使用
components: {},
props: {},
data() {
    //这里存放数据
    return {
         userList:[
             { 
                id:'1',
                username:'zhangsan',
                avatar:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
             },
             {
                id:'2',
                username:'lisi',
                avatar:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
             },
             {
                id:'3',
                username:'wangwu',
                avatar:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
             },
         ]

         
    };
},
//计算属性类似于data 概念
computed: {},
//监控data 中的数据变化
watch: {},
//方法集合
methods: {
    
},
//生命周期- 创建完成（可以访问当前this 实例）
created() {

},
//生命周期- 挂载完成（可以访问DOM 元素）
mounted() {

},
beforeCreate() {}, //生命周期- 创建之前
beforeMount() {}, //生命周期- 挂载之前
beforeUpdate() {}, //生命周期- 更新之前
updated() {}, //生命周期- 更新之后
beforeDestroy() {}, //生命周期- 销毁之前
destroyed() {}, //生命周期- 销毁完成
activated() {}, //如果页面有keep-alive 缓存功能，这个函数会触发
}
</script>
<style >

*
{
    margin: 0;padding: 0;
    box-sizing: border-box;
    font-family: 'Open Sans' ,sans-serif;
}
body
{
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: linear-gradient(#009688 0%,#009688 130px,#d9dbd5 130px,#d9dbd5 100%)
}
.container
{
    position: relative;
    width: 1396px;
    max-width: 100%;
    height: calc( 100vh - 40px);
    background:#fff;
    box-shadow: 0 1px 1px 0 rgba(0,0,0,0.06),0 2px 5px 0 rgba(0,0,0,0.06);
    display: flex;
}
.container .leftSide
{
    position: relative;
    background:#fff;
    border-right: 0px solid rgba(0, 0, 0, 0.2);
}
.container .rightside
{
    position: relative;
  
    background:#e5ddd5;
}
.container .rightside::before
{
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    opacity: 0.06;
}
.header
{
    position: relative;
    width: 100%;
    height: 60px;
    background: #ededed;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 15px;
}
.userimg
{
    position: relative;
    width: 40px;
    height: 40px;
    overflow: hidden;
    border-radius: 50%;
    cursor: pointer;
}
.cover
{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.nav_icons
{
    display: flex;
}
.nav_icons li
{
    display: flex;
    list-style: none;
    cursor: pointer;
    color: #51585c;
    font-size: 1.5em;
    margin-left: 22px;
}
.search_chat
{
    position: relative;
    width: 100%;
    height: 50px;
    background: #f6f6f6;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 15px;
}
.search_chat div
{
    width: 100%;
}
.search_chat div input
{
    width:100%;
    outline: none;
    border: none;
    background: #fff;
    padding: 6px;
    height: 38px;
    border-radius: 30px;
    font-size: 14px;
    padding-left: 40px;
}
.search_chat div input::placeholder
{
    color: #bbb;
}
.search_chat div img
{
    position: absolute;
    left: 30px;
    top: 14px;
    font-size: 1.5em;
}
.chatlist
{
    position: relative;
    height: calc(100%-110px);
    overflow-y: auto;
}
.chatlist .block
{
    position: relative;
    width: 100%;
    display: flex;
    align-items: center;
    padding: 15px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.06);
}
.chatlist .block.active{
    background: #ebebeb;
}
.chatlist .block:hover
{
    background:#f5f5f5;
}
.chatlist .block .imgbx
{
    position: relative;
    min-width: 45px;
    height: 45px;
    overflow: hidden;
    border-radius: 50%;
    margin-right: 10px;
}
.chatlist .block .details
{
    position: relative;
    width: 100%;
}
.chatlist .block .details .listHead
{
    display: flex;
    justify-content: space-between;
    margin-bottom: 5px;
}
.chatlist .block .details .listHead h4
{
    font-size: 1.1em;
    font-weight: 600;
    color: #111;
}
.chatlist .block .details .listHead .time
{
    font-size: 0.75em;
    color: #aaa;
}
.chatlist .block .details .listHead .time
{
    color: #111;
}
 
.message_p
{
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.message_p p
{
    color: #aaa;
    display: -webkit-box;
    -webkit-line-break: 1;
    font-size: 0.9em;
    overflow: hidden;
    text-overflow: ellipsis;
}
.message_p b
{
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
.imgText
{
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
}
.imgText h4
{
    font-weight: 500;
    line-height: 1.2em;
    margin-left: 15px;
}
.imgText h4 span
{
    font-size: 0.8em;
    color: #555;
}
.chatBox
{
    position: relative;
    width: 100%;
    height: calc(100%-120px);
    padding: 50px;
    overflow-y: auto;
}
.message
{
    position: relative;
    display: flex;
    width: 100%;
    margin: 5px 0;
}
.message p
{
    position: relative;
    right: 0;
    text-align: right;
    max-width: 65%;
    padding: 12px;
    background: #dcf8c6;
    border-radius: 10px;
    font-size: 0.9em;
}
.message p::before
{
    content: '';
    position: absolute;
    top: 0;
    right: -12px;
    width: 20px;
    height: 20px;
    background: linear-gradient(135deg,#dcf8c6 0%,#dcf8c6 50%,transparent 50%,transparent);
}
.message p span
{
    display: block;
    margin-top: 5px;
    font-size: 0.85em;
    opacity: 0.5;
}
.my_message
{
    justify-content: flex-end;
}
.frnd_message
{
    justify-content: flex-start;
}
.frnd_message p
{
    background: #fff;
    text-align: left;
}
.message.frnd_message p::before
{
    content: '';
    position: absolute;
    top: 0;
    left: -12px;
    width: 20px;
    height: 20px;
    background: linear-gradient(225deg,#fff 0%,#fff 50%,transparent 50%,transparent);
}
.chatbox_input
{
    position: relative;
    width: 100%;
    height: 250px;
    background: #f0f0f0;
    padding: 15px;
    justify-content: space-between;
    align-items: center;
}
.chatbox_input input
{
    position: relative;
    width: 90%;
    
    margin: 0 20px;
    padding: 10px 20px;
    border: none;
    outline: none;
    border-radius: 30px;
    font-size: 1em;
}
</style>