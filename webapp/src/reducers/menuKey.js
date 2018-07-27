/**
 * Created by Barry on 2017/12/13.
 */

const menuKey = (state = [], action) => {
    switch (action.type) {
        case 'MENUKEY':
            return [
                ...state,
                action.data.partnersMenusDto.menuCode,
            ];
        default:
            return state;
    }
}

export default menuKey;