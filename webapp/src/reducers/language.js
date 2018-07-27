
/**
 * Created by Barry on 2018/1/17.
 */



const language = (state = "cn", action) => {
    switch (action.type) {
        case 'LANGUAGE':
            return action.data+'';
        default:
            return state;
    }
}

export default language;