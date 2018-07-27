/**
 * Created by Barry on 2017/12/26.
 */


// action types
const INIT_MERCHANT_LIST_DATA = 'INIT_MERCHANT_LIST_DATA'
// const CHANGE_COMMENT = 'CHANGE_COMMENT'

// reducer
const mchManage = (state = {}, action) => {

    switch (action.type) {
        case INIT_MERCHANT_LIST_DATA:
            // 初始化评论
            return { merchantListData: action.merchantListData }
        default:
            return state
    }
}

export default  mchManage;


