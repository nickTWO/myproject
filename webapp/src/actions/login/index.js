

//USER_LOGIN   NO_LOGIN
export const userLogin = (data) => (

    {
        type: data.type, //'USER_LOGIN',
        data: data.data,  //登陆的用户信息
    }

)

//language
export const language = (data) => ({
    type: data.type,
    data: data.data,
});

//商户菜单
export const partnerMenu = (data) => (
    {
        type: 'RENDER_MENU',
        data: data
    }
)

//商户菜单key
export const partnerKey = (data) => (
    {
        type: 'MENUKEY',
        data: data
    }
)

