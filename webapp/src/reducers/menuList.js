

let initState = [];

const dataSet = (data) => {

    const children = data.children;
    if(children){
        children.map((item) => {
            const name = item.partnersMenusDto.menuName;
            const url = item.partnersMenusDto.menuUrl;
            // item = {};
            item.name = name;
            item.url = url;
            item.key = item.menuId;
            return item;
        })
    }


    return {
        key:    data.menuId,
        name:   data.partnersMenusDto.menuName,
        url:    data.partnersMenusDto.menuCode,
        icon:   data.partnersMenusDto.menuIcon,
        children: children
    }
}

const menuList = (state = initState, action) => {
    switch (action.type) {
        case 'RENDER_MENU':
            return [
                ...state,
                dataSet(action.data)
            ];
        default:
            return {...state};
    }
}

export default menuList;








