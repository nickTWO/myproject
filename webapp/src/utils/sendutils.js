/**
 * Created by Barry on 2017/12/15.
 */
import utils from '../axios/utils';



export default {
    handleSendEmailCode (e) {
        const params = {

        }
        utils.postData("/v1/send/sendMailCode", params).then( res => {
            if(!res){
                return;
            }
            if(res.code === '200'){
                if(res.resp_data.result_code ===1){
                    this.timeDown();
                    this.setState({
                        alertType: '',
                        message: '',
                        emailMessage: res.resp_data.message,
                    })
                }else {
                     this.setState({
                        alertType:'error',
                        message:res.resp_data.message,
                    });
                }
            }else {
                this.setState({
                    alertType:'error',
                    message:'系统异常',
                });
            }
        })

    },

    timeDown () {
        let self = this;
        let globalTime = this.state.global_time;
        const timer = setInterval(function (){
            if( globalTime > 0){
                globalTime --;
                self.setState({
                    emailButtonDisabled:true,
                    global_time:globalTime,
                    emailButtonMessage:'重新获取'+globalTime,
                });
            }else {
                clearInterval(timer);
                self.setState({
                    emailButtonDisabled:false,
                    emailButtonMessage:'重新获取',
                    global_time:60,
                })
            }
        },1000);
    }
}
